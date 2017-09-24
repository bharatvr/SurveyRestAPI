package com.survey.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.survey.dto.QuestionDTO;

public interface QuestionService {

	public ResponseEntity<String> saveQuestion(QuestionDTO questionDTO,UriComponentsBuilder ucBuilder);

	public ResponseEntity<QuestionDTO> findById(long id);

	public ResponseEntity<List<QuestionDTO>> findAllQuestions();

	public ResponseEntity<QuestionDTO> updateQuestion(QuestionDTO questionDTO,	long id);

	public ResponseEntity<String> deleteQuestion(long id);

	
	
	
}
