package com.michelle.Kali.dto.order;

import javax.validation.constraints.NotNull;

import com.michelle.Kali.models.Order;

public class OrderDto {
	private Long id;
	private @NotNull Long userId;
	
	public OrderDto() {}
	
	public OrderDto(Order order) {
		this.setId(order.getId());
	}

	private void setId(Long userId){
		this.userId = userId;
		
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}
	
	
}
