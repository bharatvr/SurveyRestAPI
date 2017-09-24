package com.survey.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import com.survey.dto.CategoryDTO;
import com.survey.model.Category;

/**
 * 
 * @author Bharat
 * 
 */
public interface CategoryService {



public boolean isCategoryExist(Category category);

public ResponseEntity<String> saveCategory(Category category,UriComponentsBuilder ucBuilder);

public ResponseEntity<CategoryDTO> findById(long id);

public ResponseEntity<CategoryDTO> updateCategory(Category category,long catId);

public ResponseEntity<List<CategoryDTO>> findAllCategorys();

public ResponseEntity<String> deleteCategory(long catId);

}
