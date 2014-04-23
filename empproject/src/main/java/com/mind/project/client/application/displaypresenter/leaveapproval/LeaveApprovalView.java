package com.mind.project.client.application.displaypresenter.leaveapproval;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hidden;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.EmployeeDB;
import com.mind.project.shared.model.Leave;
import com.mind.project.shared.model.LeaveStatus;
import com.mind.project.shared.model.ManagerDB;

public class LeaveApprovalView extends
		ViewWithUiHandlers<LeaveApprovalUiHandlers> implements
		LeaveApprovalPresenter.MyView {
	interface Binder extends UiBinder<Widget, LeaveApprovalView> {
	}

	@Inject
	LeaveApprovalView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	FlexTable leaveApprovalTable;
	
	@UiField
	Hidden managerId;

	@Inject
	EmployeeDB empdb;

	// method to clear the table every time
	@Override
	public void clearTable() {

		leaveApprovalTable.clear();
		leaveApprovalTable.removeAllRows();
	}

	public Integer getManagerId()
	{
		return Integer.parseInt(managerId.getValue());
	}
	
	// Method for setting the Table headings
	public void setColumnHeading() {
		leaveApprovalTable.setWidget(0, 0, new Label("Emp Id"));
		leaveApprovalTable.setWidget(0, 1, new Label("Emp Name"));
		leaveApprovalTable.setWidget(0, 2, new Label("LeaveType"));
		leaveApprovalTable.setWidget(0, 3, new Label("LeaveDescription"));
		leaveApprovalTable.setWidget(0, 4, new Label("Start Date"));
		leaveApprovalTable.setWidget(0, 5, new Label("End Date"));
		leaveApprovalTable.setWidget(0, 6, new Label("Leave Status"));
		leaveApprovalTable.setWidget(0, 7, new Label("Approve"));
		leaveApprovalTable.setWidget(0, 8, new Label("Reject"));
	}

	@Override
	public void addLeaveDetails(final Leave leave,Integer manager_Id) {

		managerId.setValue(manager_Id.toString());;
		Label idlable = new Label(leave.getEmp_id() + "");
		idlable.setWidth("50px");

		Employee emp = empdb.searchEmpDataById(leave.getEmp_id());
		Label namelable = new Label(emp.getName());
		namelable.setWidth("80px");

		Label leavedesLable = new Label(leave.getLeaveDesc());
		leavedesLable.setWidth("160px");

		Label leaveTypeLable = new Label(leave.getLeaveType());
		leaveTypeLable.setWidth("70px");

		Label startDateLable = new Label(leave.getStart_date());
		startDateLable.setWidth("90px");

		Label endDateLable = new Label(leave.getEnd_date());
		endDateLable.setWidth("90px");

		Label statusLable = new Label(leave.getStatus());
		statusLable.setWidth("150px");

		// counter to set the initial size of the table to 0.
		int sizeOfTable = 0;
		// get the no of rows of the table
		sizeOfTable = leaveApprovalTable.getRowCount();

		// set the heading of the table
		if (sizeOfTable <= 0)
			setColumnHeading();

		sizeOfTable++;

		// Declare the delete button for deleting the employee details.
	    Button approveButton = new Button("APPROVE ");
		approveButton.setWidth("120px");
		approveButton.setHeight("45px");
		String pending = LeaveStatus.PENDING.toString();
		if (pending.equals(leave.getStatus()))
	    {
			approveButton.setEnabled(true);
	    }
	    else
	    {
	    	approveButton.setEnabled(false);
	    }
		approveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//String status = LeaveStatus.APPROVED;
				leave.setStatus(LeaveStatus.APPROVED.toString());
				getUiHandlers().approveLeave(leave);
			}
		});

		// Declare the delete button for deleting the employee details.
	    Button rejectButton = new Button("REJECT ");
	    rejectButton.setWidth("120px");
	    rejectButton.setHeight("45px");
	    String pending1 = LeaveStatus.PENDING.toString();
		if (pending1.equals(leave.getStatus()))
	    {
			rejectButton.setEnabled(true);
	    }
	    else
	    {
	    	rejectButton.setEnabled(false);
	    }
	
	    rejectButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				//String status = LeaveStatus.APPROVED;
				leave.setStatus(LeaveStatus.REJECTED.toString());
				getUiHandlers().rejectLeave(leave);
				
			}
		});
		
		
		
		leaveApprovalTable.setWidget(sizeOfTable, 0, idlable);
		leaveApprovalTable.setWidget(sizeOfTable, 1, namelable);
		leaveApprovalTable.setWidget(sizeOfTable, 2, leavedesLable);
		leaveApprovalTable.setWidget(sizeOfTable, 3, leaveTypeLable);
		leaveApprovalTable.setWidget(sizeOfTable, 4, startDateLable);
		leaveApprovalTable.setWidget(sizeOfTable, 5, endDateLable);
		leaveApprovalTable.setWidget(sizeOfTable, 6, statusLable);
		leaveApprovalTable.setWidget(sizeOfTable, 7, approveButton);
		leaveApprovalTable.setWidget(sizeOfTable, 8, rejectButton);

	}

}
