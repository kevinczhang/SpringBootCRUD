package com.czhang.cpms.service;

import java.util.List;

import com.czhang.cpms.model.db.RoleDAO;
import com.czhang.cpms.model.db.UserDAO;
import com.czhang.cpms.model.domain.User;
import com.czhang.cpms.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	RoleService roleService;

	@Value("${security.encoding-strength}")
	private Integer encodingStrength;

	public UserDAO findById(Long id) {
		return userRepository.findOne(id);
	}

	public UserDAO findByUserName(String name) {
		return userRepository.findByUsername(name);
	}
	
	public UserDAO saveUser(User user) {
		ShaPasswordEncoder encoder = new ShaPasswordEncoder(encodingStrength);
		user.setPassword(encoder.encodePassword(user.getPassword(), ""));
		UserDAO newUser = new UserDAO(user);
		RoleDAO userRole = roleService.findRolebyId(1);
		newUser.getRoles().add(userRole);
		return userRepository.save(newUser);
	}

	public void updateUser(User user){
		saveUser(user);
	}

	public void deleteUserById(Long id){
		userRepository.delete(id);
	}

	public void deleteAllUsers(){
		userRepository.deleteAll();
	}

	public List<UserDAO> findAllUsers(){
		return userRepository.findAll();
	}

	public boolean isUserExist(User user) {
		return findByUserName(user.getUsername()) != null;
	}
}
