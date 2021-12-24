package com.employeeService.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.employeeService.demo.beans.Employee;
import com.employeeService.demo.controllers.AddResponse;
import com.employeeService.demo.repositories.EmployeeRepository;

@Component
@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	
	static HashMap<Integer, Employee> employeeIdMap;

	public EmployeeService() {
		employeeIdMap = new HashMap<Integer, Employee>();
		Employee jodu = new Employee(1, "Jodu", 20, 0, "Finance");
		Employee modu = new Employee(2, "Modu", 21, 0, "Tech");
		Employee kodu = new Employee(3, "kodu", 22, 0, "HR");

		employeeIdMap.put(1, jodu);
		employeeIdMap.put(2, modu);
		employeeIdMap.put(3, kodu);

	}

	public List<Employee> getAllEmployees() {
		//List employees = new ArrayList(employeeIdMap.values());
		//return employees;
		return employeeRepo.findAll();

	}

	public Employee getEmployeeById(int id) {

		//Employee employee = employeeIdMap.get(id);
		//return employee;
		return employeeRepo.findById(id).get();

	}

	public Employee getEmployeeByName(String name) {
/*
		Employee employee = null;
		for (int i : employeeIdMap.keySet()) {
			if (employeeIdMap.get(i).getEmployee_name().equals(name)) {
				employee = employeeIdMap.get(i);
			}
		}
		return employee;
		
		*/
		
		List<Employee> employees=employeeRepo.findAll();
		Employee employee=null;
		for (Employee emp:employees) {
			if (emp.getEmployee_name().equalsIgnoreCase(name))
				employee=emp;
		}
		return employee;
	}

	public Employee addEmployee(Employee employee) {
	//	employee.setId(getMaxId());
		//employeeIdMap.put(employee.getId(), employee);
		//return employee;
		
		employee.setId(getMaxId());
		employeeRepo.save(employee);
		return employee;
		
	}

	public Employee updateEmployee(Employee employee) {
	
		/*
		if (employee.getId() > 0) {
			employeeIdMap.put(employee.getId(), employee);
		}
		return employee;
*/
		
		employeeRepo.save(employee);
		return employee;
	}

	public AddResponse deleteEmployee(int id) {
		/*employeeIdMap.remove(id);
		AddResponse response = new AddResponse("Successfully Deleted Records", id);
		return response;*/
		
		employeeRepo.deleteById(id);
		
		AddResponse response=new AddResponse("Successfully Delete Records",id);
		return response;
		
	}

	// for new record to get a new ID
	public  int getMaxId() {// rempving name non static to comply with db
		/*
		int max = 0;
		for (int id : employeeIdMap.keySet()) {
			if (max <= id) {
				max = id;
			}
		}
		return max + 1;
		
		*/
		return employeeRepo.findAll().size()+1;
	}
}
