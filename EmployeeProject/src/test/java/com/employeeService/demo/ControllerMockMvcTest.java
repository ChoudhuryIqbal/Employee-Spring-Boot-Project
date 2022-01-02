package com.employeeService.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.employeeService.demo.beans.Employee;
import com.employeeService.demo.controllers.EmployeeController;
import com.employeeService.demo.services.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
@ComponentScan(basePackages = "com.employeeService.demo")
@ContextConfiguration
@SpringBootTest(classes = { ControllerMockMvcTest.class })
public class ControllerMockMvcTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	EmployeeService employeeService;

	@InjectMocks
	EmployeeController employeeController;

	Employee employee;
	List<Employee> listOfEmployees;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	@Test
	@Order(3)
	public void test_getAllEmployees() throws Exception {
		listOfEmployees = new ArrayList<Employee>();
		listOfEmployees.add(new Employee(1, "Begum", 30, 10000, "House Wife"));
		listOfEmployees.add(new Employee(2, "Shabab", 30, 11000, "Automation Tester"));
		when(employeeService.getAllEmployees()).thenReturn(listOfEmployees);
		this.mockMvc.perform(get("/employees/getEmployees").param("page", "1").param("limit", "1"))
				.andExpect(status().isFound()).andDo(print());
	}

	@Test
	@Order(1)
	public void test_getEmployeeById() throws Exception {
		employee = new Employee(2, "Shabab", 30, 11000, "Automation Tester");
		int empId = 2;
		when(employeeService.getEmployeeById(empId)).thenReturn(employee);
		this.mockMvc.perform(get("/employees/getEmployees/{employeeId}", empId)).andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("employee_name").value("Shabab"))
				.andExpect(MockMvcResultMatchers.jsonPath("employee_age").value(30))
				.andExpect(MockMvcResultMatchers.jsonPath("employee_salary").value(11000))
				.andExpect(MockMvcResultMatchers.jsonPath("department").value("Automation Tester")).andDo(print());

	}

	@Test
	@Order(2)
	public void test_getEmployeeByName() throws Exception {
		employee = new Employee(2, "Shabab", 30, 11000, "Automation Tester");
		String empName = "Shabab";
		when(employeeService.getEmployeeByName(empName)).thenReturn(employee);
		this.mockMvc.perform(get("/employees/getEmployeesByName").param("name", "Shabab")).andExpect(status().isFound())
				.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("employee_name").value("Shabab"))
				.andExpect(MockMvcResultMatchers.jsonPath("employee_age").value(30))
				.andExpect(MockMvcResultMatchers.jsonPath("employee_salary").value(11000))
				.andExpect(MockMvcResultMatchers.jsonPath("department").value("Automation Tester")).andDo(print());

	}

	@Order(4)
	@Test
	public void test_GetAddEmployee() throws Exception {

		employee = new Employee(2, "Shabab", 30, 11000, "Automation Tester");
		when(employeeService.addEmployee(employee)).thenReturn(employee);
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(employee);

		this.mockMvc.perform(post("/employees/addEmployee").content(jsonBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());

	}

	@Test
	@Order(5)
	public void test_updateEmployee() throws Exception {
		employee = new Employee(2, "Shabab", 30, 11000, "Automation Tester");
		int employee_id = 2;
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(employee);
		when(employeeService.getEmployeeById(employee_id)).thenReturn(employee);
		when(employeeService.updateEmployee(employee)).thenReturn(employee);

		this.mockMvc
				.perform(put("/employees/updateEmployee/{id}", employee_id).content(jsonBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
				.andExpect(MockMvcResultMatchers.jsonPath("employee_name").value("Shabab"))
				.andExpect(MockMvcResultMatchers.jsonPath("employee_age").value(30))
				.andExpect(MockMvcResultMatchers.jsonPath("employee_salary").value(11000))
				.andExpect(MockMvcResultMatchers.jsonPath("department").value("Automation Tester")).andDo(print());

	}

	@Test
	@Order(6)
	public void test_deleteEmployee() throws Exception {
		Employee employee;
		employee = new Employee(2, "Shabab", 30, 11000, "Automation Tester");
		int empId = 2;
		when(employeeService.getEmployeeById(empId)).thenReturn(employee);
		this.mockMvc.perform(delete("/employees/deleteEmployee/{employeeId}", empId)).andExpect(status().isOk());

	}

}
