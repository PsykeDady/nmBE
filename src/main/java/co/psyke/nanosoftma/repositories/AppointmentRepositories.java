package co.psyke.nanosoftma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.psyke.nanosoftma.entities.Appointment;
import co.psyke.nanosoftma.entities.Doctor;

import java.util.List;
import co.psyke.nanosoftma.entities.User;


public interface AppointmentRepositories extends JpaRepository<Appointment,Long> {

	@Query("SELECT a FROM Appointment a WHERE a.user = :u")
	public List<Appointment> findByUser (User u);

	@Query("SELECT a FROM Appointment a WHERE a.doctor = :d")
	public List<Appointment> findByDoctor(Doctor d); 
}
