package com.czhang.cpms.repositories;


import org.springframework.data.repository.CrudRepository;

import com.czhang.cpms.model.db.RoleDAO;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface RoleRepository extends CrudRepository<RoleDAO, Long> {
}
