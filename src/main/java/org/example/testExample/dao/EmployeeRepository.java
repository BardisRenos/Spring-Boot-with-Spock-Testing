package org.example.testExample.dao;

import org.example.testExample.resources.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByEmployeeId(Long id);

    List<Employee> findByEmployeeLastName(String lastName);
}
