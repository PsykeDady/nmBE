package co.psyke.nanosoftma.security;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

	private final JwtAthFilter jwtAuthFilter;
	
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{ 
		return http
			.authorizeHttpRequests((auth)->{auth.anyRequest().authenticated();})
			.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class)
			.httpBasic(Customizer.withDefaults())
			.formLogin(Customizer.withDefaults())
		.build();
	}
}
