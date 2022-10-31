package com.michelle.Kali.controllers;

import java.util.List;
import java.util.Optional;

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
import com.michelle.Kali.dto.product.ProductDto;
import com.michelle.Kali.models.Category;
import com.michelle.Kali.services.CategoryService;
import com.michelle.Kali.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getProducts(){
		List<ProductDto> body = productService.listProducts();
		return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductDto productDto){
		Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category is invalid"), HttpStatus.CONFLICT);
		}
		Category category = optionalCategory.get();
		productService.addProduct(productDto, category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product has been added"), HttpStatus.CREATED);
	}
	
	@PostMapping("/update/{productId}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Long productId, @RequestBody @Valid ProductDto productDto){
		Optional<Category> optionalCategory = categoryService.readCategory(productDto.getCategoryId());
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<ApiResponse>(new ApiResponse(false, "Category is invalid"), HttpStatus.CONFLICT);
		}
		Category category = optionalCategory.get();
		productService.updateProduct(productId, productDto, category);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product has been updated"), HttpStatus.OK);
	}
	
}
