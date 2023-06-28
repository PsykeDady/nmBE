package co.psyke.nanosoftma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.psyke.nanosoftma.models.User;
import co.psyke.nanosoftma.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(originPatterns = "*")
public class UsersController {
	
	@Autowired
	UserService userService; 

	
}
