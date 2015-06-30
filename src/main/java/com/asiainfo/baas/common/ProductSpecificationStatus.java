package com.asiainfo.baas.common;

public enum ProductSpecificationStatus {
	ACTIVE("active"), INACTIVE("inactive"), PLANNED("planned");
	private String stringValue;
	private ProductSpecificationStatus(String stringValue){
		this.stringValue=stringValue;
	}
	public String getValue(){
		return stringValue;
	}
}