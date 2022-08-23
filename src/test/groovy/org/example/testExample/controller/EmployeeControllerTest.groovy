package org.example.testExample.controller

import org.example.testExample.resources.Employee
import org.example.testExample.service.Implementation.EmployeeServiceImpl
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.lang.Title
import spock.mock.DetachedMockFactory

import javax.servlet.http.HttpServletResponse

@WebMvcTest(controllers = EmployeeController.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@Title("Controller Layer Test Employee Entity")
class EmployeeControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    EmployeeServiceImpl employeeService

    def setup () {
        employeeService = Spy(EmployeeServiceImpl)
    }

    def "GetEmployees"() {
        given:
        List<Employee> employees = new ArrayList<>(Arrays.asList(
                new Employee(1L, "John", "Smith", "123 Boulevard"),
                new Employee(2L, "Nicolas", "Ahten", "589 North Street"),
                new Employee(3L, "Nikki", "Mentoza", "745 South Street")))

        employeeService.getAllEmployees() >> employees
        def employeeController = new EmployeeController(employeeService)

        when:
        def resData = employeeController.getEmployees()
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/employees")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        resData.get(0).getEmployeeId() == 1
        resData.get(0).getEmployeeName() == "John"
    }

    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        EmployeeServiceImpl employeeServiceImpl() {
            return detachedMockFactory.Stub(EmployeeServiceImpl)
        }
    }
}
