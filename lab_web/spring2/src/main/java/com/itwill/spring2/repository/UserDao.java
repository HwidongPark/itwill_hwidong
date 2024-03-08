package com.itwill.spring2.repository;

import com.itwill.spring2.domain.User;

public interface UserDao {
    
    User selectByUserId (String userid);
    int insert(User user);
    User signIn(User user);
    
}
