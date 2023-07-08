package co.psyke.nanosoftma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.services.DoctorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService; 

	@GetMapping("/list")
	public ResponseEntity<List<Doctor>> getDoctors () {
		return ResponseEntity.ok().body(doctorService.listDoctor());
	}

	@GetMapping("/search/{specialty}")
	public ResponseEntity<List<Doctor>> searchSpecialty (@Valid @PathVariable @NotNull String search) {
		return ResponseEntity.ok().body(doctorService.searchBySpecialty(search));
	}

}
