package com.mind.project.client.application.managerLink.managerlink;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ManagerLinkView extends ViewImpl implements
		ManagerLinkPresenter.MyView {
	interface Binder extends UiBinder<Widget, ManagerLinkView> {
	}

	@UiField
	SimplePanel main;

	@Inject
	ManagerLinkView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setInSlot(Object slot, IsWidget content) {
		if (slot == ManagerLinkPresenter.SLOT_ManagerLink) {
			main.setWidget(content);
		} else {
			super.setInSlot(slot, content);
		}
	}
}
