package com.survey.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.survey.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByName(String name);
}
