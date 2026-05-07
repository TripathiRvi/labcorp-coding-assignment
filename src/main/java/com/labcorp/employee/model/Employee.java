package com.labcorp.employee.model;

import com.labcorp.employee.exception.BusinessValidationException;

public abstract sealed class Employee permits HourlyEmployee, SalariedEmployee, Manager {

    public static final int WORK_DAYS_PER_YEAR = 260;

    private final Long id;
    private final EmployeeType type;
    private double vacationDays;
    private int workedDays;

    protected Employee(Long id, EmployeeType type) {
        this.id = id;
        this.type = type;
        this.vacationDays = 0.0;
        this.workedDays = 0;
    }

    protected abstract double yearlyVacationAllowance();

    public synchronized void work(int days) {
        if (days <= 0) {
            throw new BusinessValidationException("Work days must be greater than zero.");
        }

        if (workedDays + days > WORK_DAYS_PER_YEAR) {
            throw new BusinessValidationException("Employee cannot work more than 260 days in a work year.");
        }

        this.workedDays += days;
        this.vacationDays += (days * yearlyVacationAllowance()) / WORK_DAYS_PER_YEAR;
    }

    public synchronized void takeVacation(double days) {
        if (days <= 0) {
            throw new BusinessValidationException("Vacation days must be greater than zero.");
        }

        if (days > vacationDays) {
            throw new BusinessValidationException("Employee cannot take more vacation than available.");
        }

        this.vacationDays -= days;
    }

    public Long getId() {
        return id;
    }

    public EmployeeType getType() {
        return type;
    }

    public double getVacationDays() {
        return vacationDays;
    }

    public int getWorkedDays() {
        return workedDays;
    }
}