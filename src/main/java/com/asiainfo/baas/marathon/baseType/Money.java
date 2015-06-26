package com.asiainfo.baas.marathon.baseType;

/**
 * A base / value business entity used to represent money
 */
public class Money {
	
	public Money(String units,long amount){
		this.units=units;
		this.amount=amount;
	}
	public Money(){
		
	}
	

    /**
     * Currency(Notes:refer to [ISO 4217]) or non-currency terms, such as loyalty points.
     */
    public  String units;
    /**
     * A positive floating point number.
     */
    public   long amount;
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}

}