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

package com.mind.project.client.application;

import com.mind.project.client.application.home.HomeModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.mind.project.client.application.displaypresenter.DisplayPresenterModule;
import com.mind.project.client.application.displaypresenter.leaveapproval.LeaveApprovalModule;
import com.mind.project.client.application.editdetails.EditDetailsModule;
import com.mind.project.client.application.editdetails.manager.ManagerModule;
import com.mind.project.client.application.EmployeeLoginLink.EmployeeLoginLinkPageModule;
import com.mind.project.client.application.applyLeave.applyleave.ApplyLeaveModule;
import com.mind.project.client.application.test.Login.LoginModule;
import com.mind.project.client.application.managerLink.managerlink.ManagerLinkModule;





public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
     
		
		
    	install(new ManagerModule());
    	install(new LeaveApprovalModule());
		install(new ManagerLinkModule());
		install(new EmployeeLoginLinkPageModule());
		install(new LoginModule());

		install(new ApplyLeaveModule());
		install(new EditDetailsModule());
		install(new DisplayPresenterModule());
		install(new HomeModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}