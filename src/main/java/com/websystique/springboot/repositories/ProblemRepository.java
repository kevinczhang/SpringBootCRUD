package com.websystique.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.websystique.springboot.model.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Long>{

	Problem findByName(String name);
	
}
