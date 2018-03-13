package com.czhang.cpms.service;

import java.util.List;
import java.util.UUID;

import com.czhang.cpms.model.db.ProblemDAO;
import com.czhang.cpms.model.domain.Problem;

public interface ProblemService {
	ProblemDAO findById(UUID id);

	ProblemDAO findByTitle(String title);
	
	ProblemDAO saveProblem(Problem problem);

	void updateProblem(ProblemDAO problem);

	void deleteProblemById(UUID id);

	void deleteAllProblems();

	List<Problem> findAllProblems();

	boolean isProblemExist(Problem problem);
}
