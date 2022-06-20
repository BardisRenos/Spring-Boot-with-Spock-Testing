package org.example.testExample.controller

import org.example.testExample.resources.Company
import org.example.testExample.resources.User
import org.example.testExample.service.Implementation.CompanyServiceImpl
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification
import spock.lang.Title
import spock.mock.DetachedMockFactory

import javax.servlet.http.HttpServletResponse

@WebMvcTest(controllers = CompanyController.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
@Title("Controller Layer Test Company Entity")
class CompanyControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    CompanyServiceImpl companyService

    def setup () {
        companyService = Spy(CompanyServiceImpl)
    }

    def "GetCompanies"() {
        given:
        List<Company> companies = new ArrayList<>(Arrays.asList(
                new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr"),
                new Company(2L, "Astek", "IT-Sector", "astek@esn-astek.fr"),
                new Company(3L, "ReiffesenBank", "Banking", "rb@banking.cz")))

        companyService.getAllCompanies() >> companies
        def companyController = new CompanyController(companyService)

        when:
        def resData = companyController.getCompanies()
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/companies")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        resData.get(0).getCompanyId() == 1
        resData.get(0).getCompanyName() == "Alten"
    }

    def "GetCompanyById"() {
        given:
        Company company = new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr")

        companyService.getCompanyById(1) >> company
        def companyController = new CompanyController(companyService)

        when:
        def resData = companyController.getCompanyById(1)
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company/1")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        resData.getCompanyName() == "Alten"
        resData.getCompanySector() == "IT-Sector"

    }

    def "GetCompanyByName"() {
        given:
        Company company = new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr")

        companyService.getCompanyByName("Alten") >> company
        def companyController = new CompanyController(companyService)

        when:
        def resData = companyController.getCompanyByName("Alten")
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company/companyName/Alten")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        resData.getCompanyName() == "Alten"
        resData.getCompanySector() == "IT-Sector"

    }

    def "GetCompanyUsers"() {
        given:
        Company company = new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr")
        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com"),
                new User(2L, "Omar", "Omar90", "Matter", "omar@gmail.com"),
                new User(3L, "Marc", "Marc97", "Better", "marc@gmail.com")))

        company.setUsers(users)
        companyService.getCompanyUsers("Alten") >> company
        def companyController = new CompanyController(companyService)

        when:
        def resData = companyController.getCompanyUsers("Alten")
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/company/users").param("companyName", "Alten")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        resData.getCompanyName() == "Alten"
        resData.getCompanySector() == "IT-Sector"
        resData.getUsers().size() == 3
        resData.getUsers().get(0).getFirstName() == "Renos"
        resData.getUsers().get(0).getLastName() == "Bardis"

    }

    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        CompanyServiceImpl userServiceImpl() {
            return detachedMockFactory.Stub(CompanyServiceImpl)
        }
    }
}
