package com.mind.project.client.application.managerLink.managerlink;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;

import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;

import com.mind.project.client.place.NameTokens;

public class ManagerLinkPresenter extends
		Presenter<ManagerLinkPresenter.MyView, ManagerLinkPresenter.MyProxy> {
	interface MyView extends View {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_ManagerLink = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.managerlinkpresenter)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<ManagerLinkPresenter> {
	}

	@Inject
	public ManagerLinkPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

	}

}
