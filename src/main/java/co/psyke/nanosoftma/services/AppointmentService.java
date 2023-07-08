package co.psyke.nanosoftma.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Appointment;
import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.repositories.AppointmentRepositories;
import co.psyke.nanosoftma.repositories.DoctorRepositories;
import co.psyke.nanosoftma.repositories.UserRepositories;
import co.psyke.nanosoftma.validators.AppointmentDateValidation;

@Service
public class AppointmentService {
	
	@Autowired
	private UserRepositories userRepositories;

	@Autowired
	private AppointmentDateValidation appointmentDateValidation;

	@Autowired
	private AppointmentRepositories appointmentRepositories; 

	@Autowired
	private DoctorRepositories doctorRepositories;

	public Long registerAppointment (String email, Doctor d, LocalDateTime l) {
		Appointment a = new Appointment(); 
		User u= userRepositories.findById(email).get();
		
		List<Appointment> doctorsAppointment = appointmentRepositories.findByDoctor(d);

		if(doctorsAppointment.size()>10){
			throw new IllegalArgumentException("too many appointment for this doctor");
		}

		if(!appointmentDateValidation.validDate(l,doctorsAppointment)){
			throw new IllegalArgumentException("not valid date");
		}

		a.setDoctor(d);
		a.setUser(u);
		a.setAppointmentDate(l);

		
		return appointmentRepositories.save(a).getId();
	}


	public List<Appointment> userAppointment(User u){

		Doctor d= doctorRepositories.findById(u.getEmail()).orElse(null);
		
		if(d==null){
			return appointmentRepositories.findByUser(u);
		}

		return appointmentRepositories.findByDoctor(d);

	}
}
