              package com.mind.project.client.application.home.WelcomePresenter.wellcomepresenter;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class WellcomePresenterView extends ViewImpl implements
		WellcomePresenterPresenter.MyView {
	interface Binder extends UiBinder<Widget, WellcomePresenterView> {
	}

	
	

	@Inject
	WellcomePresenterView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Button addDetails;
	
	@UiField
	Button display;
	
	
	@Override
	public Button getAddButton() {
	
		return addDetails;
	}


	@Override
	public Button getDisplayButton() {

		return display;
	}



	

		
}
