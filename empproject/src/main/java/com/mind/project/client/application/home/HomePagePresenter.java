/**
 * Copyright 2012 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.mind.project.client.application.home;

import com.mind.project.client.application.ApplicationPresenter;
import com.mind.project.client.place.NameTokens;
import com.mind.project.shared.model.Employee;
import com.mind.project.shared.model.EmployeeDB;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.mind.project.shared.model.ManagerDB;

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy> {
    public interface MyView extends View
    {
        //create the contract bw the presenter and the view and implement these methods in view 
    	public Button getAddButton();
        public TextBox getEmpIdTextBox();
        public TextBox getEmpNameTextBox();
        public TextBox getEmpAgeTextBox();
        public TextBox getAddressTextBox();
        public TextBox getPhoneNoTextBox();
       
       public PasswordTextBox getPassword();
    
    
    
    }

    @ProxyStandard
    @NameToken(NameTokens.home)
    public interface MyProxy extends ProxyPlace<HomePagePresenter> {
    }

    @Inject
    public HomePagePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
        
    }
    
    @Inject
    PlaceManager placemanager;
    @Inject
    EmployeeDB empdb;
    @Inject
    ManagerDB managerDB;
    
    
   /* @Override
	public void prepareFromRequest(PlaceRequest request) {
		
		super.prepareFromRequest(request);
		(request.getParameter("Param","Did not find" ));
	}
	*/
    
    
    @Override
    protected void onBind() {
    	super.onBind();
    	getView().getAddButton().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			   //call the addemp function
				Employee employee1 = new Employee(Integer.parseInt(getView().getEmpIdTextBox().getText()),Integer.parseInt(getView().getEmpAgeTextBox().getText())
						                ,getView().getEmpNameTextBox().getText()
						                ,getView().getAddressTextBox().getText(),
						                Long.parseLong(getView().getPhoneNoTextBox().getText()),getView().getPassword().getText());
				
				empdb.addEmployee(employee1);
			
				Window.alert("The Employee Named\t "+employee1.getName()+"\tis successfully added to the database");
				
				
				
			}
		});
    		
    
    
    
}
    }