package org.example.testExample.service

import org.example.testExample.dao.CompanyRepository
import org.example.testExample.resources.Company
import org.example.testExample.resources.User
import org.example.testExample.service.Implementation.CompanyServiceImpl
import spock.lang.Specification
import spock.lang.Title

@Title("Service Layer Test Company Entity")
class CompanyServiceImplTest extends  Specification {

    def "GetAllCompanies"() {
        setup:
        List<Company> companies = new ArrayList<>(Arrays.asList(
                new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr"),
                new Company(2L, "Astek", "IT-Sector", "astek@esn-astek.fr"),
                new Company(3L, "ReiffesenBank", "Banking", "rb@banking.cz")))

        def companyRepositoryMock = Spy(CompanyRepository)
        companyRepositoryMock.findAll() >> companies
        def companyService = new CompanyServiceImpl(companyRepositoryMock)

        when:
        List<Company> res = companyService.getAllCompanies()

        then:
        res.size() == 3
        res.get(0).getCompanyId() == 1
        res.get(0).getCompanyName() == "Alten"
        res.get(0).getCompanySector() == "IT-Sector"

        res.get(1).getCompanyId() == 2
        res.get(1).getCompanyName() == "Astek"
        res.get(1).getCompanySector() == "IT-Sector"

        res.get(2).getCompanyId() == 3
        res.get(2).getCompanyName() == "ReiffesenBank"
        res.get(2).getCompanySector() == "Banking"
    }

    def "GetCompanyById"() {
        setup:
        Company company = new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr")

        def companyRepositoryMock = Spy(CompanyRepository)
        companyRepositoryMock.findByCompanyId(1L) >> company
        def companyService = new CompanyServiceImpl(companyRepositoryMock)

        when:
        Company res = companyService.getCompanyById(1L)

        then:
        res.getCompanyId() == 1
        res.getCompanyName() == "Alten"
        res.getCompanySector() == "IT-Sector"
    }


    def "GetCompanyByName"() {
        setup:
        Company company = new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr")

        def companyRepositoryMock = Spy(CompanyRepository)
        companyRepositoryMock.findByCompanyName("Alten") >> company
        def companyService = new CompanyServiceImpl(companyRepositoryMock)

        when:
        Company res = companyService.getCompanyByName("Alten")

        then:
        res.getCompanyId() == 1
        res.getCompanyName() == "Alten"
        res.getCompanySector() == "IT-Sector"
    }

    def "GetCompanyUsers"() {
        setup:
        Company company = new Company(1L, "Alten", "IT-Sector", "alten@esn-alten.fr")
        List<User> users = new ArrayList<>(Arrays.asList(
                new User(1L, "Renos", "Renos87", "Bardis", "renos@gmail.com"),
                new User(2L, "Omar", "Omar90", "Matter", "omar@gmail.com"),
                new User(3L, "Marc", "Marc97", "Better", "marc@gmail.com")))

        company.setUsers(users)

        def companyRepositoryMock = Spy(CompanyRepository)
        companyRepositoryMock.findByCompanyNameAndUser("Alten") >> company
        def companyService = new CompanyServiceImpl(companyRepositoryMock)

        when:
        Company res = companyService.getCompanyUsers("Alten")

        then:
        res.getCompanyId() == 1
        res.getCompanyName() == "Alten"
        res.getUsers().size() == 3
        res.getUsers().get(0).getFirstName() == "Renos"
        res.getUsers().get(0).getLastName() == "Bardis"
        res.getUsers().get(1).getFirstName() == "Omar"
        res.getUsers().get(1).getLastName() == "Matter"
    }
}
