package com.mind.project.shared.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;



@Singleton
public class LeaveTypeDB {

	// Stores the Database of the leave
	public Map<Integer,List<String> > leaves = new HashMap<Integer,List<String> >();

	public void addLeaveDetailsToDb(Integer emp_id, List<String> leavelist) 
	{
		leaves.put(emp_id, leavelist);
		
		
	}
	public int getMapCount(){
		return leaves.size();
	}
	
	public List<String> searchLeavesByEmpId(Integer emp_id) 
	{
		for (Map.Entry<Integer,List<String>> entry : leaves.entrySet()) {
			
			if(leaves.containsKey(emp_id))
			{
				List<String> assignedLeaves = entry.getValue();
				return assignedLeaves;
				
			}

			System.out.println("\n Employee id is  : " + entry.getKey()
					+ "\t LeaveTypes Asssigned to the emp are :   " + entry.getValue());
		}
		return null;
		
		
	}

	

}
