package com.itwill.springboot2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.domain.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class HomeController {
    
    private final EmployeeRepository employeeRepo;
    
    @GetMapping("/")
    public String home() {
        log.info("home()");
        
        return "index"; // 뷰의 이름: templates/index.html
    }
    
    
}
