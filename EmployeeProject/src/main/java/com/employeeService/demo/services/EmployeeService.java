package com.employeeService.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.employeeService.demo.beans.Employee;
import com.employeeService.demo.controllers.AddResponse;

public class EmployeeService {

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

	public List getAllEmployees() {
		List employees = new ArrayList(employeeIdMap.values());

		return employees;

	}

	public Employee getEmployeeById(int id) {

		Employee employee = employeeIdMap.get(id);
		return employee;

	}

	public Employee getEmployeeByName(String name) {

		Employee employee = null;
		for (int i : employeeIdMap.keySet()) {
			if (employeeIdMap.get(i).getEmployee_name().equals(employee)) {
				employee = employeeIdMap.get(i);
			}
		}
		return employee;
	}

	public Employee addEmployee(Employee employee) {
		employee.setId(getMaxId());
		employeeIdMap.put(employee.getId(), employee);
		return null;
	}

	public Employee updateEmployee(Employee employee) {
		if (employee.getId() > 0) {
			employeeIdMap.put(employee.getId(), employee);
		}
		return employee;

	}

	public AddResponse deleteEmployee(int id) {
		employeeIdMap.remove(id);
		AddResponse response = new AddResponse("Successfully Deleted Records", id);
		return response;
	}

	// for new record to get a new ID
	public static int getMaxId() {
		int max = 0;
		for (int id : employeeIdMap.keySet()) {
			if (max <= id) {
				max = id;
			}
		}
		return max + 1;
	}
}
