package com.czhang.cpms.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czhang.cpms.model.db.Problem;
import com.czhang.cpms.model.domain.ProblemJsonModel;
import com.czhang.cpms.repositories.ProblemRepository;

@Service("problemService")
@Transactional
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemRepository problemRepository;
	@Autowired
	ResourceLoader resourceLoader;

	public Problem findById(UUID id) {
		return problemRepository.findOne(id);
	}

	public Problem findByTitle(String title) {
		return problemRepository.findByTitle(title);		
	}
	
	public Problem saveProblem(ProblemJsonModel problem) {
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
		return problemRepository.save(newProblem);
	}

	public void updateProblem(Problem problem) {
		problemRepository.save(problem);
	}

	public void deleteProblemById(UUID id) {
		problemRepository.delete(id);
	}

	public void deleteAllProblems() {
		problemRepository.deleteAll();
	}

	public List<ProblemJsonModel> findAllProblems() {
		List<Problem> allProblems = problemRepository.findAll();
		List<ProblemJsonModel> res = new ArrayList<>();
		for (Problem p : allProblems) {
			res.add(new ProblemJsonModel(p));
		}
		return res;
	}

	public boolean isProblemExist(ProblemJsonModel problem) {
		return findByTitle(problem.getTitle()) != null;
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
