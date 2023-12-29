package com.jpmchase.employees.service;

import com.jpmchase.employees.constants.EmployeeConstants;
import com.jpmchase.employees.enums.ErrorEnum;
import com.jpmchase.employees.exception.EmployeeQLCustomException;
import com.jpmchase.employees.graphql.request.EmployeeRequest;
import com.jpmchase.employees.model.EmployeeEntity;
import com.jpmchase.employees.repository.EmployeeRepository;
import com.jpmchase.employees.util.ErrorCodes;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService implements EmployeeInterface {
    @Autowired
    private EmployeeRepository employeeRepository;
    private ErrorCodes errorCodes;

    public EmployeeService(EmployeeRepository employeeRepository, ErrorCodes errorCodes) {
        this.employeeRepository = employeeRepository;
        this.errorCodes = errorCodes;
    }

    // Find all employees
    @Override
    public List<EmployeeEntity> findAll() {
        log.info("Inside getAllEmployees method in EmployeeService");
        return (List<EmployeeEntity>) employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> findById(Integer employeeId) {
        log.info("Inside findByEmployeeEmail method inside EmployeeService");

        // Throw error if ID not found
        if(!employeeRepository.existsById(Long.valueOf(employeeId))) {
            StringBuilder errorDescription = new StringBuilder();
            log.info("employeeEmail not found: " + employeeId);
            throw new EmployeeQLCustomException(ErrorEnum.ID_NOT_FOUND_EXCEPTION.getCode(), errorDescription.append(ErrorEnum.ID_NOT_FOUND_EXCEPTION.getDescription()).append(" employeeId: ").append(employeeId).toString(), HttpStatus.NOT_FOUND);
        }

        Optional<EmployeeEntity> response = employeeRepository.findById(Long.valueOf(employeeId));
        return response;
    }

    // Find an employee with email
    public Optional<EmployeeEntity> findByEmployeeEmail(String employeeEmail) {
        log.info("Inside findByEmployeeEmail method inside EmployeeService");

        // Throw error if email not found
        if(!employeeRepository.existsByEmployeeEmail(employeeEmail)) {
            StringBuilder errorDescription = new StringBuilder();
            log.info("employeeEmail not found: " + employeeEmail);
            throw new EmployeeQLCustomException(ErrorEnum.EMAIL_NOT_FOUND_EXCEPTION.getCode(), errorDescription.append(ErrorEnum.EMAIL_NOT_FOUND_EXCEPTION.getDescription()).append(" employeeEmail: ").append(employeeEmail).toString(), HttpStatus.NOT_FOUND);
        }

        Optional<EmployeeEntity> response = employeeRepository.findByEmployeeEmail(employeeEmail);
        return response;
    }

    // Create an employee
    @Override
    @Transactional
    public EmployeeEntity createEmployee(EmployeeRequest request) {
        log.info("Inside createEmployee method in EmployeeService");

        // Throw error for duplicate record
        if (employeeRepository.findByEmployeeEmail(request.getEmployeeEmail()).isPresent()) {
            StringBuilder errorDescription = new StringBuilder();
            log.info(EmployeeConstants.DUPLICATE_RECORD_ERROR_LOG_MESSAGE + request.getEmployeeEmail());
            throw new EmployeeQLCustomException(errorCodes.getDuplicateRecordException().getErrorCode(),
                    errorDescription.append(errorCodes.getDuplicateRecordException().getErrorText())
                            .append(EmployeeConstants.EMPLOYEE_EMAIL_STRING).append(request.getEmployeeEmail()).toString(),
                    HttpStatus.CONFLICT);
        }

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeEmail(request.getEmployeeEmail());
        employeeEntity.setEmployeeName(request.getEmployeeName());

        employeeRepository.save(employeeEntity);
        return employeeEntity;
    }

    // Delete an employee
    @Override
    public void deleteEmployee(@Argument("employeeId") Integer employeeId) {
        log.info("Inside deleteEmployee method in EmployeeService");
        if (employeeRepository.existsById(Long.valueOf(employeeId))) {
            employeeRepository.deleteById(Long.valueOf(employeeId));
        } else {
            throw new EmployeeQLCustomException(ErrorEnum.ID_NOT_FOUND_EXCEPTION.getCode(),
                    ErrorEnum.ID_NOT_FOUND_EXCEPTION.getDescription(),
                    HttpStatus.NOT_FOUND);
        }
    }
}