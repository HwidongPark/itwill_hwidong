package com.itwill.spring2.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.spring2.domain.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserDaoTest {
    
    @Autowired private UserDao userDao;
    
    @Test
    public void userInsertTest() {
        
        User user = userDao.selectByUserId("admin");
        log.debug("user={}", user);
        Assertions.assertNotNull(user);
    }

}
