package com.mind.project.client.application.applyLeave.applyleave;

import java.util.List;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ApplyLeaveView extends ViewWithUiHandlers<ApplyLeaveUiHandlers>
		implements ApplyLeavePresenter.MyView {
	interface Binder extends UiBinder<Widget, ApplyLeaveView> {
	}

	@UiField
	SimplePanel main;
	@UiField
	TextBox empId,leaveDesc,start_date,end_date,status;
	@UiField
	ListBox assignedLeaveTypes;
	@UiField
	Button applyButton;
	
	@Inject
	ApplyLeaveView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == ApplyLeavePresenter.SLOT_ApplyLeave) {
			main.setWidget(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	@Override
	public void getAssigendLeave(List<String> assignLeave) {
		// TODO Auto-generated method stub
		
	}
}
