package com.mind.project.client.application.test.Login;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
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
import com.mind.project.shared.model.EmployeeDB;
import com.mind.project.shared.model.ManagerDB;

public class LoginPresenter extends
		Presenter<LoginPresenter.MyView, LoginPresenter.MyProxy> {
	interface MyView extends View {
		public TextBox getEmpId();

		public PasswordTextBox getPassword();

		public Button getAddButton();
	}

	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_Test = new Type<RevealContentHandler<?>>();

	@NameToken(NameTokens.testpresenter)
	@ProxyStandard
	public interface MyProxy extends ProxyPlace<LoginPresenter> {
	}

	@Inject
	public LoginPresenter(EventBus eventBus, MyView view, MyProxy proxy) {
		super(eventBus, view, proxy, RevealType.Root);

	}

	@Inject
	PlaceManager placemanager;
	@Inject
	EmployeeDB empdb;
	@Inject
	ManagerDB manDB;

	@Override
	protected void onBind() {
		super.onBind();
		getView().getAddButton().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// employee object contains employeeID and Password , where as
				// employeeid can be Managerid or employeeid itself
				
					
					
				String UserId = getView().getEmpId().getText();
				String Password = getView().getPassword().getText();
				
				
				

				

				// checks for Manager and permit him to his authenticated page
				
				if (UserId.equals("admin")
						&& Password.equals("adminpassword")) 
				{
					PlaceRequest placerequesofwelcomepresenter = new PlaceRequest(
							NameTokens.wellcomepresenter);
					placemanager.revealPlace(placerequesofwelcomepresenter);
					
				}
				
				// parse ( STring - user Name) to Integer 
				
				Employee employee = new Employee(Integer.parseInt(UserId), Password);

				
					if (manDB.searchManageRUserName(Integer.parseInt(UserId))
						
						&& empdb.searchEmpIdAndPassword(employee)) {

					PlaceRequest placerequestofLeaveType = new PlaceRequest(
							NameTokens.managerlinkpresenter);
					placemanager.revealPlace(placerequestofLeaveType);

				}
				// checks for employee and permit him to his authenticated page

				
				 else if(empdb.searchEmpIdAndPassword(employee)) {
				 
				 PlaceRequest placerequestofhome = new
				 PlaceRequest(NameTokens.linkpresenter);
				 placemanager.revealPlace(placerequestofhome);
				 
				  
				  }
				 

				// checks for Admin and permit him to all pages
/*
				else if (UserId.equals("admin")
						&& Password.equals("adminpassword")) {
					PlaceRequest placerequesofwelcomepresenter = new PlaceRequest(
							NameTokens.wellcomepresenter);
					placemanager.revealPlace(placerequesofwelcomepresenter);

				}*/
				
				
				

				else {
					Window.alert("Authentication Failure");
				}

			}
		});

	}

}
/*
 * if(empdb.searchEmpIdAndPassword(employee)) {
 * 
 * PlaceRequest placerequestofhome = new PlaceRequest(NameTokens.linkpresenter);
 * placemanager.revealPlace(placerequestofhome);
 * 
 * 
 * }
 */