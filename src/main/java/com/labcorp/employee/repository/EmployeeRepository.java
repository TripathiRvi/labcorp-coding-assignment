package com.labcorp.employee.repository;

import com.labcorp.employee.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    Employee save(Employee employee);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();
}