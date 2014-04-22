package com.mind.project.client.application.editdetails.manager;

import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.EmployeeDB;

public class ManagerView extends ViewWithUiHandlers<ManagerUiHandlers>
		implements ManagerPresenter.MyView {
	interface Binder extends UiBinder<Widget, ManagerView> {
	}

	@Inject
	ManagerView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		clearList();
		// add the change handler to the managers list to assign reportees to
		// more than one manager
		managerList.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// clear the available Employees list and the assign Employees list
				availableEmployees.clear();
				assignedEmployees.clear();
				// get the employees list from the emploeeDB
				List<Employee> employeeList = getUiHandlers().getAllEmployees();

				// get the index of the selected manager_id from the Manager
				// List
				Integer index = managerList.getSelectedIndex();
				// Iterate through the employees list
				Iterator<Employee> it = employeeList.iterator();
				while (it.hasNext()) {
					Employee employee = it.next();
					Integer emp_id = employee.getEmp_id();
					// check if emp_id does not match with the emp_id of the manager
					// list then add it to the available employees list
					if (!managerList.getItemText(index).equals(emp_id.toString())) {
						availableEmployees.addItem(emp_id.toString(),employee.getName());

					}

				}

			}
		});

		//add the clickHandler to the add button to add the emp_id to the assigned employees list
		addButn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) 
			{

				//get the index of the emp_id selected in  the available employee
                //list and parse it from String to the integer type
				Integer availableIndex = availableEmployees.getSelectedIndex();
				Integer employee_id = Integer.parseInt(availableEmployees
						.getItemText(availableIndex));
				// get the count of the items in the assignedEmployees list
				Integer count = assignedEmployees.getItemCount();
				//check if there is no value in the assigned employee list, then add the firstemp_id
				if (count == 0) {
					assignedEmployees.addItem(employee_id + "");

				} 
			// now if already the assigned employee list has some values, then go to the else part
				else 
				{
					Integer index;
					//set a flag to check if the emp_id of the available employees list is already 
					//present in  the assigned employees list
					Boolean flag = false;
					// Now, iterate through the assigned employees list to match the values
					for (index = 0; index < count; index++)
					{
					 //check if the emp_id of the available employees list is not present in the  
					 //assigned employees list,then set the flag to false
						if (!(employee_id.toString().equals(assignedEmployees
								.getItemText(index)))) 
						{
							flag = false;
						}
                     //Now, if the emp_id of the available employees list already present in the  
					//assigned employees list,then show an alert and set the flag to true
						else 
						{
							flag = true;
							Window.alert("The reportee" + employee_id
									+ "is already assigned to the manager");
							break;
						}
					}
					//if the flag is false, add the emp_id to the assigned employees list
					if (flag == false)
						assignedEmployees.addItem(employee_id + "");
				}
			}
		});

	 //add the clickHandler for the delete button to delete the emp_id from the assigned employee list
		delButn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) 
			{
              //get the index of the emp_id selected in  the available employee list 
				Integer Index = assignedEmployees.getSelectedIndex();
				//and now remove emp_id of the selected index from the assigned employees list
				assignedEmployees.removeItem(Index);

			}

		});
  
		//add the clickHandler for the save button to save the list of the employees under a manager
		saveButn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) 
			{
				String nextemployee;
				//create a list of Integer type to store the emp_ids of the assigned employees list
				List<Integer> emplist = new ArrayList<Integer>();
				//get the count of the emp_id in the assigned employees list
				Integer empcount = assignedEmployees.getItemCount();
				
                //Now,iterate through the assigned employees list to get all the emp_ids in
				//the list and store in the list
				for (int i = 0; i < empcount; i++) {
					//get the string emp_id from the index and parse it to Integer type
					nextemployee = assignedEmployees.getItemText(i);
					Integer employeeID = Integer.parseInt(nextemployee);
					emplist.add(employeeID);
				}

				//here,get the index of the selected emp_id from the Manager List
				Integer index = managerList.getSelectedIndex();
				//now,get the emp_id from the index and parse it to Integer 
				//type to store in the list of Integer type
				String id = managerList.getItemText(index);
				Integer mgr_id = Integer.parseInt(id);
			  //call the method defined in the presenter to store the manager id and its reportees to the map
				getUiHandlers().addToManager(mgr_id, emplist);
				Window.alert("The reportees are added to the manager");

			}
		});

	}

	@UiField
	ListBox managerList;

	@UiField
	ListBox availableEmployees;

	@UiField
	ListBox assignedEmployees;

	@UiField
	Button addButn;

	@UiField
	Button delButn;

	@UiField
	Button saveButn;

	@Inject
	EmployeeDB empdb;
	
	
//method to clear the content of all list boxes 
	public void clearList() 
	{

		managerList.clear();
		availableEmployees.clear();
		assignedEmployees.clear();

	}

	//method to add the employees' emp_id from the employeeDB and name to the Managers list
	@Override
	public void addEmpToManagersList(Employee employee) 
	{
              
		//get the emp_id of the employee from the employeeDB
		Integer emp_id = employee.getEmp_id();
        //add the emp_id and the em_name to the managers List
		managerList.addItem(emp_id.toString(), employee.getName());

	}

	//method to add the employees' emp_id of the employeeDB and name to the available employees list
	public void addEmpToAvailableEmployees(Employee employee2) 
	{
		//get the index of the selected emp_id  ofthe managers list
		Integer index = managerList.getSelectedIndex();
		//get the emp_id of the employee from the employeeDB
		Integer emp_id = employee2.getEmp_id();
		//check if the employee's emp_id of the employeeDB does not match with the manager's emp_id, 
		//then add the emp_id to the available employees list
		if (!managerList.getItemText(index).equals(emp_id.toString())) {

			//add the emp_id and name of the available employee to the assigned employee list
			availableEmployees.addItem(emp_id.toString(), employee2.getName());

		}

	}

}