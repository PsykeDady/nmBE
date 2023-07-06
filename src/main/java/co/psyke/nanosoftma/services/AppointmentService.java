package co.psyke.nanosoftma.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Appointment;
import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.repositories.AppointmentRepositories;

@Service
public class AppointmentService {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AppointmentRepositories appointmentRepositories; 

	public Long registerAppointment (String email, Doctor d, LocalDateTime l) {
		Appointment a = new Appointment(); 
		User u= userService.getByEmail(email);
		
		a.setDoctor(d);
		a.setUser(u);
		a.setAppointmentDate(l);

		
		return appointmentRepositories.save(a).getId();
	}


	public List<Appointment> userAppointment(User u){
		return appointmentRepositories.findByUser(u);
	}
}
