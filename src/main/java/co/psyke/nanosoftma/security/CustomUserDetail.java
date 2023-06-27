package co.psyke.nanosoftma.security;

import static co.psyke.nanosoftma.security.Encryption.cryptedPsk;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import co.psyke.nanosoftma.models.User;

public class CustomUserDetail implements UserDetails {

	private User u;
	private LocalDateTime l; 

	public CustomUserDetail(User u) {
		this.u=u;
		this.l=LocalDateTime.now();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}

	@Override
	public String getPassword() {
		return cryptedPsk(u.pskH());
	}

	@Override
	public String getUsername() {
		return u.email();
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
