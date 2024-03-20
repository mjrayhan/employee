package com.dev.employeemanagement.service;

import com.dev.employeemanagement.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public Employee createEmployee(Employee employee);

    public Employee getEmployeeById(Long id);

    public List<Employee> getEmployees();

    public Employee updateEmployee(Long id, Employee employee);

    public void deleteEmployee(Long id);

}
