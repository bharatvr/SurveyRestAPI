package com.survey.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "survey_category_questions")
public class Question {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    @Column(name = "question_id")
	    private Long id;
	    
	    @Column(name = "question")
	    private String question;
	    
	    @Column(name = "question_description")
	    private String questionDescription;
	    
	    @Column(name = "created_user")
		private String createdUser;
		
		@Column(name = "created_date")
		private Date createdDate;
		
		@Column(name = "updated_user")
		private String updatedUser;
		
		@Column(name = "updated_date")
		private Date updatedDate;
		
		@ManyToOne
	    @JoinColumn(name = "category_id")
	    private Category category;
		
		@ManyToOne
	    @JoinColumn(name = "question_type_id")
	    private QuestionTypes questionTypes;
		
	   
		 @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
		 private Set<QuestionAnswers> questionAnswers;
		

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getQuestionDescription() {
			return questionDescription;
		}

		public void setQuestionDescription(String questionDescription) {
			this.questionDescription = questionDescription;
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

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public QuestionTypes getQuestionTypes() {
			return questionTypes;
		}

		public void setQuestionTypes(QuestionTypes questionTypes) {
			this.questionTypes = questionTypes;
		}

		public Set<QuestionAnswers> getQuestionAnswers() {
			return questionAnswers;
		}

		public void setQuestionAnswers(Set<QuestionAnswers> questionAnswers) {
			this.questionAnswers = questionAnswers;
		}
		
		
		

}
