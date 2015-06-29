package com.asiainfo.baas.common;

public enum CatalogType {
	REGULAR("regular"), PROMOTION("promotion");
	private String stringValue;
	private  CatalogType(String stringValue){
		this.stringValue=stringValue;
	}
	public String getValue(){
		return stringValue;
	}
}