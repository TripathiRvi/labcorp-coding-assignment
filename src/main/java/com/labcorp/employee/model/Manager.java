package com.labcorp.employee.model;

public final class Manager extends Employee {

    public Manager(Long id) {
        super(id, EmployeeType.MANAGER);
    }

    @Override
    protected double yearlyVacationAllowance() {
        return 30.0;
    }
}