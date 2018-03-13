package com.czhang.cpms.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.czhang.cpms.model.db.ProblemDAO;

@Repository
public interface ProblemRepository extends JpaRepository<ProblemDAO, UUID>{

	ProblemDAO findByTitle(String title);

}
