package org.example.testExample.service;

import lombok.RequiredArgsConstructor;
import org.example.testExample.dao.UserRepository;
import org.example.testExample.exception.UserNotFoundException;
import org.example.testExample.resources.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) throws UserNotFoundException {
        if (Objects.isNull(userRepository.findByUserId(id))) {
            throw new UserNotFoundException(String.format("The user not found with the id: %s ", id));
        }
        return userRepository.findByUserId(id);
    }

    @Override
    public User getUserByLastName(String lastName) throws UserNotFoundException {
        if (Objects.isNull(userRepository.findByLastName(lastName))) {
            throw new UserNotFoundException(String.format("The user not found with the last name: %s ", lastName));
        }
        return userRepository.findByLastName(lastName);
    }

    @Override
    public List<User> getUserByFirstName(String firstName) throws UserNotFoundException {
        if (Objects.isNull(userRepository.findByFirstName(firstName))) {
            throw new UserNotFoundException(String.format("The user not found with the first name: %s ", firstName));
        }
        return userRepository.findByFirstName(firstName);
    }
}
