package co.psyke.nanosoftma.entities;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import co.psyke.nanosoftma.serializers.AppointmentSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@JsonSerialize(using = AppointmentSerializer.class)
public class Appointment{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name="user_email",nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name="doctor_email",nullable = false)
	private Doctor doctor;

	@Column(nullable = false)
	@NotNull
	private LocalDateTime appointmentDate;
}
