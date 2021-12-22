/**
 * 
 */
package com.employeeService.demo.beans;

/**
 * @author Shabab
 *
 */
public class Employee {
	int id;
	String employee_name;
	int employee_age;
	int employee_salary;
	String department;

	public Employee(int id, String employee_name, int employee_age, int employee_salary, String department) {
		this.id = id;
		this.employee_name = employee_name;
		this.employee_age = employee_age;
		this.employee_salary = employee_salary;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public int getEmployee_age() {
		return employee_age;
	}

	public void setEmployee_age(int employee_age) {
		this.employee_age = employee_age;
	}

	public int getEmployee_salary() {
		return employee_salary;
	}

	public void setEmployee_salary(int employee_salary) {
		this.employee_salary = employee_salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
