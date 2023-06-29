package co.psyke.nanosoftma.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpRequestResponseHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import co.psyke.nanosoftma.models.User;
import co.psyke.nanosoftma.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	UserService userService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((auth)->
				auth
					.requestMatchers("/register").permitAll()
					.requestMatchers("/error").permitAll()
					.requestMatchers("/admin").hasRole("ADMIN")
			)
			.securityContext(context->{
				context.securityContextRepository());
			})
			.authenticationProvider(new AuthenticationProvider() {
				@Override
				public Authentication authenticate(Authentication authentication) throws AuthenticationException {
					User u = userService.login(authentication.getName(),Encryption.cryptedPsk(authentication.getCredentials().toString()));
					if (u!=null){
						return new UsernamePasswordAuthenticationToken(u.getEmail(), u.getPskH());
					} else {
						return null;
					}
				}

				@Override
				public boolean supports(Class<?> authentication) {
					return authentication.equals(UsernamePasswordAuthenticationToken.class);
				}
				
			})
			.formLogin(login->{
				login.loginProcessingUrl("/login");
			})
			.sessionManagement(session-> {
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			})
			.csrf((csrf)->csrf.disable())
			.httpBasic(withDefaults());
		return http.build();
	}
}
