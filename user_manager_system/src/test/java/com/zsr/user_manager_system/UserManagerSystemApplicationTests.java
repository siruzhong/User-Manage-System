package com.zsr.user_manager_system;

import com.zsr.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserManagerSystemApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.findAll());
    }

}
