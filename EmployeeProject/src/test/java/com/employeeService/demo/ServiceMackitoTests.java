package com.employeeService.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.employeeService.demo.beans.Employee;
import com.employeeService.demo.repositories.EmployeeRepository;
import com.employeeService.demo.services.EmployeeService;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { ServiceMackitoTests.class })
public class ServiceMackitoTests {

	@Mock
	EmployeeRepository empRepo;

	@InjectMocks
	EmployeeService employeeService;

	List<Employee> myEmployees;

	@Test
	@Order(1)
	public void test_getAllEmployees() {

		myEmployees = new ArrayList<Employee>();
		myEmployees.add(new Employee(1, "Korim", 28, 10000, "HR"));
		myEmployees.add(new Employee(2, "Rohim", 29, 2000, "HR"));
		myEmployees.add(new Employee(3, "Lubana", 30, 40000, "HR"));
		when(empRepo.findAll()).thenReturn(myEmployees);
		assertEquals(3, employeeService.getAllEmployees().size());
	}

	@Test
	@Order(2)
	public void test_getEmployeeById() {
		myEmployees = new ArrayList<Employee>();
		myEmployees.add(new Employee(4, "Korim", 28, 10000, "HR"));
		myEmployees.add(new Employee(6, "Rohim", 29, 2000, "HR"));
		myEmployees.add(new Employee(5, "Lubana", 30, 40000, "HR"));
		int empId = 4;
		when(empRepo.findAll()).thenReturn(myEmployees);
		assertEquals(empId, employeeService.getEmployeeById(empId).getId());
	}

	@Test
	@Order(3)
	public void test_getEmployeeByName() {
		myEmployees = new ArrayList<Employee>();
		myEmployees.add(new Employee(4, "Korim", 28, 10000, "HR"));
		myEmployees.add(new Employee(6, "Rohim", 29, 2000, "HR"));
		myEmployees.add(new Employee(5, "Lubana", 30, 40000, "HR"));
		String empName = "Lubana";
		when(empRepo.findAll()).thenReturn(myEmployees);
		assertEquals(5, employeeService.getEmployeeByName(empName).getId());
	}

	@Test
	@Order(4)
	public void test_addEmployee() {
		myEmployees = new ArrayList<Employee>();
		Employee employee = new Employee(7, "Korim", 28, 10000, "HR");
		when(empRepo.save(employee)).thenReturn(employee);
		assertEquals(employee, employeeService.addEmployee(employee));
	}

	@Test
	@Order(5)
	public void test_updateEmployee() {
		myEmployees = new ArrayList<Employee>();
		Employee employee = new Employee(7, "Korim", 28, 10000, "HR");
		when(empRepo.save(employee)).thenReturn(employee);
		assertEquals(employee, employeeService.updateEmployee(employee));
	}

	@Test
	@Order(6)
	public void test_deleetEmployee() {
		myEmployees = new ArrayList<Employee>();
		Employee employee = new Employee(7, "Korim", 28, 10000, "HR");
		myEmployees.add(employee);
		employeeService.deleteEmployee(employee);
		verify(empRepo, times(1)).delete(employee);
	}
}
