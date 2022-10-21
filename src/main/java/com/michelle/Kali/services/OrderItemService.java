package com.michelle.Kali.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michelle.Kali.models.OrderItem;
import com.michelle.Kali.repositories.OrderItemRepository;

@Service
@Transactional
public class OrderItemService {
	
	@Autowired
	private OrderItemRepository orderItemRepo;
	
	public void addOrderedProducts(OrderItem orderItem) {
		orderItemRepo.save(orderItem);
	}
}
