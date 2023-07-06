package co.psyke.nanosoftma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.models.UserForm;
import co.psyke.nanosoftma.repositories.DoctorRepositories;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepositories doctorRepositories; 

	public Doctor registerDoctor(UserForm uf){
		User u = new User(uf.email(),uf.name());
		
		Doctor d= new Doctor(uf.email(),u, uf.doctorType());
		
		return doctorRepositories.save(d);
	}

	public List<Doctor> listDoctor() {
		List<Doctor> lista = doctorRepositories.findAll();

		return lista;
	}
}
