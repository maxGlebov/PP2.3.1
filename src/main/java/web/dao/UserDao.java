package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    void saveUser(User user);

    void editUser(User user);

    void deleteUser(long id);

    User getUserById(Long id);

    void addUser(User user);
}