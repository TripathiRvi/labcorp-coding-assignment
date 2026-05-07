package com.labcorp.employee.repository;

import com.labcorp.employee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final ConcurrentMap<Long, Employee> employeeStore =
            new ConcurrentHashMap<>();

    @Override
    public Employee save(Employee employee) {
        employeeStore.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(employeeStore.get(id));
    }

    @Override
    public List<Employee> findAll() {
        return employeeStore.values()
                .stream()
                .toList();
    }
}