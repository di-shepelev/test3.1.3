package ru.kata.spring.boot_security.demo.services;







import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserServise {

    void addUser(User user);

    List<User> listUsers();

    void updateUser(User user);

    void removeUser(int id);

    User getUserById(int id);

    User findByUsername(String username);

}
