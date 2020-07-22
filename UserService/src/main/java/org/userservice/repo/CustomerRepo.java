package org.userservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.userservice.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}
