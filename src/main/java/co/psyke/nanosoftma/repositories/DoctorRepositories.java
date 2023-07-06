package co.psyke.nanosoftma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.psyke.nanosoftma.entities.Doctor;

public interface DoctorRepositories extends JpaRepository<Doctor,String> {}
