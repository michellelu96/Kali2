package com.michelle.Kali.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.michelle.Kali.exceptions.EmailNotFoundException;
import com.michelle.Kali.models.Role;
import com.michelle.Kali.models.User;
import com.michelle.Kali.repositories.UserRepository;

@Service
@Configuration("UserDetailsServiceImplementation")
public class UserDetailsServiceImplementation implements UserDetailsService {
	
    private UserRepository userRepository;
    
    public UserDetailsServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public UserDetails loadUserByEmail(String email) throws EmailNotFoundException {
        User user = userRepository.findByEmail(email);
        
        if(user == null) {
            throw new EmailNotFoundException("Email not found");
        }
        
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }
    
    // 2
    private List<GrantedAuthority> getAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}
