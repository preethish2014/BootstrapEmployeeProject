package com.mind.project.client.application.displaypresenter.testpresenter;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
//import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class testPresenterPresenter
		extends
		Presenter<testPresenterPresenter.MyView, testPresenterPresenter.MyProxy> {
	interface MyView extends View {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_testPresenter = new Type<RevealContentHandler<?>>();

	@ProxyStandard
	public interface MyProxy extends Proxy<testPresenterPresenter> {
	}

	@Inject
	public testPresenterPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

	}

}
