package com.employeeService.demo;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import com.employeeService.demo.beans.Employee;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ControllerIntegrationTest {
		
	@Test
	@Order(1)
	public void getEmployeeIntegrationTest() throws Exception {
		
		String expected="[\r\n"
				+ "    {\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"employee_name\": \"Shabab\",\r\n"
				+ "        \"employee_age\": 30,\r\n"
				+ "        \"employee_salary\": 10000,\r\n"
				+ "        \"department\": \"HR\"\r\n"
				+ "    },\r\n"
				+ "    {\r\n"
				+ "        \"id\": 2,\r\n"
				+ "        \"employee_name\": \"Begum\",\r\n"
				+ "        \"employee_age\": 56,\r\n"
				+ "        \"employee_salary\": 12000,\r\n"
				+ "        \"department\": \"HouseWife\"\r\n"
				+ "    }\r\n"
				+ "]";
		TestRestTemplate restTemplate=new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/employees/getEmployees?page=1&limit=50", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	
	
	@Test
	@Order(2)
	public void getEmployeeByIdIntegrationTest() throws Exception {
		
		String expected="{\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"employee_name\": \"Shabab\",\r\n"
				+ "        \"employee_age\": 30,\r\n"
				+ "        \"employee_salary\": 10000,\r\n"
				+ "        \"department\": \"HR\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate=new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/employees/getEmployees/1", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	
	

	@Test
	@Order(3)
	public void getEmployeeByNameIntegrationTest() throws Exception {
		
		String expected="{\r\n"
				+ "        \"id\": 1,\r\n"
				+ "        \"employee_name\": \"Shabab\",\r\n"
				+ "        \"employee_age\": 30,\r\n"
				+ "        \"employee_salary\": 10000,\r\n"
				+ "        \"department\": \"HR\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate=new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/employees/getEmployeesByName?name=Shabab", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	
	@Test
	@Order(4)
	public void addEmployeeIntegrationTest() throws Exception {
		
		Employee employee=new Employee(3, "Korim", 28, 10000, "HR");
		String expected="{\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"employee_name\": \"Korim\",\r\n"
				+ "        \"employee_age\": 28,\r\n"
				+ "        \"employee_salary\": 10000,\r\n"
				+ "        \"department\": \"HR\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate=new TestRestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity <Employee> request=new HttpEntity<Employee> (employee,headers);
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/employees/addEmployee", request, String.class);
		
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	@Test
	@Order(5)
	public void updateEmployeeIntegrationTest() throws Exception {
		
		Employee employee=new Employee(3, "Jordan", 28, 10000, "HR");
		String expected="{\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"employee_name\": \"Jordan\",\r\n"
				+ "        \"employee_age\": 28,\r\n"
				+ "        \"employee_salary\": 10000,\r\n"
				+ "        \"department\": \"HR\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate=new TestRestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity <Employee> request=new HttpEntity<Employee> (employee,headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/employees/updateEmployee/3", HttpMethod.PUT,request, String.class);
		
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		JSONAssert.assertEquals(expected, response.getBody(), false);
		
	}
	@Test
	@Order(6)
	public void deleteEmployeeIntegrationTest() throws Exception {
		
		Employee employee=new Employee(3, "Jordan", 28, 10000, "HR");
		String expected="{\r\n"
				+ "        \"id\": 3,\r\n"
				+ "        \"employee_name\": \"Jordan\",\r\n"
				+ "        \"employee_age\": 28,\r\n"
				+ "        \"employee_salary\": 10000,\r\n"
				+ "        \"department\": \"HR\"\r\n"
				+ "    }";
		TestRestTemplate restTemplate=new TestRestTemplate();
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity <Employee> request=new HttpEntity<Employee> (employee,headers);
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/employees/deleteEmployee/3", HttpMethod.DELETE,request, String.class);
		System.out.println(response.getBody());
		System.out.println(response.getStatusCode());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	//	restTemplate.delete("http://localhost:8080/employees/deleteEmployee/3");  // this one not return anything thus we can not use any assertion. 
		
		
	}
}
