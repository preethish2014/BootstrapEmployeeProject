package com.mind.project.client.application.home.showalldetails;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ShowAllDetailsModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(ShowAllDetailsPresenter.class,
				ShowAllDetailsPresenter.MyView.class, ShowAllDetailsView.class,
				ShowAllDetailsPresenter.MyProxy.class);
	}
}
