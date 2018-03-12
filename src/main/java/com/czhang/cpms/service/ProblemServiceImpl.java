package com.czhang.cpms.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czhang.cpms.model.Problem;
import com.czhang.cpms.model.ProblemJson;
import com.czhang.cpms.model.domain.ProblemJsonModel;
import com.czhang.cpms.repositories.ProblemRepository;

@Service("problemService")
@Transactional
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemRepository problemRepository;
	@Autowired
	ResourceLoader resourceLoader;

	public Problem findById(Long id) {
		// return problemRepository.findOne(id);
		return null;
	}

	public Problem findByName(String name) {
		// return problemRepository.findByName(name);
		return null;
	}

	public void saveProblem(ProblemJson problem) {
		Resource resource = resourceLoader.getResource("classpath:/solutions/Easy/addBinary.java");
		try {
			InputStream resourceInputStream = resource.getInputStream();
			String res = readFromInputStream(resourceInputStream);
			System.out.print(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		com.czhang.cpms.model.db.Problem newProblem = new com.czhang.cpms.model.db.Problem(problem);
		problemRepository.save(newProblem);
	}
	
	public void saveProblem(ProblemJsonModel problem) {
		Resource solutionResource = resourceLoader.getResource("classpath:/solutions/" + problem.getFile() +".java");
		Resource descriptionResource = resourceLoader.getResource("classpath:/descriptions/" + problem.getFile() +".html");
		try {
			InputStream solutionInputStream = solutionResource.getInputStream();
			InputStream descriptionInputStream = descriptionResource.getInputStream();
			String solution = readFromInputStream(solutionInputStream);
			String description = readFromInputStream(descriptionInputStream);
			problem.setSolution(solution);
			problem.setDescription(description);
		} catch (IOException e) {
			e.printStackTrace();
		}

		com.czhang.cpms.model.db.Problem newProblem = new com.czhang.cpms.model.db.Problem(problem);
		problemRepository.save(newProblem);
	}

	public void updateProblem(Problem problem) {
		//problemRepository.save(problem);
	}

	public void deleteProblemById(Long id) {
		//problemRepository.delete(id);
	}

	public void deleteAllProblems() {
		problemRepository.deleteAll();
	}

	public List<ProblemJson> findAllProblems() {
//		List<Problem> allProblems = problemRepository.findAll();
//		List<ProblemJson> res = new ArrayList<>();
//		for (Problem p : allProblems) {
//			res.add(new ProblemJson(p));
//		}
//		return res;
		return null;
	}

	public boolean isProblemExist(ProblemJson problem) {
		return findByName(problem.getTITLE()) != null;
	}

	private String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}
}
