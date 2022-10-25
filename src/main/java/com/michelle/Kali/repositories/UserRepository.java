package com.michelle.Kali.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.michelle.Kali.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	List<User> findAll();
	Optional<User> findByEmail(String email);
	User findUserByEmail(String email);
}