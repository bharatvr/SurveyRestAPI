package com.survey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.survey.dto.QuestionDTO;
import com.survey.jparepository.QuestionRepository;
import com.survey.model.Category;
import com.survey.model.Question;
import com.survey.model.QuestionTypes;
import com.survey.util.CustomErrorType;

/**
 * 
 * @author Bharat
 * 
 */
@Service
public class QuestionServiceImpl  implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	
	
	@Override
	public ResponseEntity<String> saveQuestion(QuestionDTO questionDTO,UriComponentsBuilder ucBuilder) {
		
		
		if (!questionRepository.findByQuestionANDCatId(questionDTO.getQuestion(),questionDTO.getCategoryId()).isEmpty()) {

			return new ResponseEntity(new CustomErrorType(
					"Unable to create. A question with name " + questionDTO.getQuestion() + " already exist."),
					HttpStatus.CONFLICT);
		}

		Category category=new Category();		
		category.setId(questionDTO.getCategoryId());
		
		QuestionTypes questionTypes=new QuestionTypes();		
		questionTypes.setId(questionDTO.getQuestionTypeId());
		
		
		Question question=new Question();
		question.setQuestion(questionDTO.getQuestion());
		question.setQuestionDescription((questionDTO.getQuestionDescription()));
		question.setCategory(category);
		question.setQuestionTypes(questionTypes);
		
		questionRepository.save(question);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/question/{id}")
				.buildAndExpand(question.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		
		
		
	}



	@Override
	public ResponseEntity<QuestionDTO> findById(long id) {

		Question question = questionRepository.findOne(id);

		if (question != null) {
			QuestionDTO questionDTO = new QuestionDTO();

			questionDTO.setQuestionId(question.getId());
			questionDTO.setQuestion(question.getQuestion());
			questionDTO.setQuestionDescription(question
					.getQuestionDescription());
			questionDTO.setCategoryId(question.getCategory().getId());
			questionDTO.setQuestionTypeId(question.getQuestionTypes().getId());

			return new ResponseEntity<QuestionDTO>(questionDTO, HttpStatus.OK);

		}

		else {
			return new ResponseEntity(new CustomErrorType("Question with id "
					+ id + " not found"), HttpStatus.NOT_FOUND);
		}

	}



	@Override
	public ResponseEntity<List<QuestionDTO>> findAllQuestions() {

		List<Question> questionList = questionRepository.findAll();
      if (questionList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		}
		List<QuestionDTO> result = new ArrayList<>();

		for (Question question : questionList) {
			QuestionDTO questionDTO = new QuestionDTO();

			questionDTO.setQuestionId(question.getId());
			questionDTO.setQuestion(question.getQuestion());
			questionDTO.setQuestionDescription(question
					.getQuestionDescription());
			questionDTO.setCategoryId(question.getCategory().getId());
			questionDTO.setQuestionTypeId(question.getQuestionTypes().getId());

			result.add(questionDTO);
		}

		return new ResponseEntity<List<QuestionDTO>>(result, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<QuestionDTO> updateQuestion(QuestionDTO questionDTO,
			long questionId) {

		Question currentQuestion = questionRepository.findOne(questionId);

		if (currentQuestion == null) {
			return new ResponseEntity(new CustomErrorType(
					"Unable to upate question with id " + questionId
							+ " not found."), HttpStatus.NOT_FOUND);
		}

		if (!currentQuestion.getQuestion().equalsIgnoreCase(
				questionDTO.getQuestion())
				&& !questionRepository.findByQuestionANDCatId(
						questionDTO.getQuestion(),
						currentQuestion.getCategory().getId()).isEmpty()) {

			return new ResponseEntity(new CustomErrorType(
					"Unable to update. A question with name "
							+ questionDTO.getQuestion() + " already exist."),
					HttpStatus.CONFLICT);

		}

		currentQuestion.getQuestionTypes().setId(
				questionDTO.getQuestionTypeId());
		currentQuestion.setQuestion(questionDTO.getQuestion());
		currentQuestion.setQuestionDescription(questionDTO
				.getQuestionDescription());

		questionRepository.saveAndFlush(currentQuestion);

		questionDTO.setQuestionId(currentQuestion.getId());

		return new ResponseEntity<QuestionDTO>(questionDTO, HttpStatus.OK);
	}



	@Override
	public ResponseEntity<String> deleteQuestion(long questionId) {

		Question question = questionRepository.findOne(questionId);

		if (question == null) {
			return new ResponseEntity(new CustomErrorType(
					"Unable to delete. question with id " + questionId
							+ " not found."), HttpStatus.NOT_FOUND);
		}

		questionRepository.delete(question);

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
	}
	

}
