package com.michelle.Kali.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michelle.Kali.models.Category;
import com.michelle.Kali.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryService {
	private final CategoryRepository categoryRepo;
	
	public CategoryService(CategoryRepository categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
	
	public List<Category> listCategories(){
		return categoryRepo.findAll();
	}
	
	public void createCategory(Category category) {
		categoryRepo.save(category);
	}
	
	public Category readCategory(String categoryName) {
		return categoryRepo.findByCategoryName(categoryName);
	}
	
	public Optional<Category> readCategory(Long categoryId){
		return categoryRepo.findById(categoryId);
	}
	
	public void updateCategory(Long categoryId, Category newCategory) {
		categoryRepo.findById(categoryId)
		.ifPresent(category->{
			category.setCategoryName(newCategory.getCategoryName());
			category.setDescription(newCategory.getDescription());
			category.setImageURL(newCategory.getImageURL());
			category.setProducts(newCategory.getProducts());
			categoryRepo.save(category);
		});
	}
}
