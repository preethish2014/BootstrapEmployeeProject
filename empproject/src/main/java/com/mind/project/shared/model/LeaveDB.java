package com.mind.project.shared.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Singleton;

@Singleton
public class LeaveDB implements LeaveApplication {

	public List<Leave> leaveDB = new ArrayList<Leave>();
	
	
	
	/*//just for testing
		public Leave callLeaveConnstructor(int emp_id, String leaveType, String leaveDesc,
				String start_date, String end_date, String status)
		{
			Leave leave = new Leave(emp_id,leaveType,leaveDesc,start_date,end_date,status);
	        leaveDB.add(leave);
			return leave;
			
			
		}*/
		

	public Leave searchApplyLeaveDetails(int id, String leaveType) {

		Iterator<Leave> it = leaveDB.iterator();
		while (it.hasNext()) {
			Leave leaveapplylist = it.next();
			if (id == leaveapplylist.getEmp_id())
				if (leaveType.equals(leaveapplylist.getLeaveType())) {
					return leaveapplylist;
				}
		}
		return null;
	}

	public void addApplyLeave(Leave leave) {
		leaveDB.add(leave);
	}

	public int getApplyListCount() {
		return leaveDB.size();
	}

	@Override
	public void addApproveLeave(Leave leave) {

	}

	public List<Leave> getAllAppliedLeavesByEmpId(List<Integer> empUnderManager) {
		List<Leave> appliedLeaves = new ArrayList<Leave>();
		Iterator<Integer> it = empUnderManager.iterator();
		while (it.hasNext()) {
			Integer emp_id = it.next();

			if (leaveDB.contains(emp_id)) {
				Iterator<Leave> it1 = leaveDB.iterator();
				while (it1.hasNext()) {
					Leave leave = it1.next();

					if (leave.getEmp_id() == emp_id) {
						appliedLeaves.add(leave);

					}
				}

			}

		}
		return appliedLeaves;

	}

	public List<Leave> getLeaveDB() {
		return leaveDB;

	}

	public boolean updateLeaveDatabase(Leave leave1) {
		System.out.println("the size of db later" + leaveDB.size());

		ListIterator<Leave> itr = leaveDB.listIterator();
		int i = 0;
		while (itr.hasNext()) {
			Leave leave = itr.next();
			if (leaveDB.contains(leave1) && leave.equals(leave1)
					&& (LeaveStatus.APPROVED).toString().equals(leave1.status)) {

				leave1.setStatus(LeaveStatus.APPROVED.toString());
				leaveDB.set(i, leave1);
				return true;

			} else if (leaveDB.contains(leave1) && leave.equals(leave1)
					&& (LeaveStatus.REJECTED).toString().equals(leave1.status)) {

				leave1.setStatus(LeaveStatus.REJECTED.toString());
				leaveDB.set(i, leave1);
				return true;

			}

			i++;
		}

		System.out.println("the size of db later" + leaveDB.size());
		return false;
	}

}
