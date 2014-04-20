package com.mind.project.client.application.displaypresenter.leavetypepresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.mind.project.client.application.displaypresenter.DisplayPresenterPresenter;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.EmployeeDB;
import com.mind.project.shared.model.LeaveTypeDB;

public class LeaveTypePresenterView extends
		ViewWithUiHandlers<LeaveTypePresenterUiHandlers> implements
		LeaveTypePresenterPresenter.MyView {
	interface Binder extends UiBinder<Widget, LeaveTypePresenterView> {
	}

	
	@UiField
	ListBox availableLeaveTypes;
	@UiField
	ListBox assignedLeaveTypes;

	@UiField
	HTMLPanel contentPanel;
	@UiField
	Label nameOfEmp;
	
	@UiField
	Label idOfEmp;
	@UiField
	Button addButton;
	@UiField
	Button delButton;

	@UiField
	Button saveButton;
	@Inject
	EmployeeDB empdb;
    
	
	@Inject
	LeaveTypePresenterView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		addButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Integer availableIndex = availableLeaveTypes.getSelectedIndex();
				String leaveType = availableLeaveTypes
						.getItemText(availableIndex);
				assignedLeaveTypes.addItem(leaveType);

			}
		});

		delButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				Integer selectedIndex = assignedLeaveTypes.getSelectedIndex();
				assignedLeaveTypes.removeItem(selectedIndex);

			}

		});

		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				String leaveType;
				List<String> leavelist = new ArrayList<String>();
				
				for (int i =0; i<assignedLeaveTypes.getItemCount();i++)
				{
				
			      leaveType = assignedLeaveTypes.getItemText(i);
				  leavelist.add(leaveType);
				
				}
				Integer emp_id = Integer.parseInt(idOfEmp.getText());
				
				getUiHandlers().saveEmpLeaveDetails(emp_id,leavelist);

			}
		});

	}

	// Function to set the empname to the lable
	@Override
	public void setEmployeeName(String empName) {
		nameOfEmp.setText(empName);
	}

	

	@Override
	public void setEmployeeId(int empId) {
		idOfEmp.setText(empId+"");
	
	}

	@Override
	public ListBox getAssignedListBox() {
		
		return assignedLeaveTypes;
	}

	@Override
	public void clearAssignedListBox() {
		
		assignedLeaveTypes.clear();
	}

	
}
