package com.mind.project.client.application.editdetails.manager;

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

public class ManagerPresenter extends
		Presenter<ManagerPresenter.MyView, ManagerPresenter.MyProxy> implements
		ManagerUiHandlers {
	interface MyView extends View, HasUiHandlers<ManagerUiHandlers> {
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_Manager = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.manager)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<ManagerPresenter> {
	}

	@Inject
	public ManagerPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

		getView().setUiHandlers(this);
	}

}
