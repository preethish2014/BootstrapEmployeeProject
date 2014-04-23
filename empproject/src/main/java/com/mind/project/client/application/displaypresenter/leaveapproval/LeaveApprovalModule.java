package com.mind.project.client.application.displaypresenter.leaveapproval;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class LeaveApprovalModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(LeaveApprovalPresenter.class,
				LeaveApprovalPresenter.MyView.class, LeaveApprovalView.class,
				LeaveApprovalPresenter.MyProxy.class);
	}
}
