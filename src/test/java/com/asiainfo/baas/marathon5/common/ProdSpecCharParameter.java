package com.asiainfo.baas.marathon5.common;

import com.asiainfo.baas.marathon.baseType.TimePeriod;

public class ProdSpecCharParameter {
	
	private String name;
	private boolean isPackage;
	private boolean canBeOveridden;
	private TimePeriod validFor;
	private boolean isContainValue;
	private int[] valueIndex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isPackage() {
		return isPackage;
	}
	public void setPackage(boolean isPackage) {
		this.isPackage = isPackage;
	}
	public boolean isCanBeOveridden() {
		return canBeOveridden;
	}
	public void setCanBeOveridden(boolean canBeOveridden) {
		this.canBeOveridden = canBeOveridden;
	}
	public TimePeriod getValidFor() {
		return validFor;
	}
	public void setValidFor(TimePeriod validFor) {
		this.validFor = validFor;
	}
	public boolean isContainValue() {
		return isContainValue;
	}
	public void setContainValue(boolean isContainValue) {
		this.isContainValue = isContainValue;
	}
	public int[] getValueIndex() {
		return valueIndex;
	}
	public void setValueIndex(int[] valueIndex) {
		this.valueIndex = valueIndex;
	}
	
	
	

}
