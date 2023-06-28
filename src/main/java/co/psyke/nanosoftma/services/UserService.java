package co.psyke.nanosoftma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.models.Appointment;
import co.psyke.nanosoftma.models.User;
import co.psyke.nanosoftma.repositories.UserRepositories;

import static co.psyke.nanosoftma.security.Encryption.cryptedPsk;

@Service
public class UserService {

	@Autowired
	private UserRepositories userRepositories; 

	public Long register(User u){
		if(userRepositories.findByEmail(u.getEmail())!=null){
			throw new IllegalArgumentException("email already registered");
		}
		u.setPskH(cryptedPsk(u.getPskH()));
		return userRepositories.save(u).getId();
	}

	public User login(String email){
		User u = userRepositories.findByEmail(email);
		return u;
	}

	public void editInfo(User u){
		if(userRepositories.findById(u.getId())==null){
			throw new IllegalArgumentException();
		}
		userRepositories.save(u);
	}

	public void logout(User u){
		return;
	}

	public void viewCalendar(User u){

	}

	public void newAppointment(Appointment a){

	}
}
