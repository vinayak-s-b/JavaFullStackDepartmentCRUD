package com.SpringMicro.DepartmentService.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SpringMicro.DepartmentService.entity.Department;
import com.SpringMicro.DepartmentService.service.DepartmentService;

import ch.qos.logback.classic.Logger;


@RestController
@CrossOrigin(origins= "http://localhost:3000")
@RequestMapping("/departments")
public class DepartmentController {
	Logger logger = (Logger) LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	private DepartmentService departmentService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createDepartment(@Valid @RequestBody Department department) {
		logger.info("dept Create controller");
			Department createdDept = departmentService.createDepartment(department);
			if(createdDept!=null) {
				return ResponseEntity.status(HttpStatus.CREATED).body("Department Created Successfully");
			}
			else{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Create Department");
			}
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Department>> getDepartment() {
		logger.info("get dept controller");
		List<Department> department = departmentService.getDepartment();
		return ResponseEntity.ok(department);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Department> getDepartmentById(@PathVariable long id) {
		logger.info("get deptById controller");
		Department departmentById = departmentService.getDepartmentById(id);
		if(departmentById!=null) {
			return ResponseEntity.ok(departmentById);
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateDepartment(@Valid @RequestBody Department department){
		Department tempDepartment=departmentService.getDepartmentById(department.getId());
		if(tempDepartment!=null){
			tempDepartment.setName(department.getName());
			tempDepartment.setDescription(department.getDescription());
			Department createdDept = departmentService.createDepartment(tempDepartment);
			return ResponseEntity.status(HttpStatus.OK).body("Department Updated Successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("failed to Update Department");
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteDepartmentByID(@PathVariable long id){
		if(departmentService.getDepartmentById(id)!=null){
			departmentService.dropDepartmentById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deleted "+id);
		}
		else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dept Not Found");
		}
	}
	
}
