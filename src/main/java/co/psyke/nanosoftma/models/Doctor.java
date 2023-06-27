package co.psyke.nanosoftma.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Data

public class Doctor {
	@OneToOne
	@JoinColumn(name = "user_id")
	private User u;

	@NotBlank
	private String specialty;
}
