package com.czhang.cpms.controller;

import java.util.List;

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

import com.czhang.cpms.model.Problem;
import com.czhang.cpms.model.ProblemJson;
import com.czhang.cpms.service.ProblemService;
import com.czhang.cpms.util.CustomErrorType;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ProblemRestApiController {

	public static final Logger logger = LoggerFactory.getLogger(ProblemRestApiController.class);

	// Service which will do all data retrieval/manipulation work
	@Autowired
	ProblemService problemService;

	// -------------------Retrieve All Problems
	// --------------------------------------

	@RequestMapping(value = "/problem/", method = RequestMethod.GET)
	public ResponseEntity<List<ProblemJson>> listAllUsers() {
		List<ProblemJson> problems = problemService.findAllProblems();
		if (problems.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<>(problems, HttpStatus.OK);
	}

	// -------------------Retrieve Single Problem
	// ---------------------------------------

	@RequestMapping(value = "/problem/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProblem(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		Problem problem = problemService.findById(id);
		if (problem == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ProblemJson(problem), HttpStatus.OK);
	}

	// -------------------Create a Problem
	// ---------------------------------------

	@RequestMapping(value = "/problem/", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody ProblemJson problem, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Problem : {}", problem);
		if (problemService.isProblemExist(problem)) {
			logger.error("Unable to create. A Problem with name {} already exist", problem.getTITLE());
			return new ResponseEntity<>(
					new CustomErrorType("Unable to create. A User with name " + problem.getTITLE() + " already exist."),
					HttpStatus.CONFLICT);
		}
		problemService.saveProblem(problem);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/problem/{id}").buildAndExpand(problem.getID()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// ------------------- Import Problems ---------------------------------------

	@RequestMapping(value = "/problem/import/", method = RequestMethod.POST)
	public ResponseEntity<?> importProblems(@RequestBody List<ProblemJson> problems, UriComponentsBuilder ucBuilder) {
		for(ProblemJson problem : problems){
			logger.info("Creating Problem : {}", problem);
			problemService.saveProblem(problem);
		}
		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Problem
	// ----------------------------------------

	@RequestMapping(value = "/problem/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProblem(@PathVariable("id") long id, @RequestBody Problem problem) {
		logger.info("Updating User with id {}", id);
		Problem currentProblem = problemService.findById(id);

		if (currentProblem == null) {
			logger.error("Unable to update. Problem with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentProblem.setName(problem.getName());
		currentProblem.setTags(problem.getTags());
		currentProblem.setSpecialtags(problem.getSpecialtags());
		currentProblem.setSolution(problem.getSolution());
		currentProblem.setNumber(problem.getNumber());
		currentProblem.setDifficulty(problem.getDifficulty());
		currentProblem.setDescription(problem.getDescription());
		currentProblem.setCompanies(problem.getCompanies());

		problemService.updateProblem(currentProblem);
		return new ResponseEntity<>(currentProblem, HttpStatus.OK);
	}

	// ------------------- Delete a
	// PRoblem-----------------------------------------

	@RequestMapping(value = "/problem/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteProblem(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Problem with id {}", id);

		Problem problem = problemService.findById(id);
		if (problem == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity<>(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		problemService.deleteProblemById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Problems-----------------------------

	@RequestMapping(value = "/problem/", method = RequestMethod.DELETE)
	public ResponseEntity<Problem> deleteAllProblems() {
		logger.info("Deleting All Problems");

		problemService.deleteAllProblems();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
