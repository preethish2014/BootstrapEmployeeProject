package com.mind.project.shared.model;

public class Employee {

	// Instance var of emp classses
	int emp_id, age;
	String name,address;
	Long phno;



	// constructor to initilalize instance var
	public Employee(int emp_id, int age, String name, String address, Long phno) {
		super();
		this.emp_id = emp_id;
		this.age = age;
		this.name = name;
		this.address = address;
		this.phno = phno;
	}


	

	

	public int getEmp_id() {
		return emp_id;
	}






	public int getAge() {
		return age;
	}






	public String getName() {
		return name;
	}






	public String getAddress() {
		return address;
	}






	public Long getPhno() {
		return phno;
	}






	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Employee) {
			if ((emp_id == ((Employee) obj).emp_id))
				return true;
			else
				return false;
		} else
			return super.equals(obj);
	}

}
