package com.mind.project.client.application.EmployeeLoginLink;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class EmployeeLoginLinkPageView extends ViewImpl implements EmployeeLoginLinkPagePresenter.MyView {
	interface Binder extends UiBinder<Widget, EmployeeLoginLinkPageView> {
	}

	@UiField
	SimplePanel main;

	@Inject
	EmployeeLoginLinkPageView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == EmployeeLoginLinkPagePresenter.SLOT_LinkPage) {
			main.setWidget(content);
		} else {
			super.setInSlot(slot, content);
		}
	}
}
