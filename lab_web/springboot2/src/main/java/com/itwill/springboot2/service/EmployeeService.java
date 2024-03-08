package com.itwill.springboot2.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.itwill.springboot2.domain.Employee;
import com.itwill.springboot2.domain.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {
    
    private final EmployeeRepository empDao;
    
    public List<Employee> getEmployeeList() {
        log.info("getEmployeeList()");
        
        List<Employee> empList = empDao.findAll();
        log.info("employeeList={}", empList);
        
        return empList;
    }
    
    
    public Employee getEmployeeDetails(int id) {
        log.info("getEmployeeDetails(id={})", id);
        
        Employee employee = empDao.findById(id).orElse(null);
//        log.info("employee={}", employee);
        
        return employee;
    }
    
}
