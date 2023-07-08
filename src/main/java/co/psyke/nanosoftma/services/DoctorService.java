package co.psyke.nanosoftma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.models.DoctorType;
import co.psyke.nanosoftma.repositories.DoctorRepositories;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepositories doctorRepositories; 

	public List<Doctor> listDoctor() {
		List<Doctor> lista = doctorRepositories.findAll();

		return lista;
	}

	public List<Doctor> searchBySpecialty (String search) {
		if(!DoctorType.present(search)){
			throw new IllegalArgumentException("no specialty found with identifier "+search);
		}
		
		List<Doctor> lista = doctorRepositories.findBySpecialty(search); 

		return lista; 
	}
}
