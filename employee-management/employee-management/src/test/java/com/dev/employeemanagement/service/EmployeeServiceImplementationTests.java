package com.dev.employeemanagement.service;

import com.dev.employeemanagement.entity.Employee;
import com.dev.employeemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmployeeServiceImplementationTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImplementation employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee();
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee createdEmployee = employeeService.createEmployee(employee);

        assertEquals(employee, createdEmployee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void testGetEmployeeById() {
        Long id = 1L;
        Employee employee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Employee retrievedEmployee = employeeService.getEmployeeById(id);

        assertEquals(employee, retrievedEmployee);
        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    void testGetEmployeeById_NotFound() {
        Long id = 1L;
        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(id));
    }

    @Test
    void testGetEmployees() {
        List<Employee> employeeList = new ArrayList<>();
        when(employeeRepository.findAll()).thenReturn(employeeList);

        List<Employee> retrievedEmployees = employeeService.getEmployees();

        assertEquals(employeeList, retrievedEmployees);
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testUpdateEmployee() {
        Long id = 1L;
        Employee existingEmployee = new Employee();
        Employee updatedEmployee = new Employee();
        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));

        Employee returnedEmployee = employeeService.updateEmployee(id, updatedEmployee);

        assertEquals(updatedEmployee, returnedEmployee);
        verify(employeeRepository, times(1)).findById(id);
    }

    @Test
    void testDeleteEmployee() {
        Long id = 1L;

        employeeService.deleteEmployee(id);

        verify(employeeRepository, times(1)).deleteById(id);
    }
}
