package com.michelle.Kali.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michelle.Kali.models.Order;
import com.michelle.Kali.models.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	void save(Order newOrder);

}
