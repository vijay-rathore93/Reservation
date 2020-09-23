package org.userservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userservice.entity.Customer;
import org.userservice.entity.Travels;

public interface TravelsRepo extends JpaRepository<Travels, Long> {
	
	
	public Optional<Travels> findByToken(String msg);
	public Optional<Travels> findByUserName(String username);
	

}
