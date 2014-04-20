package com.mind.project.client.application.home.showalldetails;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.Label;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.mind.project.client.place.NameTokens;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.EmployeeDB;

public class ShowAllDetailsPresenter
		extends
		Presenter<ShowAllDetailsPresenter.MyView, ShowAllDetailsPresenter.MyProxy> {
	interface MyView extends View 
	{
		

	
		public void clearTable();
		public Label getLable1();
		public void viewEmployeeDetails(Employee employee);
		
		
		
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_ShowAllDetails = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.showalldetails)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<ShowAllDetailsPresenter> {
	}

	@Inject
	public ShowAllDetailsPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

	}

	@Inject
	EmployeeDB empdb;
	
	
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		
		super.prepareFromRequest(request);
		
		//getView().getLable1().setText(request.getParameter("Param","Did not find" ));
		
		Integer id =Integer.parseInt(request.getParameter("Param","Did not find" ));
	
		// get the data from the list and set the data to the view
				Employee emp = empdb.searchEmpDataById(id);
				
				getView().clearTable();
                getView().viewEmployeeDetails(emp);
               		
		
	}

	}

	
	
	
	

