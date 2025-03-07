package com.SpringMicro.DepartmentService.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringMicro.DepartmentService.entity.Department;
import com.SpringMicro.DepartmentService.repository.DepartmentRepository;

import ch.qos.logback.classic.Logger;

@Service
public class DepartmentService {
	Logger logger = (Logger) LoggerFactory.getLogger(DepartmentService.class);
	@Autowired
	private  DepartmentRepository departmentRepository;

	public DepartmentService(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	public  Department createDepartment(Department department) {
		// TODO Auto-generated method stub
        logger.info("create department service");
		return departmentRepository.save(department);
	}

	public Department getDepartmentById(long id) {
		// TODO Auto-generated method stub
		logger.info("get department by id service");
		return departmentRepository.getDepartmentById(id);
	}

	public List<Department> getDepartment() {
		// TODO Auto-generated method stub
		logger.info("get department service");
		return departmentRepository.findAll();
	}
	public void dropDepartmentById(long id){
		 departmentRepository.deleteById(id);
	}

}
