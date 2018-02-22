package com.java;

import com.java.dto.User;
import com.java.repository.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by iss on 18/2/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-context.xml"})
public class MyBatisTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        User user = userMapper.getUserById(1);
        System.out.println();
    }
}
