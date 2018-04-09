package com.czhang.cpms.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.czhang.cpms.model.db.ProblemDAO;
import com.czhang.cpms.model.domain.Problem;
import com.czhang.cpms.model.response.ProblemResponse;
import com.czhang.cpms.service.ProblemService;
import com.czhang.cpms.util.ProblemServiceHelper;

@CrossOrigin(origins = { "*" }, allowCredentials = "false", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ProblemRestApiController {

	public static final Logger logger = LoggerFactory.getLogger(ProblemRestApiController.class);

	// Service which will do all data retrieval/manipulation work
	@Autowired
	ProblemService problemService;

	// -------------------Retrieve All Problems---------------------
	@RequestMapping(value = "/problem/", method = RequestMethod.GET)
	public ResponseEntity<ProblemResponse> listAllProblems() {
		ProblemResponse response = new ProblemResponse();
		List<Problem> problems = problemService.findAllProblems();
		response.setPayload(problems);
		response.setSuccess(true);
		if (problems.isEmpty()) {
			response.setMessage("No problems found");
		}		
		response.setMessage("Get all problems.");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// -------------------Retrieve Single Problem---------------------
	@RequestMapping(value = "/problem/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProblemResponse> getProblem(@PathVariable("id") String id) {
		logger.info("Fetching User with id {}", id);
		ProblemResponse response = new ProblemResponse();
		//UUID problemId = ProblemServiceHelper.convertStringToUUID(id);
		ProblemDAO problem = problemService.findById(id);
		if (problem == null) {
			String message = "Problem with id " + id + " not found.";
			logger.error(message);
			response.setSuccess(true);
			response.setMessage(message);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setSuccess(true);
		response.setMessage("Get the problem");
		response.setPayload(new Problem(problem));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// -------------------Create a Problem-----------------------------
	@RequestMapping(value = "/problem/", method = RequestMethod.POST)
	public ResponseEntity<ProblemResponse> createUser(@RequestBody Problem problem, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Problem : {}", problem);
		ProblemResponse response = new ProblemResponse();
		if (problemService.isProblemExist(problem)) {
			String message = "Unable to create. A Problem with name " + problem.getTitle() + " already exist";
			logger.error(message);
			response.setSuccess(false);
			response.setMessage(message);
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}
		ProblemDAO newProblem = problemService.saveProblem(problem);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/problem/{id}").buildAndExpand(newProblem.getId()).toUri());
		response.setSuccess(true);
		response.setMessage("New user created");
		response.setPayload(headers);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// ------------------- Import Problems ------------------------------------
	@RequestMapping(value = "/problem/import/", method = RequestMethod.POST)
	public ResponseEntity<ProblemResponse> importProblems(@RequestBody List<Problem> problems, UriComponentsBuilder ucBuilder) {
		ProblemResponse response = new ProblemResponse();
		for(Problem problem : problems){
			logger.info("Creating Problem : {}", problem);
			problemService.saveProblem(problem);
		}
		response.setSuccess(true);
		response.setMessage("Problems imported");
		response.setPayload(new HttpHeaders());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// ------------------- Update a Problem------------------------------------
	@RequestMapping(value = "/problem/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ProblemResponse> updateProblem(@PathVariable("id") String id, @RequestBody Problem problem) {
		logger.info("Updating User with id {}", id);
		//id = id.replaceAll("-", "");
		ProblemResponse response = new ProblemResponse();
		//UUID problemId = ProblemServiceHelper.convertStringToUUID(id);
		ProblemDAO currentProblem = problemService.findById(id);

		if (currentProblem == null) {
			String message = "Unable to update. Problem with id " + id + " not found.";
			logger.error(message);
			response.setSuccess(false);
			response.setMessage(message);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		currentProblem = new ProblemDAO(problem);
		currentProblem.setId(id);
		problemService.updateProblem(currentProblem);		
		response.setSuccess(true);
		response.setMessage("Problem updated");
		response.setPayload(new Problem(currentProblem));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ------------------- Delete a PRoblem-------------------------------
	@RequestMapping(value = "/problem/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ProblemResponse> deleteProblem(@PathVariable("id") String id) {
		logger.info("Fetching & Deleting Problem with id {}", id);
		ProblemResponse response = new ProblemResponse();
		// UUID problemId = ProblemServiceHelper.convertStringToUUID(id);
		ProblemDAO problem = problemService.findById(id);
		if (problem == null) {
			String message = "Unable to delete. User with id " + id + " not found.";
			logger.error(message);
			response.setSuccess(false);
			response.setMessage(message);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		problemService.deleteProblemById(id);
		response.setSuccess(true);
		response.setMessage("Problem deleted");
		return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Problems-----------------------------
	@RequestMapping(value = "/problem/", method = RequestMethod.DELETE)
	public ResponseEntity<ProblemDAO> deleteAllProblems() {
		logger.info("Deleting All Problems");

		problemService.deleteAllProblems();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
