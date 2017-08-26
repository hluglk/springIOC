package web.dao;

import web.entity.User;

public interface UserDAO {

    User findUser();

    void update(User user);

}
