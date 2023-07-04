package co.psyke.nanosoftma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.psyke.nanosoftma.entities.User;

public interface UserRepositories extends JpaRepository<User,Long>  {

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	User findByEmail(String email);

	@Query("SELECT u FROM User u WHERE u.email = ?1 and u.pskH = ?2")
	User findByEmailAndPskH(String email,String pskH);
	
}
