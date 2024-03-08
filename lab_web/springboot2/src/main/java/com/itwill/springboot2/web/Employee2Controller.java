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
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.springboot2.domain.Employee2;
import com.itwill.springboot2.domain.Employee2Repository;
import com.itwill.springboot2.dto.PagingDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class Employee2Controller {
    
    private final Employee2Repository employee2Dao;
    private int pagesInBar = 3;
    private int postsPerPage = 10;
    
    public PagingDto paging(long totPosts, int page, int pagesInBar) {
                
        int startPage = (int) (Math.ceil((double) page / pagesInBar) - 1)* pagesInBar + 1;
        int endPage = startPage + pagesInBar - 1;
        int totPages = (int) Math.ceil((double) totPosts / postsPerPage);
        
        return PagingDto.builder().page(page).endPage(endPage).startPage(startPage).totPages(totPages).pagesInBar(pagesInBar).build();
    }
    
    @GetMapping("/employee2/list")
    public void emp2List(Model model,  @RequestParam(name = "page", required = false, defaultValue = "1") int page, Sort sort) {
        Pageable pageable = PageRequest.of(page - 1, postsPerPage, Sort.by(Direction.DESC, "employeeId"));
        Page<Employee2> pagedList = employee2Dao.findAll(pageable);
        
//        List<Employee2> employees =  employee2Dao.findAll();
        long totPosts = employee2Dao.count();
        log.info("totPosts={}", totPosts);
        
        PagingDto pagingDto = paging(totPosts, page, pagesInBar);
        log.info("pagingDto={}", pagingDto);
        model.addAttribute("employees", pagedList);
        model.addAttribute("pagingDto", pagingDto);
    }
    
    
    @GetMapping("/employee2/details")
    public void emp2Details(@RequestParam(name = "id")int id, Model model) {
        log.info("emp2Details(id={})", id);
        
        Employee2 employee = employee2Dao.findById(id).orElse(null);
        
        model.addAttribute("employee", employee);
        
    }
    
}
