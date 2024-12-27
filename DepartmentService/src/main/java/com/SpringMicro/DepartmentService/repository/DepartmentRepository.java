package com.SpringMicro.DepartmentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SpringMicro.DepartmentService.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>{

	public Department getDepartmentById(long id);

}
