package com.SpringMicro.DepartmentService.service;

import com.SpringMicro.DepartmentService.entity.Department;
import com.SpringMicro.DepartmentService.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    DepartmentRepository departmentRepository;
    Department department;

    DepartmentService departmentService;

    @BeforeEach
    public void setup(){
        departmentService=new DepartmentService(departmentRepository);
        department=new Department("AI","building no 3");
    }

    @Test
    void createDepartmentTest() {
        mock(Department.class);
        when(departmentRepository.save(department)).thenReturn(department);
        assertEquals(department.getName(),departmentService.createDepartment(department).getName());
    }
    @Test
    void createDepartmentTest_null() {
        mock(Department.class);
        when(departmentRepository.save(department)).thenReturn(null);
        assertNull(departmentService.createDepartment(department));
    }

    @Test
    void getDepartmentById_test() {
        mock(Department.class);
        when(departmentRepository.getDepartmentById(anyLong())).thenReturn(department);
        assertEquals(department.getName(),departmentService.getDepartmentById(1l).getName());
    }

    @Test
    void getDepartment() {
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department));
        assertEquals(1,departmentService.getDepartment().size());
    }
    @Test
    void getDepartment_Empty() {
        when(departmentRepository.findAll()).thenReturn(null);
        assertNull(departmentService.getDepartment());
    }

    @Test
    void dropDepartmentById() {
        departmentService.dropDepartmentById(1L);
        verify(departmentRepository,times(1)).deleteById(any());
    }
}