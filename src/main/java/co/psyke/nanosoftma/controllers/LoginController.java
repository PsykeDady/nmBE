package co.psyke.nanosoftma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.models.UserForm;
import co.psyke.nanosoftma.services.UserService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(originPatterns = "*")
@Validated
public class LoginController {
	@Autowired
	private UserService userService; 

	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody @Valid UserForm uf){
		userService.register(uf);
		
		return ResponseEntity.ok().body(null);
	}

	@GetMapping("/login")
	public ResponseEntity<User> login (Authentication authentication){
		User u= userService.getByEmail(authentication.getName().toString());
		return ResponseEntity.ok().body(u);
	}
}
