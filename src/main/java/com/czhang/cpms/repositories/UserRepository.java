package com.czhang.cpms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.czhang.cpms.model.db.UserDAO;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Long> {

	UserDAO findByUsername(String username);

}
