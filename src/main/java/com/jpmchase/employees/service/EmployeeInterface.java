package com.jpmchase.employees.service;

import com.jpmchase.employees.graphql.request.EmployeeRequest;
import com.jpmchase.employees.model.EmployeeEntity;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.List;


public interface EmployeeInterface {
    Optional<EmployeeEntity> findById(Integer employeeId);
    Optional<EmployeeEntity> findByEmployeeEmail(String employeeEmail);
    EmployeeEntity createEmployee(EmployeeRequest employeeRequest);
    List<EmployeeEntity> findAll();
    void deleteEmployee(Integer employeeFilterId);
}
