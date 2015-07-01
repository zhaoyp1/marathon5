package com.asiainfo.baas.common;

public enum OfferingStatus {
	PLANNED("PLANND"), OBSOLETE("OBSOLETE"), ACTIVE("ACTIVE");
	
	private String stringValue;
	
	private  OfferingStatus(String stringValue){
		this.stringValue=stringValue;
	}
	 
	public String getValue() {
		return stringValue;
	}
}