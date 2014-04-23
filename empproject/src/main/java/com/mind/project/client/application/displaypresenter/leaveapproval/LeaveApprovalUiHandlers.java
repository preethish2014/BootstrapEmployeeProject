package com.mind.project.client.application.displaypresenter.leaveapproval;

import com.gwtplatform.mvp.client.UiHandlers;
import com.mind.project.shared.model.Leave;

public interface LeaveApprovalUiHandlers extends UiHandlers {



	void approveLeave(Leave leave);

	void rejectLeave(Leave leave);
}
