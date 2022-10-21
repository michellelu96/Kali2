package com.michelle.Kali.dto.product;

import javax.validation.constraints.NotNull;

import com.michelle.Kali.models.Product;

public class ProductDto {
	private Long id;
	private @NotNull String name;
	private @NotNull String imageURL;
	private @NotNull double price;
	private @NotNull String description;
	private @NotNull Long categoryId;
	
	public ProductDto(Product product) {
		this.setId(product.getId());
		this.setName(product.getName());
		this.setImageURL(product.getImageURL());
		this.setDescription(product.getDescription());
		this.setPrice(product.getPrice());
		this.setCategoryId(product.getCategory().getId());
	}
	
	public ProductDto(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
