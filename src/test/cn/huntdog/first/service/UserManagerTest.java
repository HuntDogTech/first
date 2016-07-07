package cn.huntdog.first.service;

import cn.huntdog.first.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by JonDai on 2016/7/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = cn.huntdog.first.Applicationtest.class)
public class UserManagerTest {
    @Autowired
    private UserManager userManager;

    @Test
    public void register(){
        User user = new User();
        user.setUserName("jondai");
        user.setPassword("123456");
        userManager.register(user);
    }


    @Test
    public void login() throws Exception {
        User user = new User();
        user.setUserName("jondai");
        user.setPassword("123456");
        boolean isExtis = userManager.login(user);
        if(isExtis){
            System.out.println("Login...");
        }else{
            System.out.println("用户名或者密码错误...");
        }
    }
}
