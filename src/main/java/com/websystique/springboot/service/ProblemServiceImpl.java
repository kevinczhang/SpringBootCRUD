package com.websystique.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springboot.model.Problem;
import com.websystique.springboot.model.ProblemJson;
import com.websystique.springboot.repositories.ProblemRepository;

@Service("problemService")
@Transactional
public class ProblemServiceImpl implements ProblemService{
	
	@Autowired
	private ProblemRepository problemRepository;
	
	public Problem findById(Long id){
		return problemRepository.findOne(id);
	}

	public Problem findByName(String name){
		return problemRepository.findByName(name);
	}

	public void saveProblem(ProblemJson problem){
		Problem newProblem = new Problem(problem);
		problemRepository.save(newProblem);
	}

	public void updateProblem(Problem problem){
		problemRepository.save(problem);
	}

	public void deleteProblemById(Long id){
		problemRepository.delete(id);
	}

	public void deleteAllProblems(){
		problemRepository.deleteAll();
	}

	public List<ProblemJson> findAllProblems(){
		List<Problem> allProblems = problemRepository.findAll();
		List<ProblemJson> res = new ArrayList<>();
		for(Problem p : allProblems){
			res.add(new ProblemJson(p));
		}		
		return res;
	}

	public boolean isProblemExist(ProblemJson problem){
		return findByName(problem.getTITLE()) != null;
	}
}
