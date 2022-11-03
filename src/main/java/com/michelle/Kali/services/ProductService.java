package com.michelle.Kali.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelle.Kali.dto.product.ProductDto;
import com.michelle.Kali.exceptions.ProductNotExistException;
import com.michelle.Kali.models.Category;
import com.michelle.Kali.models.Product;
import com.michelle.Kali.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	public List<ProductDto> listProducts(){
		List<Product> products = productRepo.findAll();
		List<ProductDto> productDtos = new ArrayList<>();
		
		for(Product product : products) {
			ProductDto productDto = getDtoFromProduct(product);
			productDtos.add(productDto);
		}
		
		return productDtos;
	}

	public static ProductDto getDtoFromProduct(Product product) {
		ProductDto productDto = new ProductDto(product);
		return productDto;
	}
	
	public static Product getProductFromDto(ProductDto productDto, Category category) {
		Product product = new Product(productDto, category);
		return product;
	}
	
	public void addProduct(ProductDto productDto, Category category) {
		Product product = getProductFromDto(productDto, category);
		productRepo.save(product);
	}
	
	public void updateProduct(Long productId, ProductDto productDto, Category category) {
		Product product = getProductFromDto(productDto, category);
		product.setId(productId);
		productRepo.save(product);
	}
	
	public void deleteProduct(Long productId) {
		productRepo.deleteById(productId);
	}
	
	public Product getProductById(Long productId) throws ProductNotExistException {
		Optional<Product> optionalProduct = productRepo.findById(productId);
		if (!optionalProduct.isPresent()) throw new ProductNotExistException("Product id is invalid " + productId);
		return optionalProduct.get();
	}
	
}
