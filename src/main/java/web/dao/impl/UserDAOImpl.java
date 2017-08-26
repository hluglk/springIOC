package web.dao.impl;

import web.dao.UserDAO;
import web.entity.User;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findUser() {
        User user = new User();
        user.setUname("hello world");
        return user;
    }

    @Override
    public void update(User user) {
        System.out.println("***** update ***** : " + user.getUname());
    }
}
