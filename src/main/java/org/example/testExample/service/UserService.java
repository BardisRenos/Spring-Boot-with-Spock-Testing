package org.example.testExample.service;

import org.example.testExample.exception.UserNotFoundException;
import org.example.testExample.resources.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id) throws UserNotFoundException;
    User getUserByLastName(String lastName);
    List<User> getUserByFirstName(String firstName);
}
