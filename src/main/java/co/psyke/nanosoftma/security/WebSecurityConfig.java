package co.psyke.nanosoftma.security;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.BadCredentialsException;

import co.psyke.nanosoftma.services.CustomUserDetailServices;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private CustomUserDetailServices userDetailsService; 

	public final String BEARER="Bearer: ";

	@Value("${jjwt.secret.key}")
	private String secretKey;


	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((auth)->
				auth
					.requestMatchers("/register","/h2-ui**","/error").permitAll()
					.requestMatchers("**h2**").permitAll()
					.requestMatchers("/admin").hasRole("ADMIN")
					.anyRequest().authenticated()
			)
			.authenticationProvider(new AuthenticationProvider() {

				@Override
				public Authentication authenticate(Authentication authentication) throws AuthenticationException {
					String username = authentication.getPrincipal().toString();

					if(username.startsWith(BEARER)){
						//is token
						SecretKey secrets = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
						String token = username.substring(BEARER.length()); 
						Jwts.builder()
							.setSubject(username)
							.signWith(secrets, SignatureAlgorithm.ES256); // TODO, not completed
						return new UsernamePasswordAuthenticationToken(token, http, null);
					} else {
						// is username
						UserDetails customUserDetail = userDetailsService.loadUserByUsername(username);
						
						if( passwordEncoder().matches(authentication.getCredentials().toString(), customUserDetail.getPassword()) )
							return new UsernamePasswordAuthenticationToken(
								(Object)authentication.getPrincipal(),
								(Object)authentication.getCredentials(),
								customUserDetail.getAuthorities()
							);
						
						return null;
					}
				}

				@Override
				public boolean supports(Class<?> authentication) {
					return authentication.equals(UsernamePasswordAuthenticationToken.class);
				}
				
			})
			.csrf((csrf)->{
				csrf.disable();
			})
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}

	
}
