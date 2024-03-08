package com.itwill.springboot2.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.springboot2.domain.Country2;
import com.itwill.springboot2.domain.Department2;
import com.itwill.springboot2.domain.Employee2;
import com.itwill.springboot2.domain.Employee2Repository;
import com.itwill.springboot2.domain.Location2;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
public class JpaExampleTest {
    
    
    @Autowired private Employee2Repository empDao;
    
    @Test
    public void test() {
        
        // lastName이 King인 직원(들)의 정보 검색
        // Example타입에 검색하고싶은 정보들만 넣어주면 됨
        // Example은 where조건을 검색하기 위해 필요한 정보를 가지고 있는 예제를 넣어주는 것임
        
//        Employee2 emp = new Employee2();
//        emp.setLastName("King");
//        
//        Example<Employee2> example = Example.of(emp);
        
//        Employee2 emp = new Employee2();
//        Department2 department = new Department2();
//        emp.setDepartment(department);
//        emp.getDepartment().setDepartmentName("IT");
//        
//        Example<Employee2> example = Example.of(emp);
//        
//        Pageable pageable = PageRequest.of(1, 3, Sort.by(Sort.Direction.DESC, "employeeId"));
//        
//        Page<Employee2> list = empDao.findAll(example, pageable);
//        list.forEach((e) -> log.info(e.toString()));
//        
//        log.info("number(현재 페이지)={}, # of elements(그 페이지의 포스트 수)={}, Total Elements={}", list.getNumber()
//                , list.getNumberOfElements(), list.getTotalElements());
        
        

        
//        List<Employee2> list = getByCity("Toronto");
//        list.forEach((e) -> log.info(e.toString()));
        
        List<Employee2> list = getByCountry("United States of America");
        list.forEach((e) -> log.info(e.toString()));

        
        
        
        
    }
    
    
    // 특정 도시에서 근무하는 직원들
    public List<Employee2> getByCity(String city) {
        Employee2 emp = new Employee2();
        Department2 department = new Department2();
        Location2 location = new Location2();
        location.setCity(city);
        department.setLocation(location);
        
        emp.setDepartment(department);
        
        Example<Employee2> example = Example.of(emp);
        
        List<Employee2> list = empDao.findAll(example);
        
        return list;
    }
    
    
    // 특정 나라에서 근무하는 직원들
    public List<Employee2> getByCountry(String country) {
        Employee2 emp = new Employee2();
        Department2 department = new Department2();
        Location2 location = new Location2();
        Country2 country2 = new Country2();
        
        country2.setCountryName(country);
        location.setCountry(country2);
        department.setLocation(location);
        
        emp.setDepartment(department);
        
                
        Example<Employee2> example = Example.of(emp);
        
        List<Employee2> list = empDao.findAll(example);
        
        return list;
    }
    
}