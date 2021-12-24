package com.employeeService.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employeeService.demo.beans.Employee;
import com.employeeService.demo.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping("/getEmployees/{employeeId}")
	public Employee getEmployeeById(@PathVariable(value="employeeId") int id) {
	
		return employeeService.getEmployeeById(id);
	}
	
	@GetMapping ("/getEmployeesByName")
	public Employee getEmployeeByName(@RequestParam (value="name") String employeeName) {
		return employeeService.getEmployeeByName(employeeName);
		
	}
	
	
	@GetMapping("/getEmployees")
	public List getEmployees(@RequestParam(value="page") int pageNo,@RequestParam(value="limit") int limitNo) {
		
		return employeeService.getAllEmployees();
	}
	
	

	@PostMapping("/addEmployee")
	public Employee addEmployees(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	

	@PutMapping("/updateEmployee")
	public Employee updateEmployees(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}
	
	


	@DeleteMapping("deleteEmployee/{employeeId}")
	public AddResponse deleteEmployees(@PathVariable (value ="employeeId") int id ) {
		
		return employeeService.deleteEmployee(id);
	}
	
}
