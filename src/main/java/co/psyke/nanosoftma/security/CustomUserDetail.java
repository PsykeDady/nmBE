package co.psyke.nanosoftma.security;

import static co.psyke.nanosoftma.security.Encryption.cryptedPsk;

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
		this.u=userService.findByEmail(email);
	}

	public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
		User user=  userService.findByEmail(email); 
		if(user==null){
			throw new UsernameNotFoundException("no user found");
		}
		return new org.springframework.security.core.userdetails.User(
			user.getEmail(),
			user.getPskH(),
			isEnabled(),
			isAccountNonExpired(),
			isCredentialsNonExpired(),
			isAccountNonLocked(),
			getAuthorities()
		);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

	@Override
	public String getPassword() {
		return cryptedPsk(u.getPskH());
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