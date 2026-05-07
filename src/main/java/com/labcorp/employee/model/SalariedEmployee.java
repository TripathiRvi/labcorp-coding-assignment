package com.labcorp.employee.model;

public non-sealed class SalariedEmployee extends Employee {

    public SalariedEmployee(Long id) {
        super(id, EmployeeType.SALARIED);
    }

    @Override
    protected double yearlyVacationAllowance() {
        return 15.0;
    }
}