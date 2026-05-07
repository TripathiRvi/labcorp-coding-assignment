package com.labcorp.employee.dto;

import jakarta.validation.constraints.DecimalMin;

public record VacationRequest(
        @DecimalMin(value = "0.1", message = "Vacation days must be greater than 0")
        double days
) {
}