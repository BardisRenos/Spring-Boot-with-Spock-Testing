# Spring-Boot-with-Spock-Testing

In this repo will demostrate how to use Spock unit test on Groovy with Spring Boot framework. The application is a back-end RestAPI application. It is structured into Controller, Service and Resource layer. The application has 3 Rest end points. 

This repository is using:
1. Java Version 11
2. Spring Boot 2.7
3. Lombok library
4. JpaData
5. Spock Groovy

### Building the Data entity layer.

```java
  @Table(name = "users")
  public class User {

      @Id
      @Column(name = "user_id", updatable = false, nullable = false)
      private Long userId;
      @Column(name = "first_name")
      private String firstName;
      @Column(name = "user_name")
      private String userName;
      @Column(name = "last_name")
      private String lastName;
      @Column(name = "email", nullable = false, unique = true)
      private String email;
  }

```

###  Building the Controller layer.

The end points of the application.

```java
    @GetMapping(value = "/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userServiceImpl.getAllUsers();
    }
    
    @GetMapping(value = "/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable(value = "id") Long id) {
    return userServiceImpl.getUserById(id);
    }

    @GetMapping(value = "/user/lastName/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public User getUsersByLastName(@PathVariable(value = "lastName") String lastName) {
        return userServiceImpl.getUserByLastName(lastName);
    }
    
    @GetMapping(value = "/user/firstName/{firstName}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsersByFirstName(@PathVariable(value = "firstName") String firstName) {
        return userServiceImpl.getUserByFirstName(firstName);
    }
    
```

###  Building the Service layer.

The following.

```java
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public User getUserByLastName(String lastName) {
        return userRepository.findByLastName(lastName);
    }

    @Override
    public List<User> getUserByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }

```

### DAO layer

This layer is the place to make the queries on the database

```java
  @Repository
  public interface UserRepository extends JpaRepository<User, Long> {

      User findByUserId(Long id);

      User findByLastName(String lastName);

      List<User> findByFirstName(String firstName);

```

### Testing

In this part, is the testing the methods of the Repository layer and Service layer.

```java 
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
```





