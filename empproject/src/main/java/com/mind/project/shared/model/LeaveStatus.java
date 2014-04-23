package com.mind.project.shared.model;

public enum LeaveStatus 
{
    PENDING("PENDING"),APPROVED("APPROVED"),REJECTED("REJECTED");
    
    String status;
    
    LeaveStatus(String status)
    {
    	this.status= status;
    }
    
 
}
