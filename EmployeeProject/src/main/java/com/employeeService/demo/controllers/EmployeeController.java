package com.employeeService.demo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	
	@GetMapping(path="/{employeeId}")
	public String getEmployee(@PathVariable String employeeId) {
		return "http get employee "+ employeeId;
	}
	
	
	@GetMapping
	public String getEmployees(@RequestParam(value="page") int pageNo,@RequestParam(value="limit") int limitNo) {
		return "http get employee for  "+pageNo +"limit is "+limitNo;
	}
	
	

	@PostMapping
	public String createEmployees() {
		return "http post request was Sent";
	}
	
	

	@PutMapping
	public String updateEmployees() {
		return "http put request was Sent";
	}
	
	

	@DeleteMapping
	public String deleteEmployees() {
		return "http delete request was Sent";
	}
	
}
