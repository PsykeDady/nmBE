package co.psyke.nanosoftma.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

import co.psyke.nanosoftma.services.CustomUserDetailServices;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private CustomUserDetailServices userDetailsService; 


	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((auth)->
				auth
					.requestMatchers("/register").permitAll()
					.requestMatchers("/h2-console").permitAll()
					.requestMatchers("/error").permitAll()
					.requestMatchers("/admin").hasRole("ADMIN")
					.anyRequest().authenticated()
			)
			.authenticationProvider(new AuthenticationProvider() {

				@Override
				public Authentication authenticate(Authentication authentication) throws AuthenticationException {
					String username = authentication.getPrincipal().toString();
					UserDetails customUserDetail = userDetailsService.loadUserByUsername(username);

					return new UsernamePasswordAuthenticationToken(
						(Object)customUserDetail.getUsername(),
						(Object)customUserDetail.getPassword(),
						customUserDetail.getAuthorities()
					);
				}

				@Override
				public boolean supports(Class<?> authentication) {
					return authentication.equals(UsernamePasswordAuthenticationToken.class);
				}
				
			})
			.csrf((csrf)->csrf.disable())
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	
}
