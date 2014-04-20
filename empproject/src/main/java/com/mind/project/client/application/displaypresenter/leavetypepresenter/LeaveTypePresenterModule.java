package com.mind.project.client.application.displaypresenter.leavetypepresenter;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class LeaveTypePresenterModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(LeaveTypePresenterPresenter.class,
				LeaveTypePresenterPresenter.MyView.class,
				LeaveTypePresenterView.class,
				LeaveTypePresenterPresenter.MyProxy.class);
	}
}
