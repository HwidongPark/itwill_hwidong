package com.itwill.springboot2.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot2.domain.Department2;
import com.itwill.springboot2.domain.Department2Repository;
import com.itwill.springboot2.dto.PagingDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/department2")
public class Department2Controller {
    
    private final Department2Repository department2Dao;
    private final Employee2Controller employee2Controller;
    
    @GetMapping("/list")
    public void department2List(Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        log.info("department2List()");
        
                
        
//        List<Department2> list = department2Dao.findAll();
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.by(Direction.DESC, "departmentId"));
        Page<Department2> list = department2Dao.findAll(pageable);
        
        long totNumDepts = department2Dao.count();
        
        PagingDto pagingDto = employee2Controller.paging(totNumDepts, page, 3);
        
        model.addAttribute("departments", list);
        model.addAttribute("pagingDto", pagingDto);
        
    }
    
    @GetMapping("/details")
    public void department2Details(@RequestParam(name="id")int id, Model model) {
        log.info("department2Details(id={})", id);
        
        Department2 department = department2Dao.findById(id).orElse(null);
        
        model.addAttribute("department", department);
    }
    
}