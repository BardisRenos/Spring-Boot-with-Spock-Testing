package org.example.testExample.service;

import org.example.testExample.exception.EmployeeNotFoundException;
import org.example.testExample.resources.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id) throws EmployeeNotFoundException;

    List<Employee> getEmployeeByLastName(String lastName) throws EmployeeNotFoundException;
}
