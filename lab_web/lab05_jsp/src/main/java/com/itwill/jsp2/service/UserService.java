package com.itwill.jsp2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itwill.jsp2.domain.User;
import com.itwill.jsp2.dto.UserSignInDto;
import com.itwill.jsp2.dto.UserSignUpDto;
import com.itwill.jsp2.repository.UserDao;


public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserDao dao = UserDao.getInstance();
    
    private static UserService instance = null;
    
    
    private UserService() {}
    
    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        
        return instance;
    }
    
    public int signUp(UserSignUpDto dto) {
        log.debug("signUp(dto={}", dto);
        
        int result = dao.insert(dto.toUser());
        
        return result;
    }
    
    
    public User signIn(UserSignInDto dto) {
        // TODO: userid와 password가 일치하면 true, 그렇지 않으면 false를 리턴
        log.debug("dto={}", dto);
        
        User user = dao.selectByUserIdAndPassword(dto);
        
        return user;
    }
    
    
    
    
    
}
