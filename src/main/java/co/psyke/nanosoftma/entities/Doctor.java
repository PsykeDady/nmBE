package co.psyke.nanosoftma.entities;

import co.psyke.nanosoftma.models.DoctorType;
import jakarta.persistence.CascadeType;
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
	private String email; 

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_email")
	@MapsId
	private User u;

	@NotBlank
	private DoctorType specialty;
}
