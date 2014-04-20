package com.mind.project.client.application.displaypresenter;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.mind.project.client.application.displaypresenter.leavetypepresenter.LeaveTypePresenterModule;
import com.mind.project.client.application.displaypresenter.testpresenter.testPresenterModule;

public class DisplayPresenterModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		install(new testPresenterModule());
		install(new LeaveTypePresenterModule());
		bindPresenter(DisplayPresenterPresenter.class,
				DisplayPresenterPresenter.MyView.class,
				DisplayPresenterView.class,
				DisplayPresenterPresenter.MyProxy.class);
	}
}
