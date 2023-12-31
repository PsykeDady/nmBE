package co.psyke.nanosoftma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.models.DoctorType;

public interface DoctorRepositories extends JpaRepository<Doctor,String> {
	
	@Query("SELECT d FROM Doctor d WHERE d.specialty=:search")
	List<Doctor> findBySpecialty (DoctorType search); 

}
