package com.mind.project.shared.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeaveDB implements LeaveApplication {

	
	
	public List<Leave> leaveDB=new ArrayList<Leave>();
	
	
	public void addApplyLeave(Leave leave)
	{
		leaveDB.add(leave);
	}
	public int getApplyListCount() 
	{
		return leaveDB.size();
	}
	public Leave searchApplyLeaveDetails(int id,String leaveType) {

		Iterator<Leave> it = leaveDB.iterator();
		while (it.hasNext()) {
			Leave leaveapplylist = it.next();
			if (id == leaveapplylist.getEmp_id())
				if(leaveType.equals(leaveapplylist.getLeaveType()))
			{
				return leaveapplylist;
			}
		}
		return null;
	}
	@Override
	public void addApproveLeave(Leave leave) {
		// TODO Auto-generated method stub
		
	}
}
