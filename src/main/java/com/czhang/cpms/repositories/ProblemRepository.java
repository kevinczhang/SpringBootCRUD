package com.czhang.cpms.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.czhang.cpms.model.db.ProblemDAO;

@Repository
public interface ProblemRepository extends CrudRepository<ProblemDAO, String>{

	ProblemDAO findByTitle(String title);

}
