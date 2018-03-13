package com.czhang.cpms.model.domain;

import java.util.Base64;
import java.util.List;

import com.czhang.cpms.model.db.ProblemDAO;
import com.czhang.cpms.util.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Problem {
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
	@JsonProperty("solution")
	String solution;
	@JsonProperty("description")
	String description;
	
	public Problem(){}

	public Problem(ProblemDAO p) {
		this.source = getIndex(Constants.sources, p.getSource());
		this.number = p.getNumber();
		this.type = getIndex(Constants.types, p.getType());
		this.title = p.getTitle();
		this.difficulty = getIndex(Constants.levels, p.getDifficulty());
		this.topics = convertToIntArray(p.getTopics());
		this.companies = convertToIntArray(p.getCompanies());
		this.tags = convertToIntArray(p.getTags());
		this.solution = new String(Base64.getDecoder().decode(p.getSolution()));
		this.description = new String(Base64.getDecoder().decode(p.getDescription()));
	}

	private <E extends Enum<E>> int getIndex(List<E> enums, String str) {
		for(E e : enums){
			if(e.name().equals(str))
				return enums.indexOf(e);
		}
		return -1;
	}

	private int[] convertToIntArray(String str) {
		if(str == null || str.length() == 0)
			return new int[]{};
		String[] strArray = str.split(", ");
		int[] res = new int[strArray.length];
		for(int i = 0; i < strArray.length; i++){
			res[i] = Integer.parseInt(strArray[i]);
		}
		return res;
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
