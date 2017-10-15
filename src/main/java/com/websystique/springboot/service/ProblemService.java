package com.websystique.springboot.service;

import java.util.List;

import com.websystique.springboot.model.Problem;
import com.websystique.springboot.model.ProblemJson;

public interface ProblemService {
	Problem findById(Long id);

	Problem findByName(String name);

	void saveProblem(ProblemJson problem);

	void updateProblem(Problem problem);

	void deleteProblemById(Long id);

	void deleteAllProblems();

	List<ProblemJson> findAllProblems();

	boolean isProblemExist(ProblemJson problem);
}
