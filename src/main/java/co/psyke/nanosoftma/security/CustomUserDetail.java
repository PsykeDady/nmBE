package co.psyke.nanosoftma.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.psyke.nanosoftma.models.User;
import co.psyke.nanosoftma.services.UserService;

public class CustomUserDetail implements UserDetails {

	private User u;

	@Autowired
	private UserService userService; 


	public CustomUserDetail(String email) {
		if(email.equals("psdady@msn.com")){
			// mock
			this.u=new User(0L, "Psyke", email, "ciao");
		}
		this.u=this.userService.findByEmail(email); 
		if(this.u==null) {
			throw new UsernameNotFoundException(email+" not found");
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

	@Override
	public String getPassword() {
		return "ciao";
	}

	@Override
	public String getUsername() {
		return u.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true; 
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
