package org.example.testExample.service

import org.example.testExample.dao.EmployeeRepository
import org.example.testExample.resources.Employee
import org.example.testExample.service.Implementation.EmployeeServiceImpl
import spock.lang.Specification
import spock.lang.Title

@Title("Service Layer Test Employee Entity")
class EmployeeServiceImplTest extends Specification {

    def "GetAllEmployees"() {
        setup:
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee(1L, "John", "Smith", "123 Boulevard"),
                new Employee(2L, "Nicolas", "Ahten", "589 North Street"),
                new Employee(3L, "Nikki", "Mentoza", "745 South Street")))

        def employeeRepositoryMock = Spy(EmployeeRepository)
        employeeRepositoryMock.findAll() >> employees
        def employeeService = new EmployeeServiceImpl(employeeRepositoryMock)

        when:
        List<Employee> res = employeeService.getAllEmployees()

        then:
        res.size() == 3
        res.get(0).getEmployeeId() == 1
        res.get(0).getEmployeeName() == "John"
        res.get(0).getEmployeeLastName() == "Smith"

        res.get(1).getEmployeeId() == 2
        res.get(1).getEmployeeName() == "Nicolas"
        res.get(1).getEmployeeLastName() == "Ahten"
    }

    def "GetEmployeeById"() {
        setup:
        Employee employee = new Employee(1L, "John", "Smith", "123 Boulevard")

        def employeeRepositoryMock = Spy(EmployeeRepository)
        employeeRepositoryMock.findByEmployeeId(1L) >> employee
        def employeeService = new EmployeeServiceImpl(employeeRepositoryMock)

        when:
        Employee res = employeeService.getEmployeeById(1L)

        then:
        res.getEmployeeId() == 1
        res.getEmployeeName() == "John"
        res.getEmployeeLastName() == "Smith"
        res.getEmployeeAddress() == "123 Boulevard"
    }

    def "GetEmployeeByLastName"() {
        setup:
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee(1L, "John", "Smith", "123 Boulevard"),
                new Employee(2L, "Nicolas", "Smith", "589 North Street"),
                new Employee(3L, "Nikki", "Smith", "745 South Street")))

        def employeeRepositoryMock = Spy(EmployeeRepository)
        employeeRepositoryMock.findByEmployeeLastName("Smith") >> employees
        def employeeService = new EmployeeServiceImpl(employeeRepositoryMock)

        when:
        List<Employee> res = employeeService.getEmployeeByLastName("Smith")

        then:
        res.size() == 3
        res.get(0).getEmployeeId() == 1
        res.get(0).getEmployeeName() == "John"
        res.get(0).getEmployeeLastName() == "Smith"
        res.get(0).getEmployeeAddress() == "123 Boulevard"

    }
}
