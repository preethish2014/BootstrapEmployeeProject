package com.mind.project.client.application.applyLeave.applyleave;



import java.util.List;

import com.google.gwt.event.shared.GwtEvent.Type;
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
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.LeaveTypeDB;

public class ApplyLeavePresenter extends
		Presenter<ApplyLeavePresenter.MyView, ApplyLeavePresenter.MyProxy>
		implements ApplyLeaveUiHandlers {
	interface MyView extends View, HasUiHandlers<ApplyLeaveUiHandlers> {
		
		public void getAssigendLeave(List<String> assignLeave);
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_ApplyLeave = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.applyLeave)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<ApplyLeavePresenter> {
	}

	@Inject
	public ApplyLeavePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

		getView().setUiHandlers(this);
	}
	@Inject
	LeaveTypeDB leavetypeDb;
	@Inject
	PlaceManager placemanager;

	@Override
	public void prepareFromRequest(PlaceRequest request) {

		super.prepareFromRequest(request);
		String id = request.getParameter("Param", "Not found");

		Integer emp_id = Integer.parseInt(id);

		List<String> assignLeave =leavetypeDb.searchLeavesByEmpId( emp_id) ;
		getView().getAssigendLeave(assignLeave);

	}

	
	
	
	
}
