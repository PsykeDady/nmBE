package co.psyke.nanosoftma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.psyke.nanosoftma.entities.Appointment;

import java.util.List;
import co.psyke.nanosoftma.entities.User;


public interface AppointmentRepositories extends JpaRepository<Appointment,Long> {

	@Query("SELECT a FROM Appointment a WHERE a.user = :#{#u.email}")
	public List<Appointment> findByUser (User u);
}
