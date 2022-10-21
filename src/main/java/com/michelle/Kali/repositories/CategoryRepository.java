package com.michelle.Kali.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michelle.Kali.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
		Category findByCategoryName(String categoryName);

	
		
}
