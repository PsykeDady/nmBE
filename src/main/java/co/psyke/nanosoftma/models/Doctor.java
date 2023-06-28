package co.psyke.nanosoftma.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
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
	@Id
	private Long id; 

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@MapsId
	private User u;

	@NotBlank
	private String specialty;
}
