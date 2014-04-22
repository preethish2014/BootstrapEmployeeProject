package com.mind.project.client.application.applyLeave.applyleave;



import java.util.List;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.mind.project.client.place.NameTokens;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.Leave;
import com.mind.project.shared.model.LeaveDB;
import com.mind.project.shared.model.LeaveTypeDB;

public class ApplyLeavePresenter extends
		Presenter<ApplyLeavePresenter.MyView, ApplyLeavePresenter.MyProxy>
		implements ApplyLeaveUiHandlers {
	interface MyView extends View, HasUiHandlers<ApplyLeaveUiHandlers> {
		
		public void getAssigendLeave(Integer emp_Id,List<String> assignLeave);
		public TextBox getLeaveDesc();
		public TextBox getStart_date();
		public TextBox getEnd_date();
		public TextBox getStatus() ;
		public TextBox  getEmpId();
		public ListBox getAssignedLeaveTypes();
		public void getApplyLeave(Leave applyLeaveList);
		void clearAssignedListBox();
		
		
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_ApplyLeave = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.applyLeave)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<ApplyLeavePresenter> {
	}

	@Inject
	public ApplyLeavePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

		getView().setUiHandlers(this);
	}
	@Inject 
	LeaveDB leaveDB;
	@Inject
	LeaveTypeDB leavetypeDb;
	@Inject
	PlaceManager placemanager;
	
	
//Get Id through get parameter and search into leaveTypeDb all the Alloted Leave
	@Override
	public void prepareFromRequest(PlaceRequest request) {

		super.prepareFromRequest(request);
		String id = request.getParameter("Param", "Not found");

		Integer emp_id = Integer.parseInt(id);
          
		List<String> assignLeave =leavetypeDb.searchLeavesByEmpId( emp_id) ;
		//pass the employeeId and list of assignLeave of a particular employee to the Apply Leave view
		getView().clearAssignedListBox();
		getView().getAssigendLeave(emp_id ,assignLeave);
		}
//get all the details of apply Leave into presenter
	@Override
	public void applyLeave() {
		// TODO Auto-generated method stub
		Integer index=getView().getAssignedLeaveTypes().getSelectedIndex();
		String leavetype=getView().getAssignedLeaveTypes().getItemText(index);
		Leave leaveapp=new Leave(Integer.parseInt(getView().getEmpId().getText()),leavetype, 
				getView().getLeaveDesc().getText(),getView().getStart_date().getText(), 
				getView().getEnd_date().getText(),getView().getStatus().getText());
		leaveDB.addApplyLeave(leaveapp);
		Window.alert("Success fully appled"+leaveDB.getApplyListCount());
		
		//get the emp_id from the view and search the apply_leave_details of the particular Employee 
		Integer emp_id=Integer.parseInt(getView().getEmpId().getText());
		   Leave applyLeaveList  =leaveDB.searchApplyLeaveDetails(emp_id , leavetype);
		   //pass the apply_leave_list into the view to store into flaxTable
		    getView().getApplyLeave(applyLeaveList);
	}
	

	
	
	
}
