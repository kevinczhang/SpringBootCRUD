package com.czhang.cpms.model.db;

import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.czhang.cpms.model.domain.Problem;
import com.czhang.cpms.util.Constants;

@Entity
@Table(name = "Problem")
public class ProblemDAO {
	
	@Id	
	UUID id;
	String source;
	@Column(name="problem_number")
	int number;
	String type;
	String title;
	String difficulty;	
	String topics;
	String companies;
	String tags;
	@Lob
	@Column(length=100000)
	byte[] description;
	@Lob
	@Column(length=100000)
	byte[] solution;
	
	public ProblemDAO(){}

	public ProblemDAO(Problem problem) {
		this.id = UUID.randomUUID();
		this.source = Constants.sources.get(problem.getSource()).name();
		this.number = problem.getNumber();
		this.type = Constants.types.get(problem.getType()).name();
		this.title = problem.getTitle();
		this.difficulty = Constants.levels.get(problem.getDifficulty()).name();
		this.topics = Arrays.toString(problem.getTopics()).replaceAll("[\\[\\]\"]", "");
		this.companies = Arrays.toString(problem.getCompanies()).replaceAll("[\\[\\]\"]", "");
		this.tags = Arrays.toString(problem.getTags()).replaceAll("[\\[\\]\"]", "");
		this.description = Base64.getEncoder().encode(problem.getDescription().getBytes());
		this.solution = Base64.getEncoder().encode(problem.getSolution().getBytes());
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getTopics() {
		return topics;
	}

	public void setTopics(String topics) {
		this.topics = topics;
	}

	public String getCompanies() {
		return companies;
	}

	public void setCompanies(String companies) {
		this.companies = companies;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public byte[] getDescription() {
		return description;
	}

	public void setDescription(byte[] description) {
		this.description = description;
	}

	public byte[] getSolution() {
		return solution;
	}

	public void setSolution(byte[] solution) {
		this.solution = solution;
	}

	
}
