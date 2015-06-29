package com.asiainfo.baas.common;

public enum CharacristicValueType {
	NUMBER("number"), TEXT("text");
	
	private String stringValue;
	
	private CharacristicValueType(String stringValue){
		
		this.stringValue=stringValue;
	
	}
	
	public String getValue(){
		
		return stringValue;
	
	}
}