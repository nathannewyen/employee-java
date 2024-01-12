package com.jpmchase.employees.service;

import com.jpmchase.employees.graphql.request.DepartmentRequest;
import com.jpmchase.employees.model.DepartmentEntity;

public interface DepartmentInterface {
    DepartmentEntity createDepartment(DepartmentRequest departmentRequest);
}
