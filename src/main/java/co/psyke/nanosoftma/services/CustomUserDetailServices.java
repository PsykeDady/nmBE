package co.psyke.nanosoftma.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.psyke.nanosoftma.security.CustomUserDetail;

public class CustomUserDetailServices implements UserDetailsService{
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new CustomUserDetail(username);
	}
	
}
