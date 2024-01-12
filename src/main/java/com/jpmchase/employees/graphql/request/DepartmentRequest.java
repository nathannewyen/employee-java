package com.jpmchase.employees.graphql.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequest {
    @Size(max = 16, min = 1, message = "Max length for departmentName is 16 and minimum is 1")
    @NotNull(message = "Field departmentName cannot be null")
    private String DepartmentName;
}
