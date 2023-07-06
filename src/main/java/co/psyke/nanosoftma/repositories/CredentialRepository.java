package co.psyke.nanosoftma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.psyke.nanosoftma.entities.Credential;

public interface CredentialRepository extends JpaRepository<Credential,String>{}
