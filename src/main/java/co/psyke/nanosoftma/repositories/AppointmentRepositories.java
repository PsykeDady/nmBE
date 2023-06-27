package co.psyke.nanosoftma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.psyke.nanosoftma.models.Appointment;

public interface AppointmentRepositories extends JpaRepository<Appointment,Long> {}
