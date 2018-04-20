package com.czhang.cpms.repositories;

import org.springframework.data.repository.CrudRepository;

import com.czhang.cpms.model.db.SolutionDAO;

public interface SolutionRepository extends CrudRepository<SolutionDAO, String> {

}
