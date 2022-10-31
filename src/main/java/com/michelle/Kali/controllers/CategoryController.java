package com.michelle.Kali.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelle.Kali.common.ApiResponse;
import com.michelle.Kali.models.Category;
import com.michelle.Kali.services.CategoryService;
import com.michelle.Kali.utils.Helper;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public ResponseEntity<List<Category>> getCategories(){
		List <Category> body = categoryService.listCategories();
		return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category){
		if(Helper.notNull(categoryService.readCategory(category.getCategoryName()))) return new ResponseEntity<ApiResponse>(new ApiResponse(false,"Category already exists"), HttpStatus.CONFLICT);
		categoryService.createCategory(category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Category created"), HttpStatus.CREATED);
	}
	
	@PostMapping("/update/{categoryId}")
	public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Long categoryId, @Valid @RequestBody Category category){
		if(Helper.notNull(categoryService.readCategory(categoryId))) {
			categoryService.updateCategory(categoryId, category);
			return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Category updated"), HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(false,"Category doesn't exist"), HttpStatus.NOT_FOUND);
	}
}
