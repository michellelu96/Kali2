package com.michelle.Kali.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="orderItems")
public class OrderItem {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name="quantity")
    private @NotNull int quantity;
    
    @Column(name="price")
    private @NotNull double price;
    
    @Column(name="created_date")
    private Date createdDate;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="order_id", referencedColumnName = "id")
    private Order order;
    
    @OneToOne
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;
    
    public OrderItem() {}
    
    public OrderItem(Order order, @NotNull Product product, @NotNull int quantity, @NotNull double price) {
    	this.product = product;
    	this.order = order;
    	this.quantity = quantity;
    	this.price = price;
    	this.createdDate = new Date();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    
    
}
