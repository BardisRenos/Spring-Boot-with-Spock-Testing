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

The following layer is the manipulation of the data. 

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

This layer is the place to make the queries on the database tables.

```java
  @Repository
  public interface UserRepository extends JpaRepository<User, Long> {

      User findByUserId(Long id);

      User findByLastName(String lastName);

      List<User> findByFirstName(String firstName);

```

### Testing

In this part, is the testing the methods of the Repository layer and Service layer.

1. This is for the Service layer.

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

2. This is for the Repository layer

This part initialize the classes that we need.

```java
    @Autowired
    MockMvc mockMvc

    UserServiceImpl userServiceImpl

    def setup () {
        userServiceImpl = Spy(UserServiceImpl)
    }
```

The code here test the URL path if it works properly. 

```java
    def "GetUsers"() {
        given:
        List<User> userList = new ArrayList<>(Arrays.asList(new User(1l, "Renos", "Renos87", "Bardis", "renos@gmail.com"),
                new User(2l, "Giannis", "Giannis89", "Papas", "giannis@gmail.com"),
                new User(3l, "Nikos", "Nick78", "Nikolaidi", "nikos@gmail.com")))

        userServiceImpl.getAllUsers() >> userList
        def userController= new UserController(userServiceImpl)

        when:
        def res = userController.getUsers()
        def response = mockMvc.perform(MockMvcRequestBuilders.get("/api/1.0/users")).andReturn().getResponse()

        then:
        response.status == HttpServletResponse.SC_OK

        and:
        res.get(0).getFirstName() == "Renos"
        res.get(0).getLastName() == "Bardis"
        res.get(0).getEmail() == "renos@gmail.com"

        res.get(1).getFirstName() == "Giannis"
        res.get(1).getLastName() == "Papas"
        res.get(1).getEmail() == "giannis@gmail.com"
    }


```

