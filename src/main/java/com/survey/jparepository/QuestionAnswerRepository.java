package com.survey.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.survey.model.Question;
import com.survey.model.QuestionAnswers;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswers, Long> {

	@Query("select qas.id from QuestionAnswers qas where qas.questionAnswer = :questionAnswer and qas.question.id = :questionId")
	public  List<Long> findByAnswerANDQuestion(@Param("questionAnswer") String questionAnswer, @Param("questionId") long questionId);

	public  List<QuestionAnswers> findByQuestion(Question question);

}
