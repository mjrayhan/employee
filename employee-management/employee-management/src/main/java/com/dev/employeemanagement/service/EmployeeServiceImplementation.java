package com.dev.employeemanagement.service;

import com.dev.employeemanagement.entity.Employee;
import com.dev.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImplementation implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        try{
            return employeeRepository.findById(id).get();
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving employee with ID: " + id, e);
        }

    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        try {
            Employee employeeToUpdate = employeeRepository.findById(id).get();
            employeeToUpdate.setFirstName(employee.getFirstName());
            employeeToUpdate.setLastName(employee.getLastName());
            employeeToUpdate.setEmailAddress(employee.getEmailAddress());
            employeeToUpdate.setPhone(employee.getPhone());
            employeeToUpdate.setBirthDate(employee.getBirthDate());
            employeeToUpdate.setJobTitle(employee.getJobTitle());
            employeeToUpdate.setDepartment(employee.getDepartment());
            employeeToUpdate.setLocation(employee.getLocation());
            employeeToUpdate.setStartDate(employee.getStartDate());
            employeeToUpdate.setReportingManager(employee.getReportingManager());
            employeeRepository.save(employeeToUpdate);
            return employeeToUpdate;
        } catch (Exception e) {
            throw new RuntimeException("Error updating employee with ID: " + id, e);
        }

    }

    @Override
    public void deleteEmployee(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting employee with ID: " + id, e);
        }
    }
}
