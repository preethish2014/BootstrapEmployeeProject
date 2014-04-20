package com.mind.project.client.application.home.showalldetails;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.mind.project.client.application.displaypresenter.leavetypepresenter.LeaveTypePresenterPresenter;
import com.mind.project.shared.model.Employee;

public class ShowAllDetailsView extends ViewImpl implements
		ShowAllDetailsPresenter.MyView {
	interface Binder extends UiBinder<Widget, ShowAllDetailsView> {
	}

	
	@Inject
	ShowAllDetailsView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}


	
@UiField
Label resultLable;

	@Override
	public void clearTable() {
		
		displayTable.clear();
		displayTable.removeAllRows();
	}
	
@Inject
LeaveTypePresenterPresenter leaveTypePresenter;
	
   @UiField
	FlexTable displayTable;
	
	@Override
	public Label getLable1() {
		
		return resultLable;
	}
 
	public void setColumnHeading() {
		displayTable.setWidget(0, 0, new Label("Emp Id"));
		displayTable.setWidget(0, 1, new Label("Age"));
		displayTable.setWidget(0, 2, new Label(" Name"));
		displayTable.setWidget(0, 3, new Label("Address"));
		displayTable.setWidget(0, 4, new Label("Phone No"));
	}
	@Override
	public void viewEmployeeDetails(Employee employee) 
	{
		Label idlable = new Label(employee.getEmp_id() + "");
		idlable.setWidth("50px");
		Label ageLable = new Label(employee.getAge() + "");
		ageLable.setWidth("50px");
		Label nameLable = new Label(employee.getName());
		nameLable.setWidth("50px");
		Label addressLable = new Label(employee.getAddress());
		addressLable.setWidth("50px");
		Label phnoLable = new Label(employee.getPhno() + "");
		phnoLable.setWidth("50px");
		
		int sizeOfTable = 0;
		// adding the final row to the Main Panel
		sizeOfTable = displayTable.getRowCount();
		if (sizeOfTable <= 0)
			setColumnHeading();
		sizeOfTable++;

		displayTable.setWidget(sizeOfTable, 0, idlable);
		displayTable.setWidget(sizeOfTable, 1, ageLable);
		displayTable.setWidget(sizeOfTable, 2, nameLable);
		displayTable.setWidget(sizeOfTable, 3, addressLable);
		displayTable.setWidget(sizeOfTable, 4, phnoLable);
		
		
	}
		
		
		
		
	}





	

	

