package com.mind.project.client.application.displaypresenter.leavetypepresenter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.mind.project.client.application.displaypresenter.DisplayPresenterPresenter;
import com.mind.project.client.place.NameTokens;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.LeaveTypeDB;

public class LeaveTypePresenterPresenter
		extends
		Presenter<LeaveTypePresenterPresenter.MyView, LeaveTypePresenterPresenter.MyProxy>
		implements LeaveTypePresenterUiHandlers {
	interface MyView extends View, HasUiHandlers<LeaveTypePresenterUiHandlers> {

		void setEmployeeName(String empName);

		
		ListBox getAssignedListBox();
		void setEmployeeId(int empId);


		void clearAssignedListBox();

	}

	// String to hold the emp name in the LeaveType presenter
	String empName;

	// Setting the Employee name from displayPresenter
	public void setEmpName(String empName) {
		this.empName = empName;
		// get the Lable and call the setEmployeeName in view to set the
		// employee name.
		getView().setEmployeeName(empName);
	}

	int empId;

	public void setEmpId(int empId) {
		this.empId = empId;
		getView().setEmployeeId(empId);
	}

	/*
	 * @ContentSlot public static final Type<RevealContentHandler<?>>
	 * SLOT_LeaveTypePresenter = new Type<RevealContentHandler<?>>();
	 */
	@NameToken(NameTokens.leavetype)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<LeaveTypePresenterPresenter> {
	}

	@Inject
	public LeaveTypePresenterPresenter(EventBus eventBus, MyView view,
			MyProxy proxy) {
		super(eventBus, view, proxy,
				DisplayPresenterPresenter.SLOT_DisplayPresenter);

		getView().setUiHandlers(this);
	}

	@Inject
	LeaveTypeDB leaveDB;

	@Override
	protected void onReset() {

		super.onReset();

	}

	@Override
	public void saveEmpLeaveDetails(Integer emp_id, List<String> leavelist) {
		
		leaveDB.addLeaveDetailsToDb(emp_id,leavelist);
		Window.alert("Leave Successfully assigned to the employee");
	   System.out.println("The size of map after adding the leaves are"+leaveDB.getMapCount());
		
}

	public void showAssignedLeaves(Integer emp_id) {
		List<String> leaveList_searchOfEmp = leaveDB
				.searchLeavesByEmpId(emp_id);

		getView().clearAssignedListBox();
		
		if (leaveList_searchOfEmp != null && leaveList_searchOfEmp.size() != 0) {
			
			Iterator<String> it = leaveList_searchOfEmp.iterator();
			while (it.hasNext()) {
				String leaves = it.next();

				getView().getAssignedListBox().addItem(leaves);

			}
	   
	}
	}
}