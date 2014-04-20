package com.mind.project.client.application.home.WelcomePresenter.wellcomepresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.mind.project.client.place.NameTokens;

public class WellcomePresenterPresenter
		extends
		Presenter<WellcomePresenterPresenter.MyView, WellcomePresenterPresenter.MyProxy> {
	interface MyView extends View {
		public Button getAddButton();

		public Button getDisplayButton();

	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_WellcomePresenter = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.wellcomepresenter)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<WellcomePresenterPresenter> {
	}

	@Inject
	public WellcomePresenterPresenter(EventBus eventBus, MyView view,
			MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

	}

	@Inject
	PlaceManager placemanager;

	/*@Override
	protected void onReset() {
		super.onReset();
		System.out.println("Onreset called ");
		getView().getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// call the home presenter
				PlaceRequest placerequest = new PlaceRequest(NameTokens.home);
				placemanager.revealPlace(placerequest);
				System.out.println("Clicked add button");

			}
		});
	}*/

	// Call the Home Presenter for add emp data
	@Override
	protected void onBind() {
		super.onBind();

		getView().getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// call the home presenter
				PlaceRequest placerequest = new PlaceRequest(NameTokens.home);
				placemanager.revealPlace(placerequest);
				System.out.println("Clicked add button");

			}
		});

		///System.out.println("On bind called");
		getView().getDisplayButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// call the home presenter
				PlaceRequest placerequest = new PlaceRequest(NameTokens.displaypresenter);
				placemanager.revealPlace(placerequest);

			}
		});

	}
}
