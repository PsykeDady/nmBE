package co.psyke.nanosoftma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.psyke.nanosoftma.entities.User;

public interface UserRepositories extends JpaRepository<User,String>  {}
