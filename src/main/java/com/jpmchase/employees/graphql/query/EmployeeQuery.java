package com.jpmchase.employees.graphql.query;

import com.jpmchase.employees.model.EmployeeEntity;
import com.jpmchase.employees.service.EmployeeInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class EmployeeQuery {


    @Autowired
    private EmployeeInterface employeeInterface;

    @QueryMapping
    public EmployeeEntity getEmployeeByEmail(@Argument("employeeEmail") @NotNull(message = "employeeEmail cannot be null") String employeeEmail) {
        log.info("Starting getEmployeeByEmail method for employee email {}", employeeEmail);
        Optional<EmployeeEntity> response = null;
        response = employeeInterface.findByEmployeeEmail(employeeEmail);
        return response.get();
    }

    @QueryMapping
    public List<EmployeeEntity> getAllEmployees() {
        log.info("Inside getAllEmployees method in EmployeeQuery");
        return employeeInterface.findAll();
    }
}