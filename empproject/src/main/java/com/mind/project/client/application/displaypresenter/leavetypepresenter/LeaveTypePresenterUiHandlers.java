package com.mind.project.client.application.displaypresenter.leavetypepresenter;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;

public interface LeaveTypePresenterUiHandlers extends UiHandlers {
	public void saveEmpLeaveDetails(Integer emp_id, List<String> leavelist);
}
