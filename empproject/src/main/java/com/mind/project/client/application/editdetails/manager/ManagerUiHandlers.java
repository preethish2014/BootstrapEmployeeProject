package com.mind.project.client.application.editdetails.manager;

import java.util.List;

import com.gwtplatform.mvp.client.UiHandlers;
import com.mind.project.shared.model.Employee;

public interface ManagerUiHandlers extends UiHandlers {
	
	public List<Employee> getAllEmployees();

	public void addToManager(Integer mgr_id, List<Integer> emplist);
	
}