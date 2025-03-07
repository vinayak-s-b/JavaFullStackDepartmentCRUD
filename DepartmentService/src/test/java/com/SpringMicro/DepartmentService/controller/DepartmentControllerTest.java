package com.SpringMicro.DepartmentService.controller;

import com.SpringMicro.DepartmentService.entity.Department;
import com.SpringMicro.DepartmentService.service.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class DepartmentControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    DepartmentService departmentService;

    Department departmentOne;
    Department departmentTwo;
    List<Department> departmentList=new ArrayList<>();

    @BeforeEach
    void setUp() {
        departmentOne=new Department("CS","building 1");
        departmentTwo=new Department("AI","building 2");
        departmentList.add(departmentOne);
        departmentList.add(departmentTwo);
    }


    @AfterEach
    void tearDown() {

    }

    @Test
    void createDepartmentTest() throws Exception {

        ObjectMapper ob= new ObjectMapper();
        String requestJson = ob.writeValueAsString(departmentOne);

        when(departmentService.createDepartment(any())).thenReturn(departmentOne);
        this.mockMvc.perform(post("/departments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isCreated()).andExpect(content().string("Department Created Successfully"));
    }
    @Test
    void createDepartmentTest_NotCreated() throws Exception {
        ObjectMapper ob= new ObjectMapper();
        String requestJson = ob.writeValueAsString(departmentOne);

        when(departmentService.createDepartment(any())).thenReturn(null);
        this.mockMvc.perform(post("/departments/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isBadRequest()).
                andExpect(content().string("Failed to Create Department"));
    }

    @Test
    void getDepartmentTest() throws Exception {
        when(departmentService.getDepartment()).thenReturn(departmentList);
        this.mockMvc.perform(get("/departments/get")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getDepartmentByIdTest() throws Exception {

        when(departmentService.getDepartmentById(anyLong())).thenReturn(departmentOne);
        this.mockMvc.perform(get("/departments/get/1")).andDo(print()).andExpect(status().isOk());
     }
    @Test
    void getDepartmentByIdTest_NotFound() throws Exception {

        when(departmentService.getDepartmentById(anyLong())).thenReturn(null);
        this.mockMvc.perform(get("/departments/get/1")).andDo(print()).andExpect(status().isNotFound());

    }
    @Test
    void updateDepartmentTest() throws Exception {
        departmentOne.setId(1L);
        ObjectMapper mapper=new ObjectMapper();
        String requestJson=mapper.writeValueAsString(departmentOne);

        when(departmentService.getDepartmentById(anyLong())).thenReturn(departmentOne);
        when(departmentService.createDepartment(any())).thenReturn(departmentOne);

        this.mockMvc.perform(put("/departments/update").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print()).andExpect(status().isOk()).andExpect(content().string("Department Updated Successfully"));
    }
    @Test
    void updateDepartmentTest_notFound() throws Exception {
        departmentOne.setId(1L);
        ObjectMapper mapper=new ObjectMapper();
        String requestJson=mapper.writeValueAsString(departmentOne);

        when(departmentService.getDepartmentById(anyLong())).thenReturn(null);
        this.mockMvc.perform(put("/departments/update").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print()).andExpect(status().isNotFound()).andExpect(content().string("failed to Update Department"));
    }
    @Test
    void deleteDepartmentByID() throws Exception {
        when(departmentService.getDepartmentById(anyLong())).thenReturn(departmentOne);
        doNothing().when(departmentService).dropDepartmentById(anyLong());
        this.mockMvc.perform(delete("/departments/delete/1")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void deleteDepartmentByID_NotFound() throws Exception {
        when(departmentService.getDepartmentById(anyLong())).thenReturn(null);
        this.mockMvc.perform(delete("/departments/delete/1")).andDo(print()).andExpect(status().isNotFound());
    }
}
