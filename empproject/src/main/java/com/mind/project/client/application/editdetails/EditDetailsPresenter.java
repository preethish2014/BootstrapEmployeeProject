package com.mind.project.client.application.editdetails;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
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
import com.mind.project.shared.model.EmployeeDB;

public class EditDetailsPresenter extends
		Presenter<EditDetailsPresenter.MyView, EditDetailsPresenter.MyProxy>
		implements EditDetailsUiHandlers {
	interface MyView extends View, HasUiHandlers<EditDetailsUiHandlers> {
		Label getId();

		TextBox getAge();

		TextBox getName();

		TextBox getAddress();
		TextBox getPhno();

		public void editviewEmployeeDetails(Employee employee);
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_EditDetails = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.editdetails)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<EditDetailsPresenter> {
	}

	@Inject
	public EditDetailsPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

		getView().setUiHandlers(this);
	}

	@Inject
	EmployeeDB empdb;
	@Inject
	PlaceManager placemanager;

	@Override
	public void prepareFromRequest(PlaceRequest request) {

		super.prepareFromRequest(request);
		String id = request.getParameter("Param", "Not found");

		Integer emp_id = Integer.parseInt(id);

		Employee employee = empdb.searchEmpDataById(emp_id);
		getView().editviewEmployeeDetails(employee);

	}

	@Override
	public void getEmpValues(int id1) {
		empdb.deleteEmpByID(id1);
		Employee empObj=new Employee(Integer.parseInt(getView().getId().getText()),Integer.parseInt(getView().getAge().getText()),
				           getView().getName().getText(),getView().getAddress().getText(),Long.parseLong(getView().getPhno().getText()));
				   
				//call to the addEmployee Method and pass the object of employee 
				empdb.addEmployee(empObj);
				Window.alert("Successfuly Updated");
			
		}

}
