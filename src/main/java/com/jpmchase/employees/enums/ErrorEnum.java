package com.jpmchase.employees.enums;

import lombok.Getter;

@Getter
public enum ErrorEnum {
    DATA_FETCH_EXCEPTION("DSERR-0001", "Unable to fetch data from Database"),
    INTERNAL_SERVER_ERROR("DSERR-0004", "Internal Server Error, Please try again later"),
    VALIDATION_FAILED_ERROR("DSERR-0007", "The data entered did not pass validation checks. Please try again"),
    EMAIL_NOT_FOUND_EXCEPTION("DSERR-00010", "Email not found for"),
    ID_NOT_FOUND_EXCEPTION("DSERR=00011", "Id not found for"),
    ADD_EMPLOYEE_ERROR("DSERR-0002", "Failed to add new employee");

    private final String code;
    private final String description;

    ErrorEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() { return code + ": " + description; }

}