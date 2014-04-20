package com.mind.project.client.application.applyLeave.applyleave;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplyLeaveModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(ApplyLeavePresenter.class,
				ApplyLeavePresenter.MyView.class, ApplyLeaveView.class,
				ApplyLeavePresenter.MyProxy.class);
	}
}
