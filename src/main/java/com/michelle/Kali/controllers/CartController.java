package com.michelle.Kali.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.michelle.Kali.common.ApiResponse;
import com.michelle.Kali.dto.cart.AddToCartDto;
import com.michelle.Kali.dto.cart.CartDto;
import com.michelle.Kali.exceptions.CartItemNotExistException;
import com.michelle.Kali.exceptions.ProductNotExistException;
import com.michelle.Kali.models.Product;
import com.michelle.Kali.models.User;
import com.michelle.Kali.services.CartService;
import com.michelle.Kali.services.ProductService;
import com.michelle.Kali.services.UserService;

@RestController
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/cart/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto, @RequestParam("Id") Long userId) throws ProductNotExistException{
		User user = userService.getUser(userId);
		Product product = productService.getProductById(addToCartDto.getProductId());
		System.out.println("Product to add" + product.getName());
		cartService.addToCart(addToCartDto, product, user);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Added to cart"), HttpStatus.CREATED);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("id") Long userId){
		User user = userService.getUser(userId);
		CartDto cartDto = cartService.listCartItems(user);
		return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
	}
	
	@PutMapping("/cart/update/{cartItemId}")
	public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @ Valid AddToCartDto cartDto, @RequestParam("id") Long userId) throws ProductNotExistException{
		User user = userService.getUser(userId);
		Product product = productService.getProductById(cartDto.getId());
		cartService.updateCartItem(cartDto, user, product);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Product has been updated"), HttpStatus.OK);
	}
	
	@DeleteMapping("/cart/delete/{cartItemId")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Long itemId, @RequestParam("id") Long userId) throws CartItemNotExistException{
		cartService.deleteCartItem(itemId, userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true,"Item has been removed"), HttpStatus.OK);
	}
}
