package com.itwill.spring2.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.spring2.dto.user.UserRegisterDto;
import com.itwill.spring2.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserRestController {
    
    private final UserService userService;
    
    @PostMapping("/isExistingUserid")
    public ResponseEntity<Integer> existingUser(@RequestBody UserRegisterDto dto) {
        log.debug("existingUser(userid={})", dto.getUserid());
        
        int result = userService.checkUserId(dto.getUserid());
        log.debug("result={}", result);
        
        return ResponseEntity.ok(result);
    }
    
    
}
