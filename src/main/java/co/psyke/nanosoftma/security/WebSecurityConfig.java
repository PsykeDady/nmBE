package co.psyke.nanosoftma.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import co.psyke.nanosoftma.models.User;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	public final String SERVLETPATH="/nanosoftmedical"; 

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		CustomUserDetail cud= new CustomUserDetail(new User(0L,"Psyke", "psdady@msn.com,", "toor"));
		manager.createUser(cud);
		return manager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain (HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		return http
					.securityMatcher(SERVLETPATH)
					.authorizeHttpRequests((auth)->
						auth
							.requestMatchers(HttpMethod.POST, "/register").permitAll()
							.requestMatchers("/admin*").hasRole("ADMIN")
							.anyRequest().authenticated()
					)
					.csrf((csrf)->csrf.disable())
					.httpBasic(withDefaults())
					.build();
	}
}
