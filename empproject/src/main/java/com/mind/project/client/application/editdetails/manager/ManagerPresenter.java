package com.mind.project.client.application.editdetails.manager;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.mind.project.client.place.NameTokens;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.EmployeeDB;
import com.mind.project.shared.model.ManagerDB;

public class ManagerPresenter extends
		Presenter<ManagerPresenter.MyView, ManagerPresenter.MyProxy> implements
		ManagerUiHandlers {
	interface MyView extends View, HasUiHandlers<ManagerUiHandlers> {

		void clearList();

		void addEmpToManagersList(Employee employee);

		void addEmpToAvailableEmployees(Employee employee2);

	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_Manager = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.manager)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<ManagerPresenter> {
	}

	@Inject
	public ManagerPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

		getView().setUiHandlers(this);
	}

	@Inject
	EmployeeDB empdb;

	@Inject
	ManagerDB managerdb;

	@Override
	protected void onReset() {

		super.onReset();
		//call the clear method to clear all the lists in the presenter
		getView().clearList();
		//get the employee list from the employeeDB
		List<Employee> emp1 = empdb.getAllEmployees();
	    //iterate through the employee list to get the id of the employee and to add 
	    //each employee into the managers list
		Iterator<Employee> it1 = emp1.iterator();
		while (it1.hasNext()) 
		{
			//get the employee from the employee list 
			Employee employee1 = it1.next();
			//call the method defined in the view to add the employee into the managers list
			getView().addEmpToManagersList(employee1);

		}
		//iterate through the employee list to get the id of the employee and to add 
	    //each employee into the available employees list
		List<Employee> emp2 = empdb.getAllEmployees();	
		Iterator<Employee> it2 = emp2.iterator();
		while (it2.hasNext()) 
		{
			//get the employee from the employee list 
			Employee employee2 = it2.next();
			//call the method defined in the view to add the employee into the available employees list
			getView().addEmpToAvailableEmployees(employee2);

		}

	}

	//method to return the list of the employees stored in the employeeDB
	public List<Employee> getAllEmployees() 
	{
		List<Employee> emp1 = empdb.getAllEmployees();
		return emp1;
	}

	//method to add the manager and its reportees to the map in the managerDB
	@Override
	public void addToManager(Integer mgr_id, List<Integer> emplist) 
	{
	   //call the method in the managerDB to add the manager id and its reportees into the map of managerDB
		managerdb.addReportees(mgr_id, emplist);
		managerdb.getMapCount();
	}

}