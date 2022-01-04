package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    void add(User user);

    void remove(long id);

    void edit(User user);

    User getById(long id);

    List<User> allUser();
}
