package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void delUser(Long userId);

    void changeUser(User user);

    User findUserById(Long id);

    List<User> listUser();
}
