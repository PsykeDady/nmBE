package co.psyke.nanosoftma.models;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public record User (
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id, 
	@NotBlank
	String name,
	@Email
	@NotBlank
	String email,
	@NotBlank
	String pskH
) {}
