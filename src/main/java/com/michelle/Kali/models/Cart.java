package com.michelle.Kali.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="cart")
public class Cart {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @ManyToOne
    @JoinColumn(nullable= false, referencedColumnName= "id")
    private Product product;
    
    @JsonIgnore
    @OneToOne(targetEntity= User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name ="user_id")
    private User user;
    
    private int quantity;
    
    public Cart() {
    	
    }
    
    public Cart(Product product, int quantity, User user) {
    	this.user = user;
    	this.product = product;
    	this.quantity = quantity;
    	this.createdDate = new Date();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
    
    

}
