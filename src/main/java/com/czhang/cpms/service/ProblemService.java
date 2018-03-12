package com.czhang.cpms.service;

import java.util.List;

import com.czhang.cpms.model.Problem;
import com.czhang.cpms.model.ProblemJson;

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
