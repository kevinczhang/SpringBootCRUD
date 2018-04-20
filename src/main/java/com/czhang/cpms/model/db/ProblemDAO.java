package com.czhang.cpms.model.db;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.czhang.cpms.model.domain.Problem;
import com.czhang.cpms.util.Constants;

@Entity
@Table(name = "Problem")
public class ProblemDAO {
	
	@Id	
	String id;
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
	int familiarity = 0;
	
	@Column(name = "createdTime", columnDefinition="TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdTime;
	
	@Column(name = "updatedTime", columnDefinition="TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTime;
	
	/**
     * Roles are being eagerly loaded here because
     * they are a fairly small collection of items for this example.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_problem", joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "problem_id", referencedColumnName = "id"))
    private List<UserDAO> users;
    
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "problem")
    private Set<SolutionDAO> solutions;
    
    public ProblemDAO() {
    	this.users = new ArrayList<>();
    	this.solutions = new HashSet<>();
    }

	public ProblemDAO(Problem problem) {
		this();
		this.id = (problem.getId() != null) ? problem.getId() : UUID.randomUUID().toString();
		this.source = Constants.sources.get(problem.getSource()).name();
		this.number = problem.getNumber();
		this.type = Constants.types.get(problem.getType()).name();
		this.title = problem.getTitle();
		this.difficulty = Constants.levels.get(problem.getDifficulty()).name();
		this.topics = Arrays.toString(problem.getTopics()).replaceAll("[\\[\\]\"]", "");
		this.companies = Arrays.toString(problem.getCompanies()).replaceAll("[\\[\\]\"]", "");
		this.tags = Arrays.toString(problem.getTags()).replaceAll("[\\[\\]\"]", "");
		this.familiarity = problem.getFamiliarity();
		this.description = Base64.getEncoder().encode(problem.getDescription().getBytes());
		this.solution = Base64.getEncoder().encode(problem.getSolution().getBytes());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	public int getFamiliarity() {
		return familiarity;
	}

	public void setFamiliarity(int familiarity) {
		this.familiarity = familiarity;
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

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public List<UserDAO> getUsers() {
		return users;
	}

	public void setUsers(List<UserDAO> users) {
		this.users = users;
	}

	public Set<SolutionDAO> getSolutions() {
		return solutions;
	}

	public void setSolutions(Set<SolutionDAO> solutions) {
		this.solutions = solutions;
	}	
}
