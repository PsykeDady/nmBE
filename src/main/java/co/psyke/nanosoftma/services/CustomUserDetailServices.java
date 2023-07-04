package co.psyke.nanosoftma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.repositories.UserRepositories;
import co.psyke.nanosoftma.security.CustomUserDetail;

@Service
public class CustomUserDetailServices implements UserDetailsService{

	@Autowired
	private UserRepositories userRepositories; 


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepositories.findByEmail(username);

		if(user==null) 
			throw new UsernameNotFoundException("username" + username + "not found");
		
		return new CustomUserDetail(user);
	}
	
}
