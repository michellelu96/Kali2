package com.michelle.Kali.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.michelle.Kali.dto.product.ProductDto;


@Entity
@Table(name="products")
public class Product {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long Id;
	
	private @NotNull String name;
	private @NotNull String imageURL;
	private @NotNull double price;
	private @NotNull String description;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="category_id", nullable = false)
	Category category;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy="product")
	private List<Cart> carts;
	
	
	public Product(ProductDto productDto, Category category) {
		this.name = productDto.getName();
		this.imageURL = productDto.getImageURL();
		this.description = productDto.getDescription();
		this.price = productDto.getPrice();
		this.category = category;
	}
	
	public Product(String name, String imageURL, double price, String description, Category catergory, Category category) {
		super();
		this.name=name;
		this.imageURL = imageURL;
		this.price = price;
		this.description = description;
		this.category = category;
	}
	
	public Product() {}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	
	
}
