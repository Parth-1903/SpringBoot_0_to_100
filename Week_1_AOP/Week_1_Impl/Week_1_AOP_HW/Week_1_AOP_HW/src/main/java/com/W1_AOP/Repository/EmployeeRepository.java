package com.W1_AOP.Repository;

import com.W1_AOP.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	Optional<Employee> findByEmail(String username);
}
