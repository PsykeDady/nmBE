package co.psyke.nanosoftma.services;

import static co.psyke.nanosoftma.security.Encryption.cryptedPsk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Appointment;
import co.psyke.nanosoftma.entities.Credential;
import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.models.UserForm;
import co.psyke.nanosoftma.repositories.CredentialRepository;
import co.psyke.nanosoftma.repositories.UserRepositories;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepositories userRepositories; 

	@Autowired
	private CredentialRepository credentialRepository; 

	public void deleteUser (String email) {
		credentialRepository.deleteById(email);
		userRepositories.deleteById(email);
	}

	public void register(UserForm uf){
		User u = new User(uf.email(),uf.name());
		if(userRepositories.findById(uf.email()).isPresent()){
			throw new IllegalArgumentException("email already registered");
		}
		u = userRepositories.save(u); 
		String pskH =  cryptedPsk(uf.pskH()); 
		Credential c = new Credential(null,u,pskH); 

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

	public void viewCalendar(User u){

	}

	public void newAppointment(Appointment a){

	}
}
