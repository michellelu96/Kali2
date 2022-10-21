package com.michelle.Kali.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelle.Kali.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();
    
    List<Role> findByName(String name);
}
