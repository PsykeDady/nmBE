package co.psyke.nanosoftma.security;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {
	
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{ 
		return http.
			authorizeHttpRequests((auth)->{auth.anyRequest().authenticated();}).
			httpBasic(Customizer.withDefaults()).
			formLogin(Customizer.withDefaults()).
		build();
	}
}
