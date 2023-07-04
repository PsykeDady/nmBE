package co.psyke.nanosoftma.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.psyke.nanosoftma.entities.Doctor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


@RequestMapping("/doctors")
public class DoctorController {
	@GetMapping("/list")
	public ResponseEntity<List<Doctor>> getDoctors () {

		return ResponseEntity.ok().body(List.of());
	}

	@GetMapping("/search/{specialty}")
	public ResponseEntity<List<Doctor>> searchSpecialty (@Valid @PathVariable @NotNull String search) {
		return ResponseEntity.ok().body(List.of());
	}

}
