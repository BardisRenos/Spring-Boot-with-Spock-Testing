package org.example.testExample.dao;

import org.example.testExample.resources.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(Long id);

    User findByLastName(String lastName);

    List<User> findByFirstName(String firstName);

}
