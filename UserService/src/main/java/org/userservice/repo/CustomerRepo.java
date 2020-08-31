package org.userservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userservice.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	
	public Optional<Customer> findByToken(String msg);

	public Optional<Customer> findByCustName(String name);

}
