package com.michelle.Kali.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.michelle.Kali.common.ApiResponse;
import com.michelle.Kali.dto.checkout.CheckoutItemDto;
import com.michelle.Kali.exceptions.OrderNotFoundException;
import com.michelle.Kali.models.Order;
import com.michelle.Kali.models.User;
import com.michelle.Kali.security.services.UserDetailsServiceImpl;
import com.michelle.Kali.services.OrderService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.net.StripeResponse;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	// create session API
	@PostMapping("/create-checkout-session")
	public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList) throws StripeException{
		Session session = orderService.createSession(checkoutItemDtoList);
		StripeResponse stripeResponse = new StripeResponse(0, null, session.getId());
		return new ResponseEntity<StripeResponse>(stripeResponse, HttpStatus.OK);
	}
	
	//place order after checkout
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> placeOrder(@RequestParam("userId") Long userId, @RequestParam("sessionId") String sessionId){
		User user = userService.getUser(userId);
		orderService.placeOrder(user, sessionId);
		return new ResponseEntity<>(new ApiResponse(true,"Order has been placed"), HttpStatus.CREATED);
	}
	
	//list all orders
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrders(@RequestParam("userId") Long userId){
		User user = userService.getUser(userId);
		List<Order> orderDtoList = orderService.listOrders(user);
		return new ResponseEntity<>(orderDtoList, HttpStatus.OK);
	}
	
	// get items for an order
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOrderById(@PathVariable("id") Long id, @RequestParam("userId") Long userId){
		try {
			Order order = orderService.getOrder(id);
			return new ResponseEntity<>(order, HttpStatus.OK);
		}
		catch(OrderNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
}
