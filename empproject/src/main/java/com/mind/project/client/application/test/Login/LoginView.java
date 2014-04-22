package com.mind.project.client.application.test.Login;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class LoginView extends ViewImpl implements LoginPresenter.MyView {
	interface Binder extends UiBinder<Widget, LoginView> {
	}

	@UiField
	SimplePanel main;
	
	@UiField
	TextBox empId;
	
	@UiField
	PasswordTextBox password;
	
	public TextBox getEmpId() {
		return empId;
	}

	
	public PasswordTextBox getPassword() {
		return password;
	}


	public Button getAddButton() {
		return addButton;
	}




	@UiField
	Button addButton;


	@Inject
	LoginView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == LoginPresenter.SLOT_Test) {
			main.setWidget(content);
		} else {
			super.setInSlot(slot, content);
		}
	}
}
