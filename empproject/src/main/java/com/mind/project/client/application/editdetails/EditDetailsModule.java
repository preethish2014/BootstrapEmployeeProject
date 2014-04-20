package com.mind.project.client.application.editdetails;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EditDetailsModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(EditDetailsPresenter.class,
				EditDetailsPresenter.MyView.class, EditDetailsView.class,
				EditDetailsPresenter.MyProxy.class);
	}
}
