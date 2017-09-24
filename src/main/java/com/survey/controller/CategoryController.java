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

import com.survey.dto.CategoryDTO;
import com.survey.model.Category;
import com.survey.service.CategoryService;

/**
 * 
 * @author Bharat
 * 
 */
@RestController
public class CategoryController {

	public static final Logger logger = LoggerFactory
			.getLogger(CategoryController.class);

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> listAllcategorys() {

		return categoryService.findAllCategorys();

	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable("id") long id) {
		logger.info("Fetching category with id {}", id);

		return categoryService.findById(id);

	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ResponseEntity<String> createCategory(
			@RequestBody Category category, UriComponentsBuilder ucBuilder) {

		return categoryService.saveCategory(category, ucBuilder);

	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") long id,
			@RequestBody Category category) {
		logger.info("Updating category with id {}", id);

		return categoryService.updateCategory(category,id);

	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteCategory(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting category with id {}", id);

		return categoryService.deleteCategory(id);

	}

}