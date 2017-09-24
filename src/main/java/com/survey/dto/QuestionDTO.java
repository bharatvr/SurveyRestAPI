package com.survey.dto;

public class QuestionDTO {

	private long questionId;
	private long categoryId;
	private long questionTypeId;
	private String question;
	private String questionDescription;
	
	
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}
	public long getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(long questionTypeId) {
		this.questionTypeId = questionTypeId;
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
	
	
	
	
}
