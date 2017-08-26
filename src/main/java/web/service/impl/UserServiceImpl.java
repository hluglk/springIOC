package web.service.impl;

import web.dao.UserDAO;
import web.entity.User;
import web.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User findUser() {
        return userDAO.findUser();
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }
}
