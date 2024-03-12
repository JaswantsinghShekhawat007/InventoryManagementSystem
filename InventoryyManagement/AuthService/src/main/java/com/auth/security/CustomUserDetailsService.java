package com.auth.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.entity.Auth;
import com.auth.repository.AuthRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private AuthRepository authRepository;	

	@Override
	public UserDetails loadUserByUsername(String userIdOrEmail) throws UsernameNotFoundException {
		
		Auth user = authRepository.findByUserIdOrEmail(userIdOrEmail, userIdOrEmail)
				.orElseThrow(() ->  new RuntimeException( "User does not Exist" ));
				
		 Set<GrantedAuthority> authorities = user.getRoles().stream()
	                .map((role) -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toSet());
		
		return new User(userIdOrEmail, user.getPassword(), authorities );
	}
	
}
