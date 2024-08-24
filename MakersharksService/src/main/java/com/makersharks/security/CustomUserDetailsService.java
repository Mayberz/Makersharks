package com.makersharks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.makersharks.Entity.UserData;
import com.makersharks.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserData user=repository.findByUsername(username);
		if (user==null) {
			throw new UsernameNotFoundException("user not found");
		}
		else {
			return new CustomUser(user);	
		}
		
	}

}
