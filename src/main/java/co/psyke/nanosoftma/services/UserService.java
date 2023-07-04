package co.psyke.nanosoftma.services;

import static co.psyke.nanosoftma.security.Encryption.cryptedPsk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.psyke.nanosoftma.entities.Appointment;
import co.psyke.nanosoftma.entities.User;
import co.psyke.nanosoftma.models.UserForm;
import co.psyke.nanosoftma.repositories.UserRepositories;

@Service
public class UserService {

	@Autowired
	private UserRepositories userRepositories; 

	public Long register(UserForm uf){
		if(userRepositories.findByEmail(uf.email())!=null){
			throw new IllegalArgumentException("email already registered");
		}
		User u = new User(0L,uf.name(),uf.email(),cryptedPsk(uf.pskH()));

		return userRepositories.save(u).getId();
	}

	public User login(String email,String pskH){
		User u = userRepositories.findByEmailAndPskH(email,pskH);
		
		return u;
	}

	public User getByEmail (String email) {
		User u = userRepositories.findByEmail(email); 
		u.setPskH(null);
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
