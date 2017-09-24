package com.survey.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.survey.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("select qus.id from Question qus where qus.question = :question and qus.category.id = :catId")
	public  List<Long> findByQuestionANDCatId(@Param("question") String question, @Param("catId") long catId);

	

}
