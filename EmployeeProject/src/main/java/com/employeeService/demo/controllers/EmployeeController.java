package com.employeeService.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="employeeId") int id) {
		try {
			Employee employee=employeeService.getEmployeeById(id);
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		}
		
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping ("/getEmployeesByName")
	public ResponseEntity<Employee> getEmployeeByName(@RequestParam (value="name") String employeeName) {
		try {
			Employee employee=employeeService.getEmployeeByName(employeeName);
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		}
		
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	
	@GetMapping("/getEmployees")
	public ResponseEntity<List<Employee>> getEmployees(@RequestParam(value="page") int pageNo,@RequestParam(value="limit") int limitNo) {
		try {
			List<Employee> employee=employeeService.getAllEmployees();
			return new ResponseEntity<List<Employee>>(employee,HttpStatus.OK);
		}
		
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}	
	}
	
	

	@PostMapping("/addEmployee")
	public ResponseEntity<Employee> addEmployees(@RequestBody Employee employee) {
		
		try {
			Employee employeeObject=employeeService.addEmployee(employee);
			return new ResponseEntity<Employee>(employeeObject,HttpStatus.OK);
		}
		
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		
	//	return employeeService.addEmployee(employee);
	}
	

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<Employee> updateEmployees(@PathVariable (value="id")int employee_id,@RequestBody Employee employee) {
		
	//	return employeeService.updateEmployee(employee);
		
		
		try {
			
		Employee existEmployee = employeeService.getEmployeeById(employee_id);
		existEmployee.setEmployee_name(employee.getEmployee_name())	;
		existEmployee.setDepartment(employee.getDepartment());
		existEmployee.setEmployee_age(employee.getEmployee_age());
		existEmployee.setEmployee_salary(employee.getEmployee_salary());
		
		Employee employeeObject=employeeService.updateEmployee(existEmployee);
			return new ResponseEntity<Employee>(employeeObject,HttpStatus.OK);
		}
		
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	


	@DeleteMapping("deleteEmployee/{employeeId}")
	public ResponseEntity<AddResponse> deleteEmployees(@PathVariable (value ="employeeId") int id ) {
		try {
			AddResponse res= employeeService.deleteEmployee(id);
			return new ResponseEntity<AddResponse>(res,HttpStatus.OK);
		}
		
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
