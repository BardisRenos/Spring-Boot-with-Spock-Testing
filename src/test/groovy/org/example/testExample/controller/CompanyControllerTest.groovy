package org.example.testExample.controller

import org.example.testExample.resources.Company
import org.example.testExample.service.Implementation.CompanyServiceImpl
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse

@WebMvcTest(controllers = CompanyController.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
class CompanyControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    CompanyServiceImpl companyService

    @Autowired
    private ApplicationContext applicationContext;

    def setup () {
        companyService = Spy(CompanyServiceImpl)
    }

    def "GetCompanies"() {
        List<Company> companies = new ArrayList<>(Arrays.asList(
                new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr"),
                new Company(2L, "Astek", "IT-Sector", "astek@esn-astek.fr"),
                new Company(3L, "ReiffesenBank", "Banking", "rb@banking.cz")));

        companyService.getAllCompanies() >> companies
        def companyController = new CompanyController(companyService)

        when:
        def res = companyController.getCompanies()
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/companies")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        res.get(0).getCompanyId() == 1
        res.get(0).getCompanyName() == "Alten"
    }

    def "GetCompanyById"() {
        Company company = new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr")

        companyService.getCompanyById(1) >> company
        def companyController = new CompanyController(companyService)

        when:
        def res = companyController.getUserById(1)
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/company/1")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        res.getCompanyName() == "Alten"
        res.getCompanySector() == "IT-Sector"

    }

    def "GetCompanyByName"() {
        Company company = new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr")

        companyService.getCompanyByName("Alten") >> company
        def companyController = new CompanyController(companyService)

        when:
        def res = companyController.getCompanyByName("Alten")
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/company/companyName/Alten")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        res.getCompanyName() == "Alten"
        res.getCompanySector() == "IT-Sector"

    }

}
