package org.userservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userservice.entity.LoginCredentials;

public interface LoginRepo extends JpaRepository<LoginCredentials, Long> {
	

	public Optional<LoginCredentials> findByToken(String msg);
	public Optional<LoginCredentials> findByUserName(String username);

}
