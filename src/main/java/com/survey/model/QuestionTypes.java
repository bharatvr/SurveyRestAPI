package com.survey.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "survey_question_type")
public class QuestionTypes {

	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_type_id")
    private long id;
    
    @Column(name = "question")
    private String questionType;   
    
    @Column(name = "created_user")
	private String createdUser;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "updated_user")
	private String updatedUser;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	
	 @OneToMany(mappedBy = "questionTypes", cascade = CascadeType.ALL)
	 private Set<Question> question;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getQuestionType() {
		return questionType;
	}


	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}


	public String getCreatedUser() {
		return createdUser;
	}


	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public String getUpdatedUser() {
		return updatedUser;
	}


	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public Set<Question> getQuestion() {
		return question;
	}


	public void setQuestion(Set<Question> question) {
		this.question = question;
	}
	 
	 
	 
	 
	
}
