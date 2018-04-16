package com.czhang.cpms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.czhang.cpms.model.db.RoleDAO;
import com.czhang.cpms.repositories.RoleRepository;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void saveRole(RoleDAO role) {
		roleRepository.save(role);		
	}

	@Override
	public List<RoleDAO> findAllRoles() {
		return (List<RoleDAO>) roleRepository.findAll();
	}

	@Override
	public RoleDAO findRolebyId(long id) {		
		return roleRepository.findOne(id);
	}

}
