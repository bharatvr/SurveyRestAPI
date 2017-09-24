package com.survey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.survey.dto.QuestionAnswerDTO;
import com.survey.jparepository.QuestionAnswerRepository;
import com.survey.model.Question;
import com.survey.model.QuestionAnswers;
import com.survey.util.CustomErrorType;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {

	private static final int ZERO_INDEX = 0;
	private static final long QUESTION_TYPE = 1;
	
	@Autowired
	private QuestionAnswerRepository questionAnswerRepository;
	
	
	@Override
	public ResponseEntity<String> saveQuestionAnswer(
			QuestionAnswerDTO qanswerDTO, UriComponentsBuilder ucBuilder) {
		
		if (!questionAnswerRepository.findByAnswerANDQuestion(qanswerDTO.getQuestionAnswer(),qanswerDTO.getQuestionId()).isEmpty()) {

			return new ResponseEntity(new CustomErrorType(
					"Unable to create. A Answer for this Question with name " + qanswerDTO.getQuestionId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		
		Question question=new Question();
		question.setId(qanswerDTO.getQuestionId());
		List<QuestionAnswers> result=questionAnswerRepository.findByQuestion(question);
		long questionType;
		if(!result.isEmpty()){
			 questionType=result.get(ZERO_INDEX).getQuestion().getQuestionTypes().getId();
			if(questionType!=QUESTION_TYPE){
				
				return new ResponseEntity(new CustomErrorType(
						"Unable to create. The Question with name type  " + questionType + " is not allowed for multiple answer."),
						HttpStatus.CONFLICT);
			}
			
			
		}
		
		Question questionId=new Question();
		questionId.setId(qanswerDTO.getQuestionId());
		
		QuestionAnswers questionAnswers=new QuestionAnswers();
		
		questionAnswers.setQuestionAnswer(qanswerDTO.getQuestionAnswer());
		questionAnswers.setQuestion(questionId);
		
		
		
		questionAnswerRepository.save(questionAnswers);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/answer/{id}")
				.buildAndExpand(questionAnswers.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		
		
		
	}


	@Override
	public ResponseEntity<QuestionAnswerDTO> updateQuestionAnswer(
			QuestionAnswerDTO questionAnswerDTO, long answerId) {
		
		QuestionAnswers currentAnswer = questionAnswerRepository.findOne(answerId);

		if (currentAnswer == null) {
			return new ResponseEntity(new CustomErrorType(
					"Unable to upate answer of question with id " + answerId
							+ " not found."), HttpStatus.NOT_FOUND);
		}

		if (!currentAnswer.getQuestionAnswer().equalsIgnoreCase(
				questionAnswerDTO.getQuestionAnswer())
				&& !questionAnswerRepository.findByAnswerANDQuestion(
						questionAnswerDTO.getQuestionAnswer(),
						questionAnswerDTO.getQuestionId()).isEmpty()) {

			return new ResponseEntity(new CustomErrorType(
					"Unable to update. An answer"
							+ questionAnswerDTO.getQuestionAnswer()
							+ " for question "
							+ questionAnswerDTO.getQuestionId()
							+ " already exist."), HttpStatus.CONFLICT);

		}

		currentAnswer.setQuestionAnswer(questionAnswerDTO.getQuestionAnswer());

		questionAnswerRepository.save(currentAnswer);		

		return new ResponseEntity<QuestionAnswerDTO>(questionAnswerDTO, HttpStatus.OK);
		
		
		
	}


	

}
