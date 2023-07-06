package co.psyke.nanosoftma.models;

import java.time.LocalDateTime;

import co.psyke.nanosoftma.entities.Doctor;
import jakarta.validation.constraints.NotNull;

public record AppointmentForm (
	@NotNull
	Doctor d, 
	@NotNull
	LocalDateTime l
){}
