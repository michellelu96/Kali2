package com.michelle.Kali.security.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.michelle.Kali.models.User;
import com.michelle.Kali.repositories.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepo;
	
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	    User user = userRepo.findByUsername(username)
	    		.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
		
	}

}
