package com.mind.project.shared.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Singleton;







@Singleton
public class EmployeeDB {
	
	// Adding employee data
		public Employee addEmpData(int emp_id, int age, String name, String address, Long phno) {

			// calling employee class constructor
			Employee emp = new Employee(emp_id, age, name, address,phno);
			// adding the emp details into the list
			person.add(emp);

			return emp;

		}
	
	
	

	// Stores the Database of the emoloyees
	public List<Employee> person = new ArrayList<Employee>();

	public void addEmployee(Employee emp) {
		person.add(emp);
	}

	// function to return the no of emp in the list
	public int getEmployeeCount() {

		return person.size();
	}
	
	
//REturn the whole list of employees
	public List<Employee> getAllEmployees() {
		
		return person;
	}
	
//function to search a employee by id
	
	public Employee searchEmpDataById(int id) {

		Iterator<Employee> it = person.iterator();
		while (it.hasNext()) {
			Employee employee = it.next();
			if (id == employee.getEmp_id()) {
				return employee;
			}
		}
		return null;
	}

	public void deleteEmpByID(int id) {

		Iterator<Employee> it = person.iterator();
		while (it.hasNext()) {
			
			Employee del_emp1 = it.next();
			
			if (id == (del_emp1.getEmp_id())) {
				{
					person.remove(del_emp1);
					break;
					// remove the emp based on id
					
				}
			}
		}

		

	}

}