package com.SpringMicro.DepartmentService.repository;

import com.SpringMicro.DepartmentService.entity.Department;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;

    Department department;

    @BeforeEach
    public void setUp(){
        department= new Department("computerScience","building number 3");
        departmentRepository.save(department);
    }
    @Test
    public void getDepartmentByIdTest(){
        Department dept= departmentRepository.getDepartmentById(1L);
        assertNotNull(dept);
        assertEquals("computerScience",dept.getName());
    }

    @Test
    public void getDepartmentByIdTest_NotFound(){

        assertThrows(NullPointerException.class,()->{
            String name=departmentRepository.getDepartmentById(50l).getName();
        });
    }
    @AfterEach
    public void tearDown(){
        department=null;
        departmentRepository.deleteAll();
    }
}
