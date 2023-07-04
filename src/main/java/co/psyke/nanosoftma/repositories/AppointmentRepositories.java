package co.psyke.nanosoftma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.psyke.nanosoftma.entities.Appointment;

public interface AppointmentRepositories extends JpaRepository<Appointment,Long> {}
