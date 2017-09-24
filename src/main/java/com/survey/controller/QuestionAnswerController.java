package com.survey.controller;

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

import com.survey.dto.QuestionAnswerDTO;
import com.survey.service.QuestionAnswerService;

/**
 * 
 * @author Bharat
 * 
 */
@RestController
@RequestMapping(value = "/question")
public class QuestionAnswerController {

	public static final Logger logger = LoggerFactory
			.getLogger(QuestionController.class);

	@Autowired
	QuestionAnswerService questionAnswerService;

	@RequestMapping(value = "/answer", method = RequestMethod.POST)
	public ResponseEntity<String> createQuestionAnswer(
			@RequestBody QuestionAnswerDTO qanswerDTO,
			UriComponentsBuilder ucBuilder) {

		return questionAnswerService.saveQuestionAnswer(qanswerDTO, ucBuilder);

	}

	@RequestMapping(value = "/answer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<QuestionAnswerDTO> updateQuestionAnswer(
			@PathVariable("id") long id,
			@RequestBody QuestionAnswerDTO QuestionAnswerDTO) {
		logger.info("Updating question answers with id {}", id);

		return questionAnswerService
				.updateQuestionAnswer(QuestionAnswerDTO, id);

	}

}
