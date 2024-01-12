package com.jpmchase.employees.repository;

import com.jpmchase.employees.model.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity, Long> {
    boolean existsByDepartmentName(String departmentName);
    Optional<DepartmentEntity> findByDepartmentName(String department_name);

}
