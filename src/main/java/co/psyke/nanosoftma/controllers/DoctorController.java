package co.psyke.nanosoftma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.services.DoctorService;
import jakarta.validation.constraints.NotEmpty;

@RestController
@CrossOrigin(originPatterns = "*")
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService; 

	@GetMapping("/list")
	public ResponseEntity<List<Doctor>> getDoctors () {
		return ResponseEntity.ok().body(doctorService.listDoctor());
	}

	@GetMapping("/search/{search}")
	public ResponseEntity<List<Doctor>> searchSpecialty (@PathVariable @NotEmpty String search) {
		return ResponseEntity.ok().body(doctorService.searchBySpecialty(search));
	}

}
