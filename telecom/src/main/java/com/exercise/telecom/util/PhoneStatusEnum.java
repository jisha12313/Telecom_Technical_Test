package com.exercise.telecom.util;

public enum PhoneStatusEnum {
	STATUS_ACTIVE ("ACTV"), STATUS_DEACTIVE("INAC"), ;
	
    private final String status;
    
    private PhoneStatusEnum(String status) {
        this.status = status;
    }
	public String getStatus() {
		return status; 
	}
}
