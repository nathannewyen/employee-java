package com.jpmchase.employees.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "EMPLOYEE_ID", updatable = false)
    private Integer employeeId;

    @Basic
    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Basic
    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;

    @ManyToOne(optional = false)
    @JoinColumn(name = "DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    private DepartmentEntity department;
}