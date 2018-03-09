package com.websystique.springboot.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springboot.model.Problem;
import com.websystique.springboot.model.ProblemJson;
import com.websystique.springboot.repositories.ProblemRepository;

@Service("problemService")
@Transactional
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemRepository problemRepository;
	@Autowired
	ResourceLoader resourceLoader;

	public Problem findById(Long id) {
		return problemRepository.findOne(id);
	}

	public Problem findByName(String name) {
		return problemRepository.findByName(name);
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

		Problem newProblem = new Problem(problem);
		problemRepository.save(newProblem);
	}

	public void updateProblem(Problem problem) {
		problemRepository.save(problem);
	}

	public void deleteProblemById(Long id) {
		problemRepository.delete(id);
	}

	public void deleteAllProblems() {
		problemRepository.deleteAll();
	}

	public List<ProblemJson> findAllProblems() {
		List<Problem> allProblems = problemRepository.findAll();
		List<ProblemJson> res = new ArrayList<>();
		for (Problem p : allProblems) {
			res.add(new ProblemJson(p));
		}
		return res;
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
