package com.itwill.spring2.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.spring2.domain.User;
import com.itwill.spring2.dto.user.UserRegisterDto;
import com.itwill.spring2.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 디스패쳐 서블릿에서 호출하는 메서드
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;
    
    @GetMapping("/signup")
    public void signup() {
        log.debug("GET - signup()");
    }
    
    
    @PostMapping("/signup")
    public String signUp(@ModelAttribute UserRegisterDto dto) {
        log.debug("POST - signup(dto={})", dto);
        
        int isExistingUserid = userService.checkUserId(dto.getUserid());
        
        if (isExistingUserid == 1) {
            return "/user/signup";
        } else {
            userService.create(dto);
        }
        
        
        return "/user/signin";
    }
    
    
    @GetMapping("/signin")
    public void signin() {
        log.debug("GET - signin()");
    }
    
    
    @PostMapping("/signin")
    public String signin(@ModelAttribute UserRegisterDto dto, HttpSession session, @RequestParam(name="target", defaultValue = "") String target) 
            throws UnsupportedEncodingException {
        log.debug("POST - signin(dto={}, session={}, target={})", dto, session, target);
        
        User user = userService.signIn(dto);
        log.debug("returned user = {}", user);
        
        if (user == null) { // 아이디와 비밀번호가 일치하는 사용자가 없는 경우 -> 로그인 실패
            return "redirect:/user/signin?result=f&target=" + URLEncoder.encode(target, "UTF-8"); // /는 context root를 의미
        }  else {    // 아이디와 비밀번호 모두 일치하는 사용자가 있는 경우 -> 로그인 성공
            // 세션에 로그인 사용자 정보를 저장
            session.setAttribute("signedInUser", user.getUserid());
            
            // 타겟 페이지로 이동
            return target.equals("") ? "redirect:/" : "redirect:/" + target;
        }
    }
    
    
    @GetMapping("/signout")
    public String signout(HttpSession session) {
        log.debug("signout(session={})", session);
        // 세션에 저장된 "signedInUser" 정보를 삭제.
        session.removeAttribute("signedInUser");
        
        // 세션을 만료시킴.
        session.invalidate();
        
        return "redirect:/";
    }
    
    
}
