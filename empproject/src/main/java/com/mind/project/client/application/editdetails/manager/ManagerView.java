package com.mind.project.client.application.editdetails.manager;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ManagerView extends ViewWithUiHandlers<ManagerUiHandlers>
		implements ManagerPresenter.MyView {
	interface Binder extends UiBinder<Widget, ManagerView> {
	}



	@Inject
	ManagerView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
	@UiField
	ListBox managers;
	
	@UiField
	ListBox availableEmployees;
	
	@UiField
	ListBox assignedEmployees;
	
	@UiField
	Button addButn;
	
	@UiField
	Button delButn;

	
	
	
	
}
