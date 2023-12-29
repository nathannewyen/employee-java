package com.jpmchase.employees.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DEPARTMENT")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPARTMENT_ID", updatable = false)
    private Long departmentId;

    @Basic
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "DEPARTMENT_ID")
    private DepartmentEntity department;
}
