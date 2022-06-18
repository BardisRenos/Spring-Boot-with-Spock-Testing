package org.example.testExample.controller;

import lombok.RequiredArgsConstructor;
import org.example.testExample.exception.UserNotFoundException;
import org.example.testExample.resources.User;
import org.example.testExample.service.Implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class UserController {
    
    private final UserServiceImpl userServiceImpl;

    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable(value = "id") Long id) throws UserNotFoundException {
        return userServiceImpl.getUserById(id);
    }

    @GetMapping(value = "/user/lastName/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public User getUsersByLastName(@PathVariable(value = "lastName") String lastName) throws UserNotFoundException {
        return userServiceImpl.getUserByLastName(lastName);
    }

    @GetMapping(value = "/user/firstName/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByFirstName(@PathVariable(value = "firstName") String firstName) throws UserNotFoundException {
        return userServiceImpl.getUserByFirstName(firstName);
    }

}
