package com.itwill.springboot2.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot2.domain.Department;
import com.itwill.springboot2.domain.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/department")
public class DepartmentController {
    
    private final DepartmentRepository deptDao;
    
    @GetMapping("/details")
    public void departmentDetails(@RequestParam(name = "id") int deptNo, Model model) {
        
        log.info("departmentDetails(deptNo={})", deptNo);
        
        Department dept = deptDao.findById(deptNo).orElse(null);
        log.info("dept={}", dept);
        log.info("직원 수 ={}", dept.getEmployees().size());
        model.addAttribute("department", dept);
    }
    
    
    @GetMapping("/list")
    public void departmentsList(Model model) {
        log.info("departmentsList()");
        
        List<Department> list = deptDao.findAll();
        
        model.addAttribute("departments", list);        
    }
    
}