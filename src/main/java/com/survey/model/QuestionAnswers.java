package com.survey.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "survey_question_answers")
public class QuestionAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_id")
	private long id;
	
	    @Column(name = "question_answer")
	    private String questionAnswer;   
	    
	    @Column(name = "created_user")
		private String createdUser;
		
		@Column(name = "created_date")
		private Date createdDate;
		
		@Column(name = "updated_user")
		private String updatedUser;
		
		@Column(name = "updated_date")
		private Date updatedDate;
		
		@ManyToOne
	    @JoinColumn(name = "question_id")
	    private Question question;
		 

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getQuestionAnswer() {
			return questionAnswer;
		}

		public void setQuestionAnswer(String questionAnswer) {
			this.questionAnswer = questionAnswer;
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

		public Question getQuestion() {
			return question;
		}

		public void setQuestion(Question question) {
			this.question = question;
		}
		 

		
}
