package com.mind.project.client.application.displaypresenter.testpresenter;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class testPresenterView extends ViewImpl implements
		testPresenterPresenter.MyView {
	interface Binder extends UiBinder<Widget, testPresenterView> {
	}

	@UiField
	SimplePanel main;

	@Inject
	testPresenterView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == testPresenterPresenter.SLOT_testPresenter) {
			main.setWidget(content);
		} else {
			super.setInSlot(slot, content);
		}
	}
}
