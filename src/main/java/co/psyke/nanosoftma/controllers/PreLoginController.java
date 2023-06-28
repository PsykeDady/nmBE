package co.psyke.nanosoftma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import co.psyke.nanosoftma.models.User;
import co.psyke.nanosoftma.services.UserService;
import jakarta.persistence.EntityManager;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/")
public class PreLoginController {
	@Autowired
	private UserService userService; 

	@PostMapping("/register")
	public ResponseEntity<Long> register(User u){
		return ResponseEntity.ok().body(userService.register(u));
	}
}
