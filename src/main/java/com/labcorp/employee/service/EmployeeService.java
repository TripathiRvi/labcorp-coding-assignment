package com.labcorp.employee.service;

import com.labcorp.employee.dto.EmployeeResponse;
import com.labcorp.employee.exception.EmployeeNotFoundException;
import com.labcorp.employee.model.Employee;
import com.labcorp.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeResponse work(Long id, int days) {

        Employee employee = findEmployeeById(id);

        employee.work(days);

        return map(employee);
    }

    public EmployeeResponse takeVacation(Long id, double days) {

        Employee employee = findEmployeeById(id);

        employee.takeVacation(days);

        return map(employee);
    }

    public EmployeeResponse getEmployee(Long id) {

        Employee employee = findEmployeeById(id);

        return map(employee);
    }

    public List<EmployeeResponse> getAllEmployees() {

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private Employee findEmployeeById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(id));
    }

    private EmployeeResponse map(Employee employee) {

        return new EmployeeResponse(
                employee.getId(),
                employee.getType(),
                employee.getVacationDays(),
                employee.getWorkedDays()
        );
    }
}