package com.labcorp.employee.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record WorkRequest(
        @Min(value = 1, message = "Work days must be at least 1")
        @Max(value = 260, message = "Work days cannot be more than 260")
        int days
) {
}