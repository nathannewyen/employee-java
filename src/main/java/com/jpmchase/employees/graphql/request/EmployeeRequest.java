package com.jpmchase.employees.graphql.request;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class EmployeeRequest {
    @Size(max = 16, min = 1, message = "Max length for employeeName is 8 and minimum is 1")
    @NotNull(message = "Field employeeName cannot be null")
    private String EmployeeName;

    @Size(max = 50, min = 1, message = "Max length for employeeEmail is 50 and minimum is 1")
    @NotNull(message = "Field employeeEmail cannot be null")
    private String EmployeeEmail;
}
