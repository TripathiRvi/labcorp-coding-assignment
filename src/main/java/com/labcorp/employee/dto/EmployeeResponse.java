package com.labcorp.employee.dto;

import com.labcorp.employee.model.EmployeeType;

public record EmployeeResponse(
        Long id,
        EmployeeType type,
        double vacationDays,
        int workedDays
) {
}