package co.psyke.nanosoftma.models;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * UserForm
 */
public record UserForm (
	@NotBlank
	String name,
	@Email
	@NotBlank
	String email,
	@NotBlank
	String pskH,
	@NotNull
	UserType user,
	@Nullable
	DoctorType doctorType
){}