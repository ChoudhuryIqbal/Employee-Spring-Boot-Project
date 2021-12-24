package com.employeeService.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeeService.demo.beans.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
	

}
