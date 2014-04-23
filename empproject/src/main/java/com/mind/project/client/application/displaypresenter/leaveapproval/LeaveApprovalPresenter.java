package com.mind.project.client.application.displaypresenter.leaveapproval;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.inject.Inject;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.mind.project.client.place.NameTokens;
import com.mind.project.shared.model.Leave;
import com.mind.project.shared.model.LeaveDB;
import com.mind.project.shared.model.LeaveStatus;
import com.mind.project.shared.model.ManagerDB;

public class LeaveApprovalPresenter
		extends
		Presenter<LeaveApprovalPresenter.MyView, LeaveApprovalPresenter.MyProxy>
		implements LeaveApprovalUiHandlers {
	interface MyView extends View, HasUiHandlers<LeaveApprovalUiHandlers> {
		void clearTable();
		
		

		void addLeaveDetails(Leave leave, Integer manager_id);



		Integer getManagerId();
		
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_LeaveApproval = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.leaveapproval)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<LeaveApprovalPresenter> {
	}

	@Inject
	public LeaveApprovalPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

		getView().setUiHandlers(this);
	}
	
	
	
	@Inject
	ManagerDB mgrdb;
	@Inject
	LeaveDB leavedb;

	@Override
	public void prepareFromRequest(PlaceRequest request) {

		super.prepareFromRequest(request);
		String id = request.getParameter("Param", "Not found");

		// get the manager id
		Integer manager_id = Integer.parseInt(id);
		// search the managerDB and get the whole list of reportees under that
		// employee
		
		/*//just for testing
		List<Integer> list1 = new ArrayList<Integer>();
		list1.add(2);
		list1.add(3);
		list1.add(4);
	
		mgrdb.addReportees(1, list1);*/
		
		List<Integer> empUnderManager = mgrdb.searchManagerDB(manager_id);

		// get the whole leavedb
		//again testing
		
		/*Leave leave1 = leavedb.callLeaveConnstructor(2,"Casual","Home trip", "12-4-14",  "14-4-14", "PENDING");
		Leave leave2 = leavedb.callLeaveConnstructor(3,"Sick","Fever", "13-4-14",  "16-4-14", "PENDING");*/
		
		List<Leave> leave_database = leavedb.getLeaveDB();
	

		List<Leave> appliedLeaves = new ArrayList<Leave>();
		
		Iterator<Integer> it = empUnderManager.iterator();
		while (it.hasNext()) {
			Integer emp_id = it.next();
           Iterator<Leave> it1 = leave_database.iterator();
				while (it1.hasNext()) {
					Leave leave = it1.next();

					if (leave.getEmp_id() == emp_id) {
						appliedLeaves.add(leave);
						
						
					}
				}

			}

		       if(!appliedLeaves.isEmpty())
	            {
	            
	            	Iterator<Leave> it2 = appliedLeaves.iterator();
					while (it2.hasNext())
					{
						Leave leave = it2.next();
	            	    getView().addLeaveDetails(leave,manager_id);
	                }
	            }
		//System.out.println("The size of "+appliedLeaves.size());
		
		
		
	}
	
   //function receiving args from the view
	@Override
	public void approveLeave(Leave leave) 
	{
		boolean status=false;
		//getting the leave database
		List<Leave> leavedetails = leavedb.getLeaveDB();
		
		ListIterator<Leave> it =  leavedetails.listIterator();
		while (it.hasNext())
		{
			Leave leaveFromDB = it.next();
			if ((leave.getEmp_id()==leaveFromDB.getEmp_id()) &&(leave.getLeaveType().equals(leaveFromDB.getLeaveType()) 
					&&(leave.getStart_date().equals(leaveFromDB.getStart_date())) && (leave.getEnd_date().equals(leaveFromDB.getEnd_date())))  )
			{
			
			 status=	leavedb.updateLeaveDatabase(leave);
				
				
				
		    }

		}
		if(status)
		{
			//refreshing the table values
		 Integer managerId = getView().getManagerId();	
		 //list of emp under the particular manager 
		 List<Integer> empUnderManager1 = mgrdb.searchManagerDB(managerId);
		 
		 // getting the leave db
		 List<Leave> leave_database = leavedb.getLeaveDB();
			
        // list to hold the approved lists 
		 List<Leave> approvedLeaves = new ArrayList<Leave>();
		 
		 Iterator<Integer> it1 = empUnderManager1.iterator();
			while (it1.hasNext()) {
				Integer emp_id1 = it1.next();
	           Iterator<Leave> it2 = leave_database.iterator();
					while (it2.hasNext()) {
						Leave leave1 = it2.next();

						if (leave1.getEmp_id() == emp_id1) {
							approvedLeaves.add(leave1);
							
							
						}
					}

				}
			if(!approvedLeaves.isEmpty())
            {
				
				getView().clearTable();
            	Iterator<Leave> it2 = approvedLeaves.iterator();
				while (it2.hasNext())
				{
					Leave leave3 = it2.next();
					
            	    getView().addLeaveDetails(leave3,managerId);
                }
            }
		 
		 
		Window.alert("Leave  Approved Successfully ");
		}
		else
		Window.alert("Leave Already Approved  !!!");
		
	}

	@Override
	public void rejectLeave(Leave leave)
	{
		
		boolean status=false;
		//getting the leave database
		List<Leave> leavedetails = leavedb.getLeaveDB();
		
		ListIterator<Leave> it =  leavedetails.listIterator();
		while (it.hasNext())
		{
			Leave leaveFromDB = it.next();
			if ((leave.getEmp_id()==leaveFromDB.getEmp_id()) &&(leave.getLeaveType().equals(leaveFromDB.getLeaveType()) 
					&&(leave.getStart_date().equals(leaveFromDB.getStart_date())) && (leave.getEnd_date().equals(leaveFromDB.getEnd_date())))  )
			{
			
			 status=	leavedb.updateLeaveDatabase(leave);
				
				
				
		    }

		}
		if(status)
		{
			//refreshing the table values
		 Integer managerId = getView().getManagerId();	
		 //list of emp under the particular manager 
		 List<Integer> empUnderManager1 = mgrdb.searchManagerDB(managerId);
		 
		 // getting the leave db
		 List<Leave> leave_database = leavedb.getLeaveDB();
			
        // list to hold the approved lists 
		 List<Leave> rejectedLeaves = new ArrayList<Leave>();
		 
		 Iterator<Integer> it1 = empUnderManager1.iterator();
			while (it1.hasNext()) {
				Integer emp_id1 = it1.next();
	           Iterator<Leave> it2 = leave_database.iterator();
					while (it2.hasNext()) {
						Leave leave1 = it2.next();

						if (leave1.getEmp_id() == emp_id1) {
							rejectedLeaves.add(leave1);
							
							
						}
					}

				}
			if(!rejectedLeaves.isEmpty())
            {
				
				getView().clearTable();
            	Iterator<Leave> it2 = rejectedLeaves.iterator();
				while (it2.hasNext())
				{
					Leave leave3 = it2.next();
					
            	    getView().addLeaveDetails(leave3,managerId);
                }
            }
		 
		 
		Window.alert("Leave  Rejected Successfully ");
		}
		else
		Window.alert("Leave Already Rejected  !!!");
	}
}
