package co.psyke.nanosoftma.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.psyke.nanosoftma.models.User;

public class CustomUserDetail implements UserDetails {

	private User u;


	public CustomUserDetail(String email) {
		this.u=new User();
		u.setEmail(email);
	}

	public static UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
		CustomUserDetail cu= new CustomUserDetail(email);
		return cu;
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
