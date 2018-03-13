package com.czhang.cpms.service;

import java.util.List;
import java.util.UUID;

import com.czhang.cpms.model.db.Problem;
import com.czhang.cpms.model.domain.ProblemJsonModel;

public interface ProblemService {
	Problem findById(UUID id);

	Problem findByTitle(String title);
	
	Problem saveProblem(ProblemJsonModel problem);

	void updateProblem(Problem problem);

	void deleteProblemById(UUID id);

	void deleteAllProblems();

	List<ProblemJsonModel> findAllProblems();

	boolean isProblemExist(ProblemJsonModel problem);
}
