package com.jpmchase.employees.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jpmc.error")
@Component
@Data
public class ErrorCodes {
    JPMCError duplicateRecordException = new JPMCError();

    @Data
    public static class JPMCError {
        private String errorCode;
        private String errorText;
    }
}
