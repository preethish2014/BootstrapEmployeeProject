package com.mind.project.client.application.managerLink.managerlink;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ManagerLinkModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(ManagerLinkPresenter.class,
				ManagerLinkPresenter.MyView.class, ManagerLinkView.class,
				ManagerLinkPresenter.MyProxy.class);
	}
}
