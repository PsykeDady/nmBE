package co.psyke.nanosoftma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.psyke.nanosoftma.entities.Appointment;
import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.models.AppointmentForm;
import co.psyke.nanosoftma.services.AppointmentService;
import co.psyke.nanosoftma.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(originPatterns = "*")
public class UsersController {
	
	@Autowired
	UserService userService; 

	@Autowired
	AppointmentService appointmentService;

	@DeleteMapping("")
	public ResponseEntity<Void> deleteUser(Authentication a) {
		userService.deleteUser(a.getName().toString());
		return ResponseEntity.ok().body(null);
	}

	@GetMapping("/appointments")
	public ResponseEntity<List<Appointment>> appointments (Authentication a) {
		User u=userService.getByEmail(a.getName().toString());
		
		List<Appointment> appointments = appointmentService.userAppointment(u);

		return ResponseEntity.ok().body(appointments); 
	}

	@PostMapping("/newappointment")
	public ResponseEntity<Long> newAppointment (Authentication a, @RequestBody @Valid AppointmentForm af) {
		return ResponseEntity.ok().body( 
			appointmentService.registerAppointment(a.getName().toString(), af.d(), af.l())
		);
	}
}
