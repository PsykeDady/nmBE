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
		if(userRepositories.findByEmail(u.email())!=null){
			throw new IllegalArgumentException("email already registered");
		}

		u= new User(null,u.name(),u.email(),cryptedPsk(u.pskH()));
		return userRepositories.save(u).id();
	}

	public User login(String email){

		User u = userRepositories.findByEmail(email);

		return null;
	}
	public void editInfo(User u){

	}
	public void logout(User u){

	}
	public void viewCalendar(User u){

	}
	public void newAppointment(Appointment a){

	}
}
