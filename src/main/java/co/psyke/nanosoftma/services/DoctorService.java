package co.psyke.nanosoftma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.models.DoctorType;
import co.psyke.nanosoftma.models.UserForm;
import co.psyke.nanosoftma.repositories.DoctorRepositories;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepositories doctorRepositories; 

	public Doctor registerDoctor(User u, DoctorType dt){
		if(u.getId()==null || u.getId()<=0){
			throw new IllegalStateException();
		}
		Doctor d= new Doctor(null, u, dt);
		return doctorRepositories.save(d);
	}
}
