package com.jpmchase.employees.graphql.mutation;

import com.jpmchase.employees.graphql.request.EmployeeRequest;
import com.jpmchase.employees.model.EmployeeEntity;
import com.jpmchase.employees.service.EmployeeInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Controller
@Validated
@Slf4j
public class EmployeeMutation {
    private final EmployeeInterface employeeInterface;

    public EmployeeMutation(EmployeeInterface employeeInterface) {
        this.employeeInterface = employeeInterface;
    }

    @MutationMapping
    public EmployeeEntity createEmployee(@Argument("createEmployeeRequest") @Valid EmployeeRequest request) {
        log.info("Starting createEmployee method");
        return employeeInterface.createEmployee(request);
    }

    @MutationMapping
    public void deleteEmployee(@Argument("employeeId") Integer employeeId) {
        log.info("Starting deleteEmployee method");
        employeeInterface.deleteEmployee(employeeId);
    }

}
