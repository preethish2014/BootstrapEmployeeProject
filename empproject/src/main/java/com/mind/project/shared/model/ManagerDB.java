package com.mind.project.shared.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerDB {
	
	public Map<Integer,List<Integer> > mgr = new HashMap<Integer,List<Integer> >();
	
	
	public void addReportees(Integer mgr_id, List list)
	{
		mgr.put(mgr_id, list);
	}
	
    public int getMapCount()
    {
    	System.out.println("The size of the manager map is" +mgr.size());
    	return mgr.size();
    }
	

}
