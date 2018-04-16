package com.czhang.cpms.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.czhang.cpms.model.db.RoleDAO;
import com.czhang.cpms.model.db.UserDAO;
import com.czhang.cpms.model.domain.User;
import com.czhang.cpms.model.response.UserResponse;
import com.czhang.cpms.service.RoleService;
import com.czhang.cpms.service.UserService;

@CrossOrigin(origins = { "*" }, allowCredentials = "false", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserRestApiController {
	public static final Logger logger = LoggerFactory.getLogger(UserRestApiController.class);

	// Service which will do all data retrieval/manipulation work
	@Autowired
	UserService userService;	

	// -------------------Retrieve All Users---------------------
	@RequestMapping(value = "/user/", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public ResponseEntity<UserResponse> listAllUsers() {
		UserResponse response = new UserResponse();
		List<UserDAO> users = userService.findAllUsers();
		response.setPayload(users);
		response.setSuccess(true);
		if (users.isEmpty()) {
			response.setMessage("No problems found");
		}
		response.setMessage("Get all problems.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// -------------------Create a User-----------------------------
	@RequestMapping(value = "/user/", method = RequestMethod.POST)
	public ResponseEntity<UserResponse> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Problem : {}", user);
		UserResponse response = new UserResponse();
		if (userService.isUserExist(user)) {
			String message = "Unable to create. A User with name " + user.getUsername() + " already exist";
			logger.error(message);
			response.setSuccess(false);
			response.setMessage(message);
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		UserDAO newUser = userService.saveUser(user);		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(newUser.getId()).toUri());
		response.setSuccess(true);
		response.setMessage("New user created");
		response.setPayload(headers);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
