package com.employeeService.demo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.employeeService.demo.beans.Employee;
import com.employeeService.demo.controllers.AddResponse;
import com.employeeService.demo.controllers.EmployeeController;
import com.employeeService.demo.services.EmployeeService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ControllerMackitoTests.class })
public class ControllerMackitoTests {

	@Mock
	EmployeeService employeeService;

	@InjectMocks
	EmployeeController employeeController;

	List<Employee> myEmployees;
	Employee employee;

	@Test
	@Order(1)
	public void test_getAllEmployees() {
		myEmployees = new ArrayList<Employee>();
		myEmployees.add(new Employee(1, "Korim", 28, 10000, "HR"));
		myEmployees.add(new Employee(2, "Rohim", 29, 2000, "HR"));
		myEmployees.add(new Employee(3, "Lubana", 30, 40000, "HR"));
		when(employeeService.getAllEmployees()).thenReturn(myEmployees);
		ResponseEntity<List<Employee>> res = employeeController.getEmployees(1, 1);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(3, res.getBody().size());
	}

	@Test
	@Order(2)
	public void test_getEmployeeById() {
		myEmployees = new ArrayList<Employee>();
		Employee newEmp = new Employee(1, "WKorim", 28, 10000, "HR");
		myEmployees.add(newEmp);
		int empId = 1;
		when(employeeService.getEmployeeById(empId)).thenReturn(newEmp);
		ResponseEntity<Employee> res = employeeController.getEmployeeById(empId);

		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(empId, res.getBody().getId());
	}

	@Test
	@Order(3)
	public void test_getEmployeeByName() {
		myEmployees = new ArrayList<Employee>();
		Employee newEmp = new Employee(1, "WKorim", 28, 10000, "HR");
		myEmployees.add(newEmp);
		String employeeName = "WKorim";
		when(employeeService.getEmployeeByName(employeeName)).thenReturn(newEmp);
		ResponseEntity<Employee> res = employeeController.getEmployeeByName(employeeName);
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(employeeName, res.getBody().getEmployee_name());
	}

	@Test
	@Order(4)
	public void test_addEmployee() {
		myEmployees = new ArrayList<Employee>();
		Employee newEmp = new Employee(1, "WKorim", 28, 10000, "HR");
		when(employeeService.addEmployee(newEmp)).thenReturn(newEmp);
		ResponseEntity<Employee> res = employeeController.addEmployees(newEmp);
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(res.getBody(), newEmp);
	}

	@Test
	@Order(5)
	public void test_updateEmployee() {
		myEmployees = new ArrayList<Employee>();
		Employee emp = new Employee(1, "WKorim", 28, 10000, "HR");
		employeeController.updateEmployees(1, emp);
		when(employeeService.getEmployeeById(1)).thenReturn(emp);
		when(employeeService.updateEmployee(emp)).thenReturn(emp);
		ResponseEntity<Employee> res = employeeController.updateEmployees(1, emp);
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(1, res.getBody().getId());
		assertEquals(28, res.getBody().getEmployee_age());
	}

	@Test
	@Order(6)
	public void test_deleteEmployee() {
		myEmployees = new ArrayList<Employee>();
		Employee emp = new Employee(1, "WKorim", 28, 10000, "HR");
		int empId = 1;
		when(employeeService.getEmployeeById(empId)).thenReturn(emp);
		ResponseEntity<AddResponse> res = employeeController.deleteEmployees(empId);
		assertEquals(HttpStatus.OK, res.getStatusCode());
	}
}
