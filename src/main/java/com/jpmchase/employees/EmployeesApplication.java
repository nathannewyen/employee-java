package com.jpmchase.employees;

import graphql.scalars.ExtendedScalars;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EmployeesApplication {

    public static void main(final String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

    @Bean
    public graphql.schema.GraphQLScalarType extededScalarLong() {
        return ExtendedScalars.GraphQLLong;
    }

}
