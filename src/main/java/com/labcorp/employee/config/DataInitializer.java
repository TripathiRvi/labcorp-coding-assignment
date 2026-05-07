package com.labcorp.employee.config;

import com.labcorp.employee.model.HourlyEmployee;
import com.labcorp.employee.model.Manager;
import com.labcorp.employee.model.SalariedEmployee;
import com.labcorp.employee.repository.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final EmployeeRepository repository;

    public DataInitializer(EmployeeRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {

        long id = 1;

        for (int i = 0; i < 10; i++) {
            repository.save(new HourlyEmployee(id++));
        }

        for (int i = 0; i < 10; i++) {
            repository.save(new SalariedEmployee(id++));
        }

        for (int i = 0; i < 10; i++) {
            repository.save(new Manager(id++));
        }
    }
}