package com.itwill.jsp2.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserTest {
    private static final Logger log = LoggerFactory.getLogger(UserTest.class);
    
    
    @Test
    public void Test() {
        User admin = User.builder().userid("admin")
                    .password("1234").build();
        
        
        Assertions.assertEquals("admin", admin.getUserid());
        Assertions.assertEquals("1234", admin.getPassword());
        log.debug("admin={}", admin);
        
        
        
        
    }
    
    
}
