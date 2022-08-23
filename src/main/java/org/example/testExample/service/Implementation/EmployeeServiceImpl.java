package org.example.testExample.service.Implementation;

import lombok.RequiredArgsConstructor;
import org.example.testExample.dao.EmployeeRepository;
import org.example.testExample.exception.EmployeeNotFoundException;
import org.example.testExample.resources.Employee;
import org.example.testExample.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        if (Objects.isNull(employeeRepository.findByEmployeeId(id))) {
            throw new EmployeeNotFoundException(String.format("The employee not found with the id: %d", id));
        }
        return employeeRepository.findByEmployeeId(id);
    }

    @Override
    public List<Employee> getEmployeeByLastName(String lastName) throws EmployeeNotFoundException {
        if (Objects.isNull(employeeRepository.findByEmployeeLastName(lastName))) {
            throw new EmployeeNotFoundException(String.format("The employee not found with the last name: %s", lastName));
        }
        return employeeRepository.findByEmployeeLastName(lastName);
    }
}
