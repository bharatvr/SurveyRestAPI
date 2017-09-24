package com.survey.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.survey.dto.QuestionAnswerDTO;

public interface QuestionAnswerService {

	public ResponseEntity<String> saveQuestionAnswer(QuestionAnswerDTO qanswerDTO,	UriComponentsBuilder ucBuilder);

	
	public ResponseEntity<QuestionAnswerDTO> updateQuestionAnswer( QuestionAnswerDTO questionAnswerDTO, long id);

}
