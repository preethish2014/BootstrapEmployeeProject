package com.mind.project.client.application.displaypresenter;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiChild;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.ibm.icu.util.IslamicCalendar;
import com.mind.project.client.application.displaypresenter.leavetypepresenter.LeaveTypePresenterPresenter;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.EmployeeDB;

public class DisplayPresenterView extends ViewWithUiHandlers<DiplayUIHandler>
		implements DisplayPresenterPresenter.MyView {
	interface Binder extends UiBinder<Widget, DisplayPresenterView> {
	}

	@Inject
	DisplayPresenterView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Inject
	EmployeeDB empdb;

	@UiField
	FlexTable empTable;
	
	
	@UiField
	HTMLPanel leavePanel;
	
	//setin slotfun
	@Override
	public void setInSlot(Object slot, IsWidget content) {
		
		super.setInSlot(slot, content);
		
		if(slot == DisplayPresenterPresenter.SLOT_leaveDetails)
		{
			leavePanel.clear();
			
			if(content!=null)
			{
			 leavePanel.add(content);
				}
			
			else{
				super.setInSlot(slot, content);
			}
		}
		
		
		
		
		
		
		
		
		
		
	}

	// method to clear the table every time
	@Override
	public void clearTable() {

		empTable.clear();
		empTable.removeAllRows();
	}

	// Method for setting the Table headings
	public void setColumnHeading() {
		empTable.setWidget(0, 0, new Label("Emp Id"));
		empTable.setWidget(0, 1, new Label("Emp Name"));
		empTable.setWidget(0, 2, new Label("Delete From DB"));
		// adding the Edit Label for the Edit option
		empTable.setWidget(0, 3, new Label("Edit"));
		// adding the LeaveType Label for the LeaveType option
		empTable.setWidget(0, 4, new Label("Edit LeaveTypes"));
	}

	/* Method for displaying the ID, Name and perform the following operations
	 Delete from DB and Edit the LeaveTypes*/
	@Override
	public void addEmployee(final Employee employee) {
        //Decalre Label for displaying the employee id
		final Label idlable = new Label(employee.getEmp_id() + "");
		idlable.setWidth("50px");
		
		
		/* Declare the anchor  for creating the link to the
		 ShowAllDetailsPresenter*/
		Anchor name = new Anchor();
		// Converting the name of the employee as a link to ShowAllDetailsPresenter
		name.setText(employee.getName());
		name.setWidth("50px");

		final Integer empId = employee.getEmp_id();
		final String empName = employee.getName();
		// Click handler for the name_link, which will redirect to the ShowAllDetailsPresenter
		name.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// calling the viewEmpDetils method form view to presenter 
				getUiHandlers().viewEmpDetails(empId);

			}
		});
		/* Declare the edit button  for creating the link to the
		 EditDetailsPresenter*/
		Button editbutton = new Button("Edit");
        editbutton.setWidth("75px");
        editbutton.setHeight("45px");
		// add click handler for the edit button
		editbutton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// calling the editEmpDetils method form view to presenter
				getUiHandlers().editEmpDetails(empId);
			}

		});
        //counter to set the initial size of the table to 0. 
		int sizeOfTable = 0;
		// get the no of rows of the table
		sizeOfTable = empTable.getRowCount();
		
		// set the heading of the table
		if (sizeOfTable <= 0)
			setColumnHeading();

		sizeOfTable++;

		
        
		// Declare the delete button for deleting the employee details.
		Button deletebutton = new Button("DELETE ");
		deletebutton.setWidth("75px");
		deletebutton.setHeight("45px");
		// variable to retain the size of the table 
		final Integer index = sizeOfTable;
		//click handler for the delete button, which will delete a particular  Employee from the EmployeeDB table.  
		deletebutton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
		        // calling the   deleteEmpByID method for deletion of particular employee by id.
				 empdb.deleteEmpByID(employee.getEmp_id());
			     empTable.removeRow(index);
				 setColumnHeading();
				 Window.alert("The Emp " + employee.getName()
								+ "is deleted successfully from DB");
			}

	});
		
		// Declare the delete button for deleting the employee details.
		 Button editLeaveType = new Button("Edit LeaveType");
		editLeaveType.setWidth("85px");
		editLeaveType.setHeight("45px");
	    //click handler for the editLeaveType button, which will delete a particular  Employee from the EmployeeDB table.

		editLeaveType.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// calling the editEmpDetils method form view to presenter
				getUiHandlers().editLeaveTypeDetails(empId,empName);
			}

		});
		
		
		
		
		/* add the idLable , nameAnchor,delete button,edit button and Edit_LeaveTypes button 
		 * to the 1st, 2nd,3rd,4rth and 5th  column of the FlexTable respectively*/
		
		empTable.setWidget(sizeOfTable, 0, idlable);
		empTable.setWidget(sizeOfTable, 1, name);
		empTable.setWidget(sizeOfTable, 2, deletebutton);
		empTable.setWidget(sizeOfTable, 3, editbutton);
		empTable.setWidget(sizeOfTable, 4, editLeaveType );
		
        

	}

	

	@Override
	public FlexTable getFlexTable() {
		
		return empTable;
	}

}
