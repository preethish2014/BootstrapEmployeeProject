package com.mind.project.client.application.applyLeave.applyleave;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.Leave;

public class ApplyLeaveView extends ViewWithUiHandlers<ApplyLeaveUiHandlers>
		implements ApplyLeavePresenter.MyView {
	interface Binder extends UiBinder<Widget, ApplyLeaveView> {
	}

	@UiField
	FlexTable displayTable;
	@UiField
	TextBox empId;
	@UiField
	TextBox leaveDesc, end_date, status, start_date;
	@UiField
	HTMLPanel dates, dates1;

	@UiField
	ListBox assignedLeaveTypes;
	@UiField
	Button applyButton;

	@Inject
	ApplyLeaveView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		applyButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				getUiHandlers().applyLeave();
			}
		});
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == ApplyLeavePresenter.SLOT_ApplyLeave) {
			// main.setWidget(content);
		} else {
			super.setInSlot(slot, content);
		}
	}
	DatePicker startdatePicker = new DatePicker();
	DatePicker enddatePicker = new DatePicker();
	// get emp_id and list of assigned Leave
	@Override
	public void getAssigendLeave(Integer emp_id, List<String> assignLeave) {
		

		// set the employee id into the TextBox

		empId.setText(emp_id + "");
		empId.setEnabled(false);
		//assignedLeaveTypes.clear();
		// add the all the assigned leave into the listBox
		for (String value : assignLeave) {
			assignedLeaveTypes.addItem(value);
		}

		status.setText("PENDING");
		status.setEnabled(false);

		

		// Set the value in the start date text box when the user selects a date
		startdatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getMediumDateFormat()
						.format(date);
				start_date.setText(dateString);
			}
		});

		// Set the default value
		startdatePicker.setValue(new Date(), true);
		dates.add(startdatePicker);

		

		// Set the value in the end_date text box when the user selects a date
		enddatePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date date = event.getValue();
				String dateString = DateTimeFormat.getMediumDateFormat()
						.format(date);
				end_date.setText(dateString);
			}
		});

		// Set the default value
		enddatePicker.setValue(new Date(), true);
		dates1.add(enddatePicker);

		
	}
	
//Flax Table  for Storing All the apply leave Details
	public void setColumnHeading() {
		displayTable.setWidget(0, 0, new Label("Emp Id"));
		displayTable.setWidget(0, 1, new Label("LeaveType"));
		displayTable.setWidget(0, 2, new Label("LeaveDescription"));
		displayTable.setWidget(0, 3, new Label("Start_date"));
		displayTable.setWidget(0, 4, new Label("End_date"));
		displayTable.setWidget(0, 5, new Label("Status"));
	}

	@Override
	public void getApplyLeave(Leave applyLeaveList) {

		Label idlable = new Label(applyLeaveList.getEmp_id() + "");
		idlable.setWidth("50px");
		Label leaveTypeLable = new Label(applyLeaveList.getLeaveType());
		leaveTypeLable.setWidth("200px");
		Label leaveDescLable = new Label(applyLeaveList.getLeaveDesc());
		leaveDescLable.setWidth("200px");
		Label start_dateLable = new Label(applyLeaveList.getStart_date());
		start_dateLable.setWidth("200px");
		Label end_dateLable = new Label(applyLeaveList.getEnd_date());
		end_dateLable .setWidth("200px");
		Label statusLable = new Label(applyLeaveList.getStatus());
		statusLable.setWidth("200px");

		int sizeOfTable = 0;
		// adding the final row to the Main Panel
		sizeOfTable = displayTable.getRowCount();
		if (sizeOfTable <= 0)
			setColumnHeading();
		sizeOfTable++;

		displayTable.setWidget(sizeOfTable, 0, idlable);
		displayTable.setWidget(sizeOfTable, 1, leaveTypeLable);
		displayTable.setWidget(sizeOfTable, 2, leaveDescLable);
		displayTable.setWidget(sizeOfTable, 3, start_dateLable);
		displayTable.setWidget(sizeOfTable, 4, end_dateLable);
		displayTable.setWidget(sizeOfTable, 5, statusLable);

	}

	public TextBox getLeaveDesc() {
		return leaveDesc;
	}

	public TextBox getStart_date() {
		return start_date;
	}

	public TextBox getEnd_date() {
		return end_date;
	}

	public TextBox getStatus() {
		return status;
	}

	public TextBox getEmpId() {
		return empId;
	}

	public ListBox getAssignedLeaveTypes() {

		return assignedLeaveTypes;
	}
	@Override
	public void clearAssignedListBox() {
		
		assignedLeaveTypes.clear();
	}

	// TODO Auto-generated method stub

}
