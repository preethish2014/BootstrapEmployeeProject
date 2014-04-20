package com.mind.project.client.application.displaypresenter.testpresenter;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class testPresenterModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(testPresenterPresenter.class,
				testPresenterPresenter.MyView.class, testPresenterView.class,
				testPresenterPresenter.MyProxy.class);
	}
}
