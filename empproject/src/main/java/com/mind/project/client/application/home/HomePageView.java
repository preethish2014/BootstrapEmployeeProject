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

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {
    public interface Binder extends UiBinder<Widget, HomePageView> {
    }

    @Inject
    public HomePageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        
    
    }
    
    @UiField
	TextBox empId;
    @UiField
    TextBox empAge;
    
    @UiField
    TextBox empName;
    @UiField
    TextBox address;
    @UiField
    TextBox phoneNo;
	@UiField
	Button addButton;
	@UiField
	PasswordTextBox password;
	@UiField
	Hyperlink clikme;
	
	public PasswordTextBox getPassword() {
		return password;
	}
	@Override
	public Button getAddButton() {
		
		return addButton;
	}
	@Override
	public TextBox getEmpIdTextBox() {
		
		return empId;
	}
	@Override
	public TextBox getEmpNameTextBox() {
	
		return empName;
	}
	@Override
	public TextBox getEmpAgeTextBox() {
		
		return empAge;
	}
	@Override
	public TextBox getAddressTextBox() {
		
		return address;
	}
	@Override
	public TextBox getPhoneNoTextBox() {
	
		return phoneNo;
	}
	
	
    
    
}