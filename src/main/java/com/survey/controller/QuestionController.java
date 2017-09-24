package com.survey.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.survey.dto.QuestionDTO;
import com.survey.service.QuestionService;

/**
 * 
 * @author Bharat
 * 
 */
@RestController
public class QuestionController {

	public static final Logger logger = LoggerFactory
			.getLogger(QuestionController.class);

	@Autowired
	QuestionService questionService;

	
	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ResponseEntity<List<QuestionDTO>> listAllQuestions() {

		return questionService.findAllQuestions();

	}

	@RequestMapping(value = "/question/{id}", method = RequestMethod.GET)
	public ResponseEntity<QuestionDTO> getQuestion(@PathVariable("id") long id) {
		logger.info("Fetching category with {}", id);

		return questionService.findById(id);

	}
	
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public ResponseEntity<String> createQuestion(
			@RequestBody QuestionDTO questionDTO, UriComponentsBuilder ucBuilder) {

		return questionService.saveQuestion(questionDTO, ucBuilder);

	}
	
	@RequestMapping(value = "/question/{id}", method = RequestMethod.PUT)
	public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable("id") long id,
			@RequestBody QuestionDTO questionDTO) {
		logger.info("Updating category with id {}", id);

		return questionService.updateQuestion(questionDTO,id);

	}

	@RequestMapping(value = "/question/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteQuestion(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting category with id {}", id);

		return questionService.deleteQuestion(id);

	}

}
