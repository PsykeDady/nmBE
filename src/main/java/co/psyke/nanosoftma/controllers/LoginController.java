package co.psyke.nanosoftma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.psyke.nanosoftma.requests.AuthenticationRequest;
import co.psyke.nanosoftma.security.JWTUtils;

@RestController
@CrossOrigin(originPatterns = "*")
@Validated
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager; 

	@Autowired
	private UserDetailsService userDetailsService; 

	@Autowired
	private JWTUtils jwtUtils;

	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate (
		@RequestBody AuthenticationRequest request
	){

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

		if(userDetails != null) {
			return ResponseEntity.ok(jwtUtils.generateToken(userDetails));
		}

		return ResponseEntity.badRequest().body("no user found");
	}	
}
