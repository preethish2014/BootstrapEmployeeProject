package com.mind.project.client.application.editdetails;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.mind.project.shared.model.Employee;

public class EditDetailsView extends ViewWithUiHandlers<EditDetailsUiHandlers>
		implements EditDetailsPresenter.MyView {
	interface Binder extends UiBinder<Widget, EditDetailsView> {
	}

	@Inject
	EditDetailsView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Label id;
	@UiField
	TextBox age;
	@UiField
	TextBox name;
	@UiField
	TextBox address;
	@UiField
	TextBox phNo;
	@UiField
	Button button;

	@Override
	public Label getId() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public TextBox getAge() {
		// TODO Auto-generated method stub
		return age;
	}

	@Override
	public TextBox getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public TextBox getAddress() {
		// TODO Auto-generated method stub
		return address;
	}

	@Override
	public void editviewEmployeeDetails(Employee employee) {
		id.setText(employee.getEmp_id() + "");
		id.setWidth("100px");
		age.setText(employee.getAge() + "");
		age.setWidth("100px");
		name.setText(employee.getName());
		name.setWidth("100px");
		address.setText(employee.getAddress());
		address.setWidth("100px");
		phNo.setText(employee.getPhno()+"");
		phNo.setWidth("100px");
		button.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				Integer id1 = Integer.parseInt(id.getText());

				getUiHandlers().getEmpValues(id1);

			}
		});

	}

	@Override
	public TextBox getPhno() {
		
		return phNo;
	}

}
