package web.service;

import web.entity.User;

public interface UserService {
    User findUser();
    void update(User user);
}
