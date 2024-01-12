package com.jpmchase.employees.service;

import com.jpmchase.employees.constants.EmployeeConstants;
import com.jpmchase.employees.exception.EmployeeQLCustomException;
import com.jpmchase.employees.graphql.request.DepartmentRequest;
import com.jpmchase.employees.model.DepartmentEntity;
import com.jpmchase.employees.repository.DepartmentRepository;
import com.jpmchase.employees.util.ErrorCodes;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DepartmentService implements DepartmentInterface {
    @Autowired
    private DepartmentRepository departmentRepository;
    private ErrorCodes errorCodes;

    public DepartmentService(DepartmentRepository departmentRepository, ErrorCodes errorCodes) {
        this.departmentRepository = departmentRepository;
        this.errorCodes = errorCodes;
    }

    @Override
    @Transactional
    public DepartmentEntity createDepartment(DepartmentRequest request) {
        log.info("Inside createDepartment method in DepartmentService");

        // Throw error for duplicate record
        if (departmentRepository.findByDepartmentName(request.getDepartmentName()).isPresent()) {
            StringBuilder errorDescription = new StringBuilder();
            log.info(EmployeeConstants.DUPLICATE_RECORD_ERROR_LOG_MESSAGE + request.getDepartmentName());
            throw new EmployeeQLCustomException(errorCodes.getDuplicateRecordException().getErrorCode(),
                    errorDescription.append(errorCodes.getDuplicateRecordException().getErrorText())
                            .append(EmployeeConstants.EMPLOYEE_EMAIL_STRING).append(request.getDepartmentName()).toString(),
                    HttpStatus.CONFLICT);
        }

        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setDepartmentName(request.getDepartmentName());

        departmentRepository.save(departmentEntity);
        return departmentEntity;
    }
}
