package com.jpmchase.employees.repository;

import com.jpmchase.employees.model.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Long> {
    boolean existsByEmployeeEmail(String employeeEmail);
    Optional<EmployeeEntity> findByEmployeeEmail(String employee_email);

}
