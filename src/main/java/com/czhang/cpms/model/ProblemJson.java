package com.czhang.cpms.model;

import java.util.Base64;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProblemJson {
	@JsonProperty("ID")
	Long ID;
	@JsonProperty("NUMBER")
	Long NUMBER;
	@JsonProperty("TITLE")
	String TITLE;
	@JsonProperty("DIFFICULTY")
	String DIFFICULTY;
	@JsonProperty("DESCRIPTION")
	String DESCRIPTION;
	@JsonProperty("SOLUTION")
	String SOLUTION;
	@JsonProperty("TAGS")
	String TAGS;
	@JsonProperty("COMPANIES")
	String COMPANIES;
	@JsonProperty("SPECIALTAGS")
	String SPECIALTAGS;
	
	public ProblemJson(){}
	
//	byte[] encodedBytes = Base64.getEncoder().encode(problems[6].getSOLUTION().getBytes());
//	System.out.println("encodedBytes " + new String(encodedBytes));
//	byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
//	System.out.println("decodedBytes " + new String(decodedBytes));
	
	public ProblemJson(Problem problem){
		this.ID = problem.getId();
		this.NUMBER = problem.getNumber();
		this.TITLE = problem.getName();
		this.DIFFICULTY = problem.getDifficulty();
		this.DESCRIPTION = new String(Base64.getDecoder().decode(problem.getDescription()));
		this.SOLUTION = new String(Base64.getDecoder().decode(problem.getSolution()));
		this.TAGS = problem.getTags();
		this.COMPANIES = problem.getCompanies();
		this.SPECIALTAGS = problem.getSpecialtags();
	}
	@JsonProperty("ID")
	public Long getID() {
		return ID;
	}
	@JsonProperty("id")
	public void setID(Long id) {
		ID = id;
	}
	@JsonProperty("NUMBER")
	public Long getNUMBER() {
		return NUMBER;
	}
	@JsonProperty("number")
	public void setNUMBER(Long number) {
		NUMBER = number;
	}
	@JsonProperty("TITLE")
	public String getTITLE() {
		return TITLE;
	}
	@JsonProperty("title")
	public void setTITLE(String title) {
		this.TITLE = title;
	}
	@JsonProperty("DIFFICULTY")
	public String getDIFFICULTY() {
		return DIFFICULTY;
	}
	@JsonProperty("difficulty")
	public void setDIFFICULTY(String difficulty) {
		DIFFICULTY = difficulty;
	}
	@JsonProperty("DESCRIPTION")
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	@JsonProperty("description")
	public void setDESCRIPTION(String description) {
		DESCRIPTION = description;
	}
	@JsonProperty("SOLUTION")
	public String getSOLUTION() {
		return SOLUTION;
	}
	@JsonProperty("solution")
	public void setSOLUTION(String solution) {
		SOLUTION = solution;
	}
	@JsonProperty("TAGS")
	public String getTAGS() {
		return TAGS;
	}
	@JsonProperty("tags")
	public void setTAGS(String tags) {
		TAGS = tags;
	}
	@JsonProperty("COMPANIES")
	public String getCOMPANIES() {
		return COMPANIES;
	}
	@JsonProperty("companies")
	public void setCOMPANIES(String companies) {
		COMPANIES = companies;
	}
	@JsonProperty("SPECIALTAGS")
	public String getSPECIALTAGS() {
		return SPECIALTAGS;
	}
	@JsonProperty("specialtags")
	public void setSPECIALTAGS(String specialtags) {
		SPECIALTAGS = specialtags;
	}	
}
