package co.psyke.nanosoftma.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public record Appointment(
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Long id,

	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	User user,

	@ManyToOne
	@JoinColumn(name="doctor_id",nullable = false)
	Doctor doctor,

	@Column(nullable = false)
	@NotNull
	LocalDateTime appointmentDate
)  {}
