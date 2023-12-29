package com.jpmchase.employees.exception;

import com.jpmchase.employees.constants.EmployeeConstants;
import graphql.GraphQLError;
import graphql.ErrorType;
import graphql.language.SourceLocation;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EmployeeQLCustomException extends RuntimeException implements GraphQLError {
    private final String errorCode;
    private final HttpStatus status;

    public EmployeeQLCustomException(String errorCode, String message, HttpStatus status) {
        super(message, null, false, false);
        this.errorCode = errorCode;
        this.status = status;
    }

    @Override
    public List<SourceLocation> getLocations() { return null; }

    @Override
    public ErrorType getErrorType() { return null; }

    @Override
    public List<Object> getPath() { return null; }

    @Override
    public Map<String, Object> getExtensions() {
        Map<String, Object> customAttributes = new LinkedHashMap<>();
        customAttributes.put(EmployeeConstants.ERROR_CODE, this.errorCode);
        customAttributes.put(EmployeeConstants.MESSAGE, this.getMessage());
        customAttributes.put(EmployeeConstants.HTTP_STATUS_CODE, this.status.value());
        customAttributes.put(EmployeeConstants.CLASSIFICATION, this.status);
        return customAttributes;
    }
}