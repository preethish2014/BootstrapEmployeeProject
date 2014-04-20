package com.mind.project.client.application.home.WelcomePresenter.wellcomepresenter;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;


public class WellcomePresenterModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		
		bindPresenter(WellcomePresenterPresenter.class,
				WellcomePresenterPresenter.MyView.class,
				WellcomePresenterView.class,
				WellcomePresenterPresenter.MyProxy.class);
	}
}
