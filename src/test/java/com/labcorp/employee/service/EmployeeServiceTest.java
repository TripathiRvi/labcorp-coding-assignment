package com.labcorp.employee.service;

import com.labcorp.employee.dto.EmployeeResponse;
import com.labcorp.employee.exception.BusinessValidationException;
import com.labcorp.employee.exception.EmployeeNotFoundException;
import com.labcorp.employee.model.HourlyEmployee;
import com.labcorp.employee.model.Manager;
import com.labcorp.employee.model.SalariedEmployee;
import com.labcorp.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {

    private EmployeeRepository repository;

    private EmployeeService service;

    @BeforeEach
    void setup() {

        repository = Mockito.mock(EmployeeRepository.class);

        service = new EmployeeService(repository);
    }

    @Test
    void shouldAccumulateVacationDaysWhenEmployeeWorks() {

        HourlyEmployee employee = new HourlyEmployee(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeResponse response = service.work(1L, 26);

        assertEquals(1.0, response.vacationDays());
        assertEquals(26, response.workedDays());
    }

    @Test
    void shouldThrowExceptionWhenEmployeeNotFound() {

        when(repository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                EmployeeNotFoundException.class,
                () -> service.getEmployee(99L)
        );
    }

    @Test
    void shouldThrowExceptionWhenVacationExceedsBalance() {

        HourlyEmployee employee = new HourlyEmployee(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        assertThrows(
                BusinessValidationException.class,
                () -> service.takeVacation(1L, 5)
        );
    }

    @Test
    void shouldTakeVacationSuccessfully() {

        HourlyEmployee employee = new HourlyEmployee(1L);

        employee.work(52);

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeResponse response =
                service.takeVacation(1L, 1);

        assertEquals(1.0, response.vacationDays());
    }

    @Test
    void shouldThrowExceptionWhenWorkingMoreThan260Days() {

        HourlyEmployee employee = new HourlyEmployee(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        assertThrows(
                BusinessValidationException.class,
                () -> service.work(1L, 300)
        );
    }

    @Test
    void shouldThrowExceptionForNegativeWorkDays() {

        HourlyEmployee employee = new HourlyEmployee(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        assertThrows(
                BusinessValidationException.class,
                () -> service.work(1L, -5)
        );
    }

    @Test
    void shouldThrowExceptionForNegativeVacationDays() {

        HourlyEmployee employee = new HourlyEmployee(1L);

        employee.work(52);

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        assertThrows(
                BusinessValidationException.class,
                () -> service.takeVacation(1L, -2)
        );
    }

    @Test
    void shouldAllowExactly260WorkDays() {

        HourlyEmployee employee = new HourlyEmployee(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeResponse response =
                service.work(1L, 260);

        assertEquals(260, response.workedDays());
    }

    @Test
    void managerShouldAccumulateMoreVacationDays() {

        Manager manager = new Manager(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(manager));

        EmployeeResponse response =
                service.work(1L, 26);

        assertEquals(3.0, response.vacationDays());
    }

    @Test
    void shouldCalculateVacationAccurately() {

        SalariedEmployee employee =
                new SalariedEmployee(1L);

        when(repository.findById(1L))
                .thenReturn(Optional.of(employee));

        EmployeeResponse response =
                service.work(1L, 52);

        assertEquals(3.0, response.vacationDays());
    }
}