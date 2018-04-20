package com.czhang.cpms;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.czhang.cpms.repositories.ProblemRepository;
import com.czhang.cpms.repositories.SolutionRepository;
import com.czhang.cpms.repositories.UserRepository;
import com.czhang.cpms.service.RoleService;
import com.czhang.cpms.util.Constants;
import com.czhang.cpms.util.ProblemServiceHelper;
import com.czhang.cpms.model.db.ProblemDAO;
import com.czhang.cpms.model.db.RoleDAO;
import com.czhang.cpms.model.db.SolutionDAO;
import com.czhang.cpms.model.db.UserDAO;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private ProblemRepository problemRepository;
	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	RoleService roleService;
	@Value("${security.encoding-strength}")
	private Integer encodingStrength;
	@Autowired
	private SolutionRepository solutionRepository;

	UserDAO adminUser;
	List<RoleDAO> roles;

	/**
	 * This event is executed as late as conceivably possible to indicate that the
	 * application is ready to service requests.
	 */
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {

		initialDBSetup();

		return;
	}

	private void initialDBSetup() {		
		roles = new ArrayList<>();
		addRoles();
		addUsers();
		addProblems();
	}

	@Transactional
	private void addRoles() {
		// Add user roles
		RoleDAO standardRole = new RoleDAO();
		standardRole.setId(1L);
		standardRole.setRoleName("STANDARD_USER");
		standardRole.setDescription("Standard User - Has no admin rights");
		roleService.saveRole(standardRole);
		roles.add(standardRole);
		
		RoleDAO adminRole = new RoleDAO();
		adminRole.setId(2L);
		adminRole.setRoleName("ADMIN_USER");
		adminRole.setDescription("Admin User - Has permission to perform admin tasks");
		roleService.saveRole(adminRole);
		roles.add(adminRole);
	}

	@Transactional
	private void addUsers() {
		// Add new users
		adminUser = new UserDAO();
		adminUser.setId(1L);
		adminUser.setUsername("admin@admin");
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(encodingStrength);
		adminUser.setPassword(encoder.encodePassword("jwtpass", ""));
		adminUser.setFirstName("Admin");
		adminUser.setLastName("Admin");
		adminUser.setRoles(roles);

		userRepository.save(adminUser);
	}

	@Transactional
	private void addProblems() {
		JSONParser parser = new JSONParser();
		String jsonFile = getFile("classpath:/data/problemList.json");

		try {
			JSONArray problemArray = (JSONArray) parser.parse(jsonFile);
			for (Object o : problemArray) {
				JSONObject problemJSON = (JSONObject) o;
				long source = (Long) problemJSON.get("source");
				long number = (Long) problemJSON.get("number");
				long type = (Long) problemJSON.get("type");
				String title = (String) problemJSON.get("title");
				long difficulty = (Long) problemJSON.get("difficulty");

				
				JSONArray topicArray = (JSONArray) problemJSON.get("topics");
				String topics = topicArray.toJSONString();
				topics = (topics.length() >= 2) ? topics.substring(1, topics.length()-1) : "";				

				JSONArray companyArray = (JSONArray) problemJSON.get("companies");
				String companies = companyArray.toJSONString();
				companies = (companies.length() >= 2) ? companies.substring(1, companies.length()-1) : "";
				
				JSONArray tagArray = (JSONArray) problemJSON.get("tags");
				String tags = tagArray.toJSONString();
				tags = (tags.length() >= 2) ? tags.substring(1, tags.length()-1) : "";

				String problemDescription = getFile("classpath:/descriptions/" + problemJSON.get("file") + ".html");

				ProblemDAO newProblem = new ProblemDAO();
				newProblem.setId(UUID.randomUUID().toString());
				newProblem.setSource(Constants.sources.get((int) source).name());
				newProblem.setNumber((int) number);
				newProblem.setType(Constants.types.get((int) type).name());
				newProblem.setTitle(title);
				newProblem.setDifficulty(Constants.levels.get((int) difficulty).name());
				newProblem.setTopics(topics);
				newProblem.setCompanies(companies);
				newProblem.setTags(tags);
				newProblem.setFamiliarity(0);
				newProblem.setDescription(Base64.getEncoder().encode(problemDescription.getBytes()));
				newProblem.setCreatedTime(new Date());
				newProblem.setUpdatedTime(new Date());
				List<UserDAO> users = new ArrayList<>();
				users.add(adminUser);
				newProblem.setUsers(users);

				ProblemDAO savedProblem = problemRepository.save(newProblem);

				SolutionDAO newSolution = new SolutionDAO();
				newSolution.setId(UUID.randomUUID().toString());
				newSolution.setLanguage(0);
				newSolution.setCreatedTime(new Date());
				newSolution.setUpdatedTime(new Date());
				String solution = getFile("classpath:/solutions/" + problemJSON.get("file") + ".java");
				newSolution.setSolution(Base64.getEncoder().encode(solution.getBytes()));
				newSolution.setProblem(savedProblem);
				newSolution.setUser(adminUser);
				solutionRepository.save(newSolution);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private String getFile(String filePath) {
		String problems = "";
		Resource problemsResource = resourceLoader.getResource(filePath);

		try {
			InputStream problemsInputStream = problemsResource.getInputStream();
			problems = ProblemServiceHelper.readFromInputStream(problemsInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return problems;
	}

} // class
