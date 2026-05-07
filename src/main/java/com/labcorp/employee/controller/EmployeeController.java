package com.labcorp.employee.controller;

import com.labcorp.employee.dto.EmployeeResponse;
import com.labcorp.employee.dto.VacationRequest;
import com.labcorp.employee.dto.WorkRequest;
import com.labcorp.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employee APIs", description = "Employee vacation management APIs")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get all employees")
    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Get employee by id")
    @GetMapping("/{id}")
    public EmployeeResponse getEmployee(@PathVariable Long id) {
        return employeeService.getEmployee(id);
    }

    @Operation(summary = "Employee works specified number of days")
    @PostMapping("/{id}/work")
    public EmployeeResponse work(
            @PathVariable Long id,
            @Valid @RequestBody WorkRequest request
    ) {
        return employeeService.work(id, request.days());
    }

    @Operation(summary = "Employee take vacation")
    @PostMapping("/{id}/vacation")
    public EmployeeResponse takeVacation(
            @PathVariable Long id,
            @Valid @RequestBody VacationRequest request
    ) {
        return employeeService.takeVacation(id, request.days());
    }
}