package com.survey.dto;


public class QuestionAnswerDTO {

	private long answerId;
	private long questionId;
	private String questionAnswer;
	public long getAnswerId() {
		return answerId;
	}
	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}
	public long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	
	
}
