package com.michelle.Kali.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "category_name")
	private @NotBlank String categoryName;
	private @NotBlank String description;
	private @NotBlank String imageURL;
	
	@OneToMany(mappedBy="category", fetch= FetchType.LAZY, cascade = CascadeType.ALL)
	Set<Product> products;
	
	public Category() {}
	
	public Category(@NotBlank String categoryName,@NotBlank String description) {
		this.categoryName = categoryName;
		this.description = description;
	}
	
	public Category(@NotBlank String categoryName, @NotBlank String description, @NotBlank String imageURL) {
		this.categoryName = categoryName;
		this.description = description;
		this.imageURL = imageURL;
	}
	
	@Override
	public String toString() {
		return "User {category id=" + id + ", category name='" + categoryName + "', description='" + description + "'}";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	
}
