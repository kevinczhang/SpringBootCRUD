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

import com.czhang.cpms.model.db.ProblemDAO;
import com.czhang.cpms.model.domain.Problem;
import com.czhang.cpms.repositories.ProblemRepository;
import com.czhang.cpms.util.ProblemServiceHelper;

@Service("problemService")
@Transactional
public class ProblemServiceImpl implements ProblemService {

	@Autowired
	private ProblemRepository problemRepository;
	@Autowired
	ResourceLoader resourceLoader;

	public ProblemDAO findById(String id) {
		return problemRepository.findOne(id);
	}

	public ProblemDAO findByTitle(String title) {
		return problemRepository.findByTitle(title);		
	}
	
	public ProblemDAO saveProblem(Problem problem) {
		Resource solutionResource = resourceLoader.getResource("classpath:/solutions/" + problem.getFile() +".java");
		Resource descriptionResource = resourceLoader.getResource("classpath:/descriptions/" + problem.getFile() +".html");
		if(solutionResource.exists() && descriptionResource.exists()) {
			try {
				InputStream solutionInputStream = solutionResource.getInputStream();
				InputStream descriptionInputStream = descriptionResource.getInputStream();
				String solution = ProblemServiceHelper.readFromInputStream(solutionInputStream);
				String description = ProblemServiceHelper.readFromInputStream(descriptionInputStream);
				problem.setSolution(solution);
				problem.setDescription(description);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		ProblemDAO newProblem = new ProblemDAO(problem);
		return problemRepository.save(newProblem);
	}

	public void updateProblem(ProblemDAO problem) {
		problemRepository.save(problem);
	}

	public void deleteProblemById(String id) {
		problemRepository.delete(id);
	}

	public void deleteAllProblems() {
		problemRepository.deleteAll();
	}

	public List<Problem> findAllProblems() {
		List<ProblemDAO> allProblems = (List<ProblemDAO>) problemRepository.findAll();
		List<Problem> res = new ArrayList<>();
		for (ProblemDAO p : allProblems) {
			res.add(new Problem(p));
		}
		return res;
	}

	public boolean isProblemExist(Problem problem) {
		return findByTitle(problem.getTitle()) != null;
	}	
}
