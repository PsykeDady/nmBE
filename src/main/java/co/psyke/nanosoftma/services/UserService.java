package co.psyke.nanosoftma.services;

import static co.psyke.nanosoftma.security.Encryption.cryptedPsk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Appointment;
import co.psyke.nanosoftma.entities.Credential;
import co.psyke.nanosoftma.entities.Doctor;
import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.models.UserForm;
import co.psyke.nanosoftma.repositories.CredentialRepository;
import co.psyke.nanosoftma.repositories.DoctorRepositories;
import co.psyke.nanosoftma.repositories.UserRepositories;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepositories userRepositories; 

	@Autowired
	private CredentialRepository credentialRepository; 

	@Autowired
	private DoctorRepositories doctorRepositories;

	public void deleteUser (String email) {
		credentialRepository.deleteById(email);
		doctorRepositories.deleteById(email);
	}

	public void register(UserForm uf){
		User u = new User(uf.email(),uf.name());
		if(userRepositories.findById(uf.email()).isPresent()){
			throw new IllegalArgumentException("email already registered");
		}
		u = userRepositories.save(u); 
		String pskH =  cryptedPsk(uf.pskH()); 
		Credential c = new Credential(null,u,pskH); 

		switch (uf.user()){
			case DOCTOR: 
				Doctor d= new Doctor(null,u, uf.doctorType());
				doctorRepositories.save(d);
			break; 

			default: ; 
		}

		credentialRepository.save(c);
	}

	public User getByEmail (String email) {
		User u = userRepositories.findById(email).get(); 
		return u;
	}

	public void editInfo(User u){
		if(userRepositories.findById(u.getEmail())==null){
			throw new IllegalArgumentException();
		}
		userRepositories.save(u);
	}
}
