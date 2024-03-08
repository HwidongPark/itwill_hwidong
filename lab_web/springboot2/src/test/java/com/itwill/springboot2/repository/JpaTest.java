package com.itwill.springboot2.repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.itwill.springboot2.domain.Department2;
import com.itwill.springboot2.domain.Employee2;
import com.itwill.springboot2.domain.Employee2Repository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class JpaTest {
    
    @Autowired private Employee2Repository empDao;
    
    
    @Test
    @Transactional
    public void test() {
        log.info("empDao={}", empDao);
        Assertions.assertNotNull(empDao);
                
//      List<Employee2> list = empDao.findByDepartmentDepartmentId(90);
//      
//      for (Employee2 e : list) {
//          log.info(e.toString());
//      }
        
        
//        List<Employee2> employees = empDao.findByDepartmentDepartmentNameIgnoreCase("it");
//        employees.forEach((emp) -> log.info(emp.toString()));
        
        
//        List<Employee2> list = empDao.findByLastName("OConnell");
//        list.forEach((e) -> log.info(e.toString()));
        
        
//        List<Employee2> list = empDao.findByLastNameLike("%C%");
//        list.forEach((e) -> log.info(e.toString()));
        
//        List<Employee2> list = empDao.findByLastNameLikeIgnoreCase("%C%");
//        list.forEach((e) -> log.info(e.toString()));
        
//        List<Employee2> list = empDao.findByLastNameLikeIgnoreCaseOrderByLastNameAsc("%iNg%");
//        list.forEach((e) -> log.info(e.toString()));
        
        
//        List<Employee2> list = empDao.findBySalaryGreaterThan(13100);
//        list.forEach((e) -> log.info(e.toString()));
        
//        List<Employee2> list = empDao.findBySalaryLessThan(2500);
//        list.forEach((e) -> log.info(e.toString()));
        
        
//        List<Employee2> list = empDao.findBySalaryBetween(2100., 2400.);
//        list.forEach((e) -> log.info(e.toString()));
        
        
//        List<Employee2> list = empDao.findByHireDateAfter(LocalDateTime.of(2008, 1, 1, 0, 0));
//        list.forEach((e) -> log.info(e.toString()));
        
//        List<Employee2> list = empDao.findByHireDateBefore(LocalDateTime.of(2002, 12, 31, 23, 59));
//        list.forEach((e) -> log.info(e.toString()));

//        List<Employee2> list = empDao.findByHireDateBetween(LocalDateTime.of(2002, 1, 1, 0, 0), LocalDateTime.of(2003, 1, 1, 0 , 0));
//        list.forEach((e) -> log.info(e.toString()));
        
//        List<Employee2> list = empDao.findByManagerEmployeeIdIsNull();
//        list.forEach((e) -> log.info(e.toString()));
        
//        List<Employee2> list = empDao.findByFirstNameAndLastName("Steven", "King");
//        List<Employee2> list = empDao.findByName("Steven", "King");
//        list.forEach((e) -> log.info(e.toString()));
        
        
//        List<Employee2> list = empDao.findByDepartmentId(90);
//        list.forEach((e) -> log.info(e.toString()));
        
        
//        List<Employee2> list = empDao.findByNameLike("ee");
//        list.forEach((e) -> log.info(e.toString()));
        
//        List<Employee2> list = empDao.findByDeparmentName("Sales");
//        list.forEach((e) -> log.info(e.toString()));
        
//        List<Employee2> list = empDao.findByCity("Toronto");
//        list.forEach((e) -> log.info(e.toString()));
        
//        List<Employee2> list = empDao.findByCountry("United States of America");
//        list.forEach((e) -> log.info(e.toString()));

        List<Employee2> list = empDao.findByCountrySalaryHireDate("United States of America", 10000, LocalDateTime.of(2003, 1, 1, 0, 0));
        list.forEach((e) -> log.info(e.toString()));

        
    }
    
    
}
