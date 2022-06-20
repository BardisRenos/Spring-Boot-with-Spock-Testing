package org.example.testExample.service

import org.example.testExample.dao.UserRepository
import org.example.testExample.resources.User
import org.example.testExample.service.Implementation.UserServiceImpl
import spock.lang.Specification
import spock.lang.Title

@Title("Service Layer Test User Entity")
class UserServiceImplTest extends Specification {

    def "GetAllUsers"() {
        setup:
        List<User> userList = new ArrayList<>(Arrays.asList(new User(1l, "Renos", "Bardis", "Renos87", "renos@gmail.com"),
        new User(2l, "Giannis", "Papas", "Giannis89", "giannis@gmail.com"),
        new User(3l, "Nikos", "Nikolaidi", "Nick78", "nikos@gmail.com")))

        def userRepositoryMock = Spy(UserRepository)
        userRepositoryMock.findAll() >> userList
        def userService = new UserServiceImpl(userRepositoryMock)

        when:
        List<User> res = userService.getAllUsers()

        then:
        notThrown(IllegalArgumentException)
        res.size() == 3
        res.get(0).getFirstName() == "Renos"
        res.get(1).getFirstName() == "Giannis"
        res.get(2).getFirstName() == "Nikos"
    }

    def "GetUserById"() {
        setup:
        User user = new User(1l, "Renos", "Renos87", "Bardis", "renos@gmail.com")

        def userRepositoryMock = Spy(UserRepository)
        userRepositoryMock.findByUserId(1) >> user
        def userService = new UserServiceImpl(userRepositoryMock)

        when:
        User res = userService.getUserById(1)

        then:
        notThrown(IllegalArgumentException)
        res.getUserId() == 1L
        res.getFirstName() == "Renos"
        res.getLastName() == "Bardis"
    }

    def "GetUserByLastName"() {
        setup:
        User user = new User(1l, "Renos", "Renos87", "Bardis", "renos@gmail.com")

        def userRepositoryMock = Spy(UserRepository)
        userRepositoryMock.findByLastName("Bardis") >> user
        def userService = new UserServiceImpl(userRepositoryMock)

        when:
        User res = userService.getUserByLastName("Bardis")

        then:
        notThrown(IllegalArgumentException)
        res.getUserId() == 1L
        res.getFirstName() == "Renos"
        res.getLastName() == "Bardis"
    }

    def "GetUserByFirstName"() {
        setup:
        List<User> userList = new ArrayList<>(Arrays.asList(new User(1l, "Renos", "Renos87", "Bardis", "renos@gmail.com"),
                new User(2l, "Renos", "RenosPap85", "Papas", "renoPap@gmail.com")))

        def userRepositoryMock = Spy(UserRepository)
        userRepositoryMock.findByFirstName("Renos") >> userList
        def userService = new UserServiceImpl(userRepositoryMock)

        when:
        List<User> res = userService.getUserByFirstName("Renos")

        then:
        notThrown(IllegalArgumentException)
        res.get(0).getUserId() == 1L
        res.get(0).getFirstName() == "Renos"
        res.get(0).getLastName() == "Bardis"
    }
}
