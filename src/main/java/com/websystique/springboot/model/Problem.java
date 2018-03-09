package com.websystique.springboot.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Entity
public class Problem {
	@Id	
	Long id;
	@Column(name="leetcode_number")
	Long number;
	String name;
	String difficulty;
	@Lob
	@Column(length=100000)
	byte[] description;
	@Lob
	@Column(length=100000)
	byte[] solution;
	String tags;
	String companies;
	String specialtags;
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCompanies() {
		return companies;
	}

	public void setCompanies(String companies) {
		this.companies = companies;
	}

	public String getSpecialtags() {
		return specialtags;
	}

	public void setSpecialtags(String specialtags) {
		this.specialtags = specialtags;
	}

	public Problem(){}
	
	public Problem(Long number, String name, String difficulty, byte[] description,
			byte[] solution, String tags, String companies, String specialtags){
		this.number = number;
		this.name = name;
		this.difficulty = difficulty;
		this.description = description;
		this.solution = solution;
		this.tags = tags;
		this.companies = companies;
		this.specialtags = specialtags;
	}

//	byte[] encodedBytes = Base64.getEncoder().encode(problems[6].getSOLUTION().getBytes());
//	System.out.println("encodedBytes " + new String(encodedBytes));
//	byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
//	System.out.println("decodedBytes " + new String(decodedBytes));
	
	public Problem(ProblemJson problem) {
		this.id = problem.getID();
		this.number = problem.getNUMBER();
		this.name = problem.getTITLE();
		this.difficulty = problem.getDIFFICULTY();
		this.description = Base64.getEncoder().encode(problem.getDESCRIPTION().getBytes());
		this.solution = Base64.getEncoder().encode(problem.getSOLUTION().getBytes());
		this.tags = problem.getTAGS();
		this.companies = problem.getCOMPANIES();
		this.specialtags = problem.getSPECIALTAGS();
	}

}
