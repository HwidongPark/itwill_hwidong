package com.itwill.springboot2.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class DepartmentRepositoryTest {
    
    @Autowired
    DepartmentRepository deptRepository;
    
    @Test
    public void deptRepoTest() {
        List<Department> list = deptRepository.findAll();
        
        for (Department e : list) {
            log.info("department={}", e);
        }
        
    }

    
}
