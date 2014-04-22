package com.mind.project.client.application.EmployeeLoginLink;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EmployeeLoginLinkPageModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(EmployeeLoginLinkPagePresenter.class, EmployeeLoginLinkPagePresenter.MyView.class,
				EmployeeLoginLinkPageView.class, EmployeeLoginLinkPagePresenter.MyProxy.class);
	}
}
