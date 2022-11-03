package com.michelle.Kali.security.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.michelle.Kali.models.User;
import com.michelle.Kali.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepo;
	
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	    User user = userRepo.findByUsername(username)
	    		.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsImpl.build(user);
		
	}
	
    public User getUser(Long id) {
    	Optional<User> optionalUser = userRepo.findById(id);
    	if(optionalUser.isPresent()) {
    		return optionalUser.get();
    	}else {
    		return null;
    	}
    }
}
