package co.psyke.nanosoftma.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Credential {
	
	@Id
	private String email; 

	@MapsId
	@OneToOne
	@JoinColumn(name="user_email",nullable = false)
	private User user;

	@NotBlank
	private String pskH;
}
