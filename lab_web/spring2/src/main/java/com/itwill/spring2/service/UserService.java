package com.itwill.spring2.service;

import org.springframework.stereotype.Service;

import com.itwill.spring2.domain.User;
import com.itwill.spring2.dto.user.UserRegisterDto;
import com.itwill.spring2.repository.UserDao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    
    private final UserDao userDao;
    
    public int checkUserId(String userId) {
        log.debug("checkUserid(userid={})", userId);
        
        User user = userDao.selectByUserId(userId);
        if (user == null) { // USERS 테이블에 유저 아이디가 없는 경우 -> 회원가입에서 사용 가능한 아이디
            return 0;
        } else {    // USERS 테이블에 유저 아이디가 있는 경우 --> 회원가입에서 사용 불가능한 아이디
            return 1;
        }
        
    }
    
    
    public int create(UserRegisterDto dto) {
        log.debug("create(dto={})", dto);
        
        int result = userDao.insert(dto.toEntity());
        log.debug("회원가입 결과 = {}", result);
        
        return result;
    }
    
    public User signIn(UserRegisterDto dto) {
        log.debug("signIn(dto={})", dto);
        
        User userToCheck = User.builder().userid(dto.getUserid()).password(dto.getPassword()).build();
        
        User user = userDao.signIn(userToCheck);
        log.debug("user={}", user);
        
        return user;
    }
    
}
