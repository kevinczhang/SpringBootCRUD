package com.czhang.cpms.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProblemJsonModel {
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
	@JsonIgnore
	String solution;
	@JsonIgnore
	String description;
	
	public ProblemJsonModel(){}

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
