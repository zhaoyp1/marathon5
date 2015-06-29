package com.asiainfo.baas.common;

public enum CharacristicType {
	
	NUMBER("number"), TEXT("text");
	
	private String stringValue;
	
	private CharacristicType(String stringValue){
		
		this.stringValue=stringValue;
	
	}
	
	public String getValue(){
		
		return stringValue;
	
	}
}