package org.example.testExample.controller

import org.example.testExample.resources.User
import org.example.testExample.service.Implementation.UserServiceImpl
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

@WebMvcTest(controllers = UserController.class)
@Title("UserController unit testing")
@AutoConfigureMockMvc
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@Title("Controller Layer Test User Entity")
class UserControllerTest extends Specification {

    @Autowired
    MockMvc mockMvc

    UserServiceImpl userServiceImpl

    def setup () {
        userServiceImpl = Spy(UserServiceImpl)
    }

    def "GetUsers"() {
        given:
        List<User> userList = new ArrayList<>(Arrays.asList(new User(1l, "Renos", "Renos87", "Bardis", "renos@gmail.com"),
                new User(2l, "Giannis", "Giannis89", "Papas", "giannis@gmail.com"),
                new User(3l, "Nikos", "Nick78", "Nikolaidi", "nikos@gmail.com")))

        userServiceImpl.getAllUsers() >> userList
        def userController = new UserController(userServiceImpl)

        when:
        def resData = userController.getUsers()
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        resData.get(0).getFirstName() == "Renos"
        resData.get(0).getLastName() == "Bardis"
        resData.get(0).getEmail() == "renos@gmail.com"

        resData.get(1).getFirstName() == "Giannis"
        resData.get(1).getLastName() == "Papas"
        resData.get(1).getEmail() == "giannis@gmail.com"

    }

    def "GetUserById"() {
        given:
        def user1 = new User(1L, "Renos", "Renos87", "Bardis","renos@gmail.com")
        userServiceImpl.getUserById(1) >> user1
        def userController= new UserController(userServiceImpl)

        when:
        def resData = userController.getUserById(1L)
        def response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        resData.getFirstName() == "Renos"
        resData.getLastName() == "Bardis"
    }


    def "GetUsersByLastName"() {
        given:
        def user1 = new User(1L, "Renos", "Renos87", "Bardis","renos@gmail.com")
        userServiceImpl.getUserByLastName("Bardis") >> user1
        def userController= new UserController(userServiceImpl)

        when:
        def resData = userController.getUsersByLastName("Bardis")
        def response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/lastName/Bardis")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        resData.getFirstName() == "Renos"
        resData.getLastName() == "Bardis"

    }

    def "GetUsersByFirstName"() {
        given:
        List<User> userList = new ArrayList<>(Arrays.asList(new User(1l, "Renos", "Renos87", "Bardis", "renos@gmail.com"),
                new User(2l, "Giannis", "Giannis89", "Papas", "giannis@gmail.com"),
                new User(3l, "Nikos", "Nick78", "Nikolaidi", "nikos@gmail.com")))

        userServiceImpl.getUserByFirstName("Renos") >> userList
        def userController= new UserController(userServiceImpl)

        when:
        def resData = userController.getUsersByFirstName("Renos")
        def response = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/firstName/Renos")).andReturn()

        then:
        response.getResponse().status == HttpServletResponse.SC_OK

        and:
        resData.get(0).getFirstName() == "Renos"
        resData.get(0).getLastName() == "Bardis"

        resData.get(1).getFirstName() == "Giannis"
        resData.get(1).getLastName() == "Papas"

        resData.get(2).getFirstName() == "Nikos"
        resData.get(2).getLastName() == "Nikolaidi"
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
