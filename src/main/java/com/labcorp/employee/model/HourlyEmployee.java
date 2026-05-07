package com.labcorp.employee.model;

public final class HourlyEmployee extends Employee {

    public HourlyEmployee(Long id) {
        super(id, EmployeeType.HOURLY);
    }

    @Override
    protected double yearlyVacationAllowance() {
        return 10.0;
    }
}