package org.userservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userservice.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Long>{
	
	

}
