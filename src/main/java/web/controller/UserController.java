package web.controller;

import peret.BeanFactory;
import peret.ClassPathJSONApplicationContext;
import web.entity.User;
import web.service.UserService;
import org.junit.Test;

public class UserController {

    @Test
    public void updateUserName() {
        BeanFactory factory = new ClassPathJSONApplicationContext("src/main/resources/bean.json");
        UserService userService = (UserService) factory.getBean("userService");
        User user =  userService.findUser();
        System.out.println(user.getUname());
        user.setUname("Lee");
        userService.update(user);
    }

}
