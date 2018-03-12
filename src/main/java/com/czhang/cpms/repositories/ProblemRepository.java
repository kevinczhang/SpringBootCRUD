package com.czhang.cpms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.czhang.cpms.model.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long>{

	Problem findByName(String name);
	
}
