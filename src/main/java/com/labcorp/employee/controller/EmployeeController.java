package com.labcorp.employee.controller;

import com.labcorp.employee.dto.EmployeeResponse;
import com.labcorp.employee.dto.VacationRequest;
import com.labcorp.employee.dto.WorkRequest;
import com.labcorp.employee.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/{id}/work")
    public EmployeeResponse work(
            @PathVariable Long id,
            @Valid @RequestBody WorkRequest request
    ) {
        return employeeService.work(id, request.days());
    }

    @PostMapping("/{id}/vacation")
    public EmployeeResponse takeVacation(
            @PathVariable Long id,
            @Valid @RequestBody VacationRequest request
    ) {
        return employeeService.takeVacation(id, request.days());
    }
}