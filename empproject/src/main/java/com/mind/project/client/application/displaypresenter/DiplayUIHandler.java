package com.mind.project.client.application.displaypresenter;

import com.gwtplatform.mvp.client.UiHandlers;

public interface DiplayUIHandler extends UiHandlers {
	public void viewEmpDetails(Integer empId);

	public void editEmpDetails(Integer empId);

	public void editLeaveTypeDetails(Integer empId,String empName);
}
