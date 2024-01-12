package com.jpmchase.employees.graphql.mutation;

import com.jpmchase.employees.graphql.request.DepartmentRequest;
import com.jpmchase.employees.model.DepartmentEntity;
import com.jpmchase.employees.service.DepartmentInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

@Controller
@Validated
@Slf4j
public class DepartmentMutation {
    private final DepartmentInterface departmentInterface;

    public DepartmentMutation(DepartmentInterface departmentInterface) {
        this.departmentInterface = departmentInterface;
    }

    @MutationMapping
    public DepartmentEntity createDepartment(@Argument("createDepartmentRequest") @Valid DepartmentRequest request) {
        log.info("Starting createDepartment method");
        return departmentInterface.createDepartment(request);
    }
}
