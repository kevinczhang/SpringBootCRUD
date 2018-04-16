package com.czhang.cpms.service;

import java.util.List;

import com.czhang.cpms.model.db.RoleDAO;

public interface RoleService {
	
	void saveRole(RoleDAO role);
	
	List<RoleDAO> findAllRoles();
	
	RoleDAO findRolebyId(long id);
}
