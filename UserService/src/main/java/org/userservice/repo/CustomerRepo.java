package org.userservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userservice.entity.Customer;
import org.userservice.entity.LoginCredentials;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

	public Optional<Customer> findByCustomerId(Long id);
	public Optional<Customer> findByLoginCredentials(LoginCredentials loginCredentials);

}
