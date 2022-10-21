package com.michelle.Kali.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michelle.Kali.models.Cart;
import com.michelle.Kali.models.User;

@Repository
public interface CartRespository extends JpaRepository<Cart, Long>{
	
	
	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
	List<Cart> deleteByUser(User user);
}
