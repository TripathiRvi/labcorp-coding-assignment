package com.labcorp.employee.service;

import com.labcorp.employee.dto.EmployeeResponse;
import com.labcorp.employee.exception.EmployeeNotFoundException;
import com.labcorp.employee.model.Employee;
import com.labcorp.employee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public EmployeeResponse work(Long id, int days) {

        Employee employee = findEmployeeById(id);
        LOGGER.info("Employee {} is working for {} days", id, days);

        employee.work(days);

        return map(employee);
    }

    public EmployeeResponse takeVacation(Long id, double days) {

        Employee employee = findEmployeeById(id);
        LOGGER.info("Employee {} is taking {} vacation days", id, days);

        employee.takeVacation(days);

        return map(employee);
    }

    public EmployeeResponse getEmployee(Long id) {

        Employee employee = findEmployeeById(id);
        LOGGER.info("Fetching employee with id {}", id);

        return map(employee);
    }

    public List<EmployeeResponse> getAllEmployees() {

        LOGGER.info("Fetching all employees");

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private Employee findEmployeeById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> {

                    LOGGER.warn("Employee not found with id {}", id);

                    return new EmployeeNotFoundException(id);
                });
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