package com.itwill.springboot2.web;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    
    private final EmployeeService empSvc;
    
    @GetMapping("/list")
    public void empList(Model model) {
        log.info("empList()");
        
        
        List<Employee> employees = empSvc.getEmployeeList();
        model.addAttribute("employees", employees);
//        log.info("employees={}", employees);
        
    }
    
    @GetMapping("/details")
    public void empDetails(@RequestParam(name = "id") int id, Model model) {
        log.info("empDetails(id={})", id);
        
        Employee employee= empSvc.getEmployeeDetails(id);
        log.info("employee detail={}", employee);
        
        model.addAttribute("employee", employee);
        
    }
    
    
}
