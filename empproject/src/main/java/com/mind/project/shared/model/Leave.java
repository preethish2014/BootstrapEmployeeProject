package com.mind.project.shared.model;

public class Leave {
	
	
	int emp_id;
	String leaveType;
	String leaveDesc;
	String start_date;
	String end_date;
	String status;
	public Leave(int emp_id, String leaveType, String leaveDesc,
			String start_date, String end_date, String status) {
		super();
		this.emp_id = emp_id;
		this.leaveType = leaveType;
		this.leaveDesc = leaveDesc;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
	}
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getLeaveDesc() {
		return leaveDesc;
	}
	public void setLeaveDesc(String leaveDesc) {
		this.leaveDesc = leaveDesc;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
