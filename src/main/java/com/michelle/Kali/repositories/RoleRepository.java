package com.michelle.Kali.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.michelle.Kali.models.ERole;
import com.michelle.Kali.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAll();
    
    List<Role> findByName(String name);

    Optional<Role> findByName(ERole name);
}
