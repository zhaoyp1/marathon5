package com.asiainfo.baas.common;

public enum RelationshipType {
	DEPENDENCY("dependency"), EXCLUSIVITY("exclusivity"), SUBSTITUTION("substitution"), AGGREGATION("aggeration");
	private String stringValue;
	
	private  RelationshipType(String stringValue){
		this.stringValue=stringValue;
	}
	
	private  RelationshipType(){
		
	}
	 
	public String getValue() {
		return stringValue;
	}
	public void setIndex(String stringValue) {
		this.stringValue = stringValue;
	}
}