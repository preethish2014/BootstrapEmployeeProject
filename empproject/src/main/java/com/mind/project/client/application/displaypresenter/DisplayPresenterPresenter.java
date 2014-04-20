package com.mind.project.client.application.displaypresenter;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;
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
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.mind.project.client.application.displaypresenter.leavetypepresenter.LeaveTypePresenterPresenter;
import com.mind.project.client.place.NameTokens;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.EmployeeDB;

public class DisplayPresenterPresenter
		extends
		Presenter<DisplayPresenterPresenter.MyView, DisplayPresenterPresenter.MyProxy>
		implements DiplayUIHandler {
	interface MyView extends View, HasUiHandlers<DiplayUIHandler> {
		public void addEmployee(Employee employee);

		public void clearTable();

		// public Button getLeaveTypeButton();
		public FlexTable getFlexTable();

	}

	public static final Object SLOT_leaveDetails = new Object();

	@Override
	protected void onBind() {
		super.onBind();
		getView().setUiHandlers(this);
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_DisplayPresenter = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.displaypresenter)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<DisplayPresenterPresenter> {
	}

	@Inject
	public DisplayPresenterPresenter(EventBus eventBus, MyView view,
			MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

	}

	/*
	 * @Override protected void revealInParent() { super.revealInParent();
	 * RevealContentEvent.fire(this,
	 * DisplayPresenterPresenter.SLOT_DisplayPresenter,this); }
	 */

	// Injecting EmployeeDB object for accessing the whole list of employees.
	@Inject
	EmployeeDB empdb;
	@Inject
	PlaceManager placemanager;
	@Inject
	LeaveTypePresenterPresenter leaveTypePresenter;

	public void viewEmpDetails(Integer empId) {

		PlaceRequest placerequest = new PlaceRequest(NameTokens.showalldetails)
				.with("Param", empId.toString());
		placemanager.revealPlace(placerequest);

	}

	public void editEmpDetails(Integer empId) {

		PlaceRequest placerequest = new PlaceRequest(NameTokens.editdetails)
				.with("Param", empId.toString());
		placemanager.revealPlace(placerequest);

	}

	@Override
	protected void onReset() {

		super.onReset();
		// Making sure to start with a clean Slate/Clearing the previous result
		getView().clearTable();
		// get the data from the list and set the data to the view
		List<Employee> emp = empdb.getAllEmployees();
		Iterator<Employee> it = emp.iterator();
		while (it.hasNext()) {
			final Employee employee = it.next();
			// get the view for this presenter for adding particular employee
			// details
			getView().addEmployee(employee);

		}

	}

	// call the setInslot of the view
	@Override
	public void editLeaveTypeDetails(Integer empId, String empName) {
		leaveTypePresenter.setEmpName(empName);
		leaveTypePresenter.setEmpId(empId);
		leaveTypePresenter.showAssignedLeaves(empId);
		
		setInSlot(SLOT_leaveDetails, leaveTypePresenter);
        System.out.println("\n entered into the leavetype details \n ");
        
       
        
        
	}
}
