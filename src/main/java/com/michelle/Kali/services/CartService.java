package com.michelle.Kali.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.michelle.Kali.dto.cart.AddToCartDto;
import com.michelle.Kali.dto.cart.CartDto;
import com.michelle.Kali.dto.cart.CartItemDto;
import com.michelle.Kali.exceptions.CartItemNotExistException;
import com.michelle.Kali.models.Cart;
import com.michelle.Kali.models.Product;
import com.michelle.Kali.models.User;
import com.michelle.Kali.repositories.CartRespository;

@Service
@Transactional
public class CartService {
	@Autowired
	private CartRespository cartRepo;
	
	public CartService() {}
	
	public CartService(CartRespository cartRepo) {
		this.cartRepo = cartRepo;
	}
	
	public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
		Cart cart = new Cart(product, addToCartDto.getQuantity(), user);
		cartRepo.save(cart);
	}
	
	public CartDto listCartItems(User user) {
		List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDto> cartItems = new ArrayList<>();
		
		for(Cart cart:cartList) {
			CartItemDto  cartItemsDto = getDtoFromCart(cart);
			cartItems.add(cartItemsDto);
		}
		double totalCost = 0;
		for(CartItemDto cartItemDto: cartItems) {
			totalCost += (cartItemDto.getProduct().getPrice()* cartItemDto.getQuantity());
		}
		return new CartDto(cartItems,totalCost);
		}

	private CartItemDto getDtoFromCart(Cart cart) {
		return new CartItemDto(cart);
	}
	
	public void updateCartItem(AddToCartDto cartDto, User user, Product product) {
		cartRepo.findById(cartDto.getId())
			.ifPresent(cart ->{
				cart.setQuantity(cartDto.getQuantity());
				cart.setCreatedDate(new Date());
				cartRepo.save(cart);
			});
		
	}
	
	public void deleteCartItem(Long id, Long userId) throws CartItemNotExistException{
		if(!cartRepo.existsById(id)) throw new CartItemNotExistException("Cart id is invalid : " + id);
		cartRepo.deleteById(id);
	}
	
	public void deleteCartItems(Long userId) {
		cartRepo.deleteAll();
	}
	
	public void deleteUserCartItems(User user) {
		cartRepo.deleteByUser(user);
	}
	
}
