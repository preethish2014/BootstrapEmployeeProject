package com.mind.project.client.application.editdetails;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.mind.project.client.application.editdetails.manager.ManagerModule;

public class EditDetailsModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new ManagerModule());
		bindPresenter(EditDetailsPresenter.class,
				EditDetailsPresenter.MyView.class, EditDetailsView.class,
				EditDetailsPresenter.MyProxy.class);
	}
}
