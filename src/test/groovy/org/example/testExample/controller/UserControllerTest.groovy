package org.example.testExample.controller

import org.example.testExample.resources.User
import org.example.testExample.service.UserServiceImpl
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
import spock.lang.Unroll
import spock.mock.DetachedMockFactory

import javax.servlet.http.HttpServletResponse

@WebMvcTest(controllers = UserController.class)
@Title("UserController unit testing")
@AutoConfigureMockMvc
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class UserControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    UserServiceImpl userServiceImpl

    def setup () {
        userServiceImpl = Spy(UserServiceImpl)
    }

    def "GetUsers"() {
        given:
        def user1 = new User(1L, "Renos", "Renos87", "Bardis","renos@gmail.com")
        def user2 = new User(2L, "Nikos", "Nikos85", "Papas","Nikos@gmail.com")
        userServiceImpl.getAllUsers() >> [user1, user2]

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/users")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK
    }

    def "GetUserById"() {
        given:
        def user1 = new User(1L, "Renos", "Renos87", "Bardis","renos@gmail.com")
        userServiceImpl.getUserById(1) >> user1

        when:
        def response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/user/1")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK
    }


    def "GetUsersByLastName"() {
        given:
        def user1 = new User(1L, "Renos", "Renos87", "Bardis","renos@gmail.com")
        userServiceImpl.getUserByLastName("Bardis") >> user1

        when:
        def response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/user/lastName/Bardis")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

    }

    def "GetUsersByFirstName"() {
        given:
        List<User> resList = new ArrayList<>()
        def user1 = new User(1L, "Renos", "Renos87", "Bardis","renos@gmail.com")
        def user2 = new User(2L, "Nikos", "Nikos85", "Papas","Nikos@gmail.com")
        resList.add(user1)
        resList.add(user2)
        userServiceImpl.getUserByFirstName("Renos") >> resList

        when:
        def response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/user/firstName/Renos")).andReturn()

        then:
        response.getResponse().status == HttpServletResponse.SC_OK
    }

    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        UserServiceImpl userServiceImpl() {
            return detachedMockFactory.Stub(UserServiceImpl)
        }
    }
}
