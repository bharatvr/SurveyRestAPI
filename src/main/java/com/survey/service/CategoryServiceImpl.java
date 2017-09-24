package com.survey.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.survey.dto.CategoryDTO;
import com.survey.jparepository.CategoryRepository;
import com.survey.model.Category;
import com.survey.util.CustomErrorType;

/**
 * 
 * @author Bharat
 * 
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	public static final Logger logger = LoggerFactory
			.getLogger(CategoryServiceImpl.class);
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public boolean isCategoryExist(Category category) {

		List<Category> result = categoryRepository.findByName(category
				.getName());

		return !result.isEmpty() ? true : false;

	}

	@Override
	public ResponseEntity<String> saveCategory(Category category,
			UriComponentsBuilder ucBuilder) {

		List<Category> result = categoryRepository.findByName(category
				.getName());

		if (!categoryRepository.findByName(category.getName()).isEmpty()) {

			return new ResponseEntity(new CustomErrorType(
					"Unable to create. A category with name "
							+ category.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}

		categoryRepository.saveAndFlush(category);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/category/{id}")
				.buildAndExpand(category.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<CategoryDTO> findById(long id) {
		CategoryDTO categoryDTO = null;

		Category category = categoryRepository.findOne(id);

		if (category != null) {
			categoryDTO = new CategoryDTO();
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			categoryDTO.setCategoryDescription(category
					.getCategoryDescription());
			categoryDTO.setCreatedUser(category.getCreatedUser());
			return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
		}

		else {
			return new ResponseEntity(new CustomErrorType("category with id "
					+ id + " not found"), HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<CategoryDTO> updateCategory(Category category, long catId) {

		Category currentCategory = categoryRepository.findOne(catId);

		if (currentCategory == null) {
			return new ResponseEntity(new CustomErrorType(
					"Unable to upate. category with id " + category.getId()
							+ " not found."), HttpStatus.NOT_FOUND);
		}

		if (!currentCategory.getName().equalsIgnoreCase(category.getName())
				&& !categoryRepository.findByName(category.getName()).isEmpty()) {

			return new ResponseEntity(new CustomErrorType(
					"Unable to update. A category with name " + catId
							+ " already exist."), HttpStatus.CONFLICT);

		}
		currentCategory.setName(category.getName());
		currentCategory.setCategoryDescription(category
				.getCategoryDescription());

		categoryRepository.saveAndFlush(currentCategory);

	       
		   CategoryDTO categoryDTO = new CategoryDTO();
			
	        categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			categoryDTO.setCategoryDescription(category
					.getCategoryDescription());
			categoryDTO.setCreatedUser(category.getCreatedUser());
			
		logger.info("question on current category : "+currentCategory.getQuestion());
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);

	}

	@Override
	public ResponseEntity<List<CategoryDTO>> findAllCategorys() {
		List<Category> categorys = categoryRepository.findAll();

		if (categorys.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);

		}
		List<CategoryDTO> result = new ArrayList<>();

		for (Category cat : categorys) {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(cat.getId());
			categoryDTO.setName(cat.getName());
			categoryDTO.setCategoryDescription(cat.getCategoryDescription());
			categoryDTO.setCreatedUser(cat.getCreatedUser());
			result.add(categoryDTO);
		}
		return new ResponseEntity<List<CategoryDTO>>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteCategory(long catId) {

		Category category = categoryRepository.findOne(catId);

		if (category == null) {
			return new ResponseEntity(new CustomErrorType(
					"Unable to delete. category with id " + catId
							+ " not found."), HttpStatus.NOT_FOUND);
		}

		categoryRepository.delete(category);

		return new ResponseEntity<String>(HttpStatus.NO_CONTENT);

	}

}
