package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    void remove(long id);

    void edit(User user);

    User getById(long id);

    List<User> allUser();


}
