package com.czhang.cpms.model.domain;

import java.util.Base64;
import java.util.List;

import com.czhang.cpms.model.db.ProblemDAO;
import com.czhang.cpms.util.Constants;
import com.czhang.cpms.util.ProblemServiceHelper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Problem {
	@JsonProperty("id")
	String id;
	@JsonProperty("source")
	int source;
	@JsonProperty("number")
	int number;
	@JsonProperty("type")
	int type;
	@JsonProperty("title")
	String title;
	@JsonProperty("difficulty")
	int difficulty;
	@JsonProperty("file")
	String file;
	@JsonProperty("topics")
	int[] topics;
	@JsonProperty("companies")
	int[] companies;
	@JsonProperty("tags")
	int[] tags;
	@JsonProperty("familiarity")
	int familiarity;
	@JsonProperty("solution")
	String solution;
	@JsonProperty("description")
	String description;
	
	public Problem(){}

	public Problem(ProblemDAO p) {
		this.id = String.valueOf(p.getId());
		this.source = ProblemServiceHelper.getIndex(Constants.sources, p.getSource());
		this.number = p.getNumber();
		this.type = ProblemServiceHelper.getIndex(Constants.types, p.getType());
		this.title = p.getTitle();
		this.difficulty = ProblemServiceHelper.getIndex(Constants.levels, p.getDifficulty());
		this.topics = ProblemServiceHelper.convertToIntArray(p.getTopics());
		this.companies = ProblemServiceHelper.convertToIntArray(p.getCompanies());
		this.tags = ProblemServiceHelper.convertToIntArray(p.getTags());
		this.familiarity = p.getFamiliarity();
		this.solution = new String(Base64.getDecoder().decode(p.getSolution()));
		this.description = new String(Base64.getDecoder().decode(p.getDescription()));
	}	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public int[] getTopics() {
		return topics;
	}

	public void setTopics(int[] topics) {
		this.topics = topics;
	}

	public int[] getCompanies() {
		return companies;
	}

	public void setCompanies(int[] companies) {
		this.companies = companies;
	}

	public int[] getTags() {
		return tags;
	}

	public void setTags(int[] tags) {
		this.tags = tags;
	}

	public int getFamiliarity() {
		return familiarity;
	}

	public void setFamiliarity(int familiarity) {
		this.familiarity = familiarity;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}	
}
