package co.psyke.nanosoftma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Credential;
import co.psyke.nanosoftma.repositories.CredentialRepository;
import co.psyke.nanosoftma.security.CustomUserDetail;

@Service
public class CustomUserDetailServices implements UserDetailsService{

	@Autowired
	private CredentialRepository credentialRepository; 


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Credential credential = credentialRepository.findById(username).get();

		if(credential==null) 
			throw new UsernameNotFoundException("username" + username + "not found");
		
		return new CustomUserDetail(credential);
	}
	
}
