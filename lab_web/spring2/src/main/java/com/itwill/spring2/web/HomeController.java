package com.itwill.spring2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller // 컨트롤러 컴포넌트
public class HomeController {
    
    
    @GetMapping("/")
    public String home() {
        
        log.debug("home() spring2");
        
        return "home";  // 뷰(JSP)의 경로(이름)을 리턴.
    }
    
    
}
