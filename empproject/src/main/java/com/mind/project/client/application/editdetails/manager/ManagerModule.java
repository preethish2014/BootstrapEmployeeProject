package com.mind.project.client.application.editdetails.manager;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ManagerModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(ManagerPresenter.class, ManagerPresenter.MyView.class,
				ManagerView.class, ManagerPresenter.MyProxy.class);
	}
}