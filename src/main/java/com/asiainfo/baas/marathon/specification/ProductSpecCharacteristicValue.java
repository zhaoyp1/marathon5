package com.asiainfo.baas.marathon.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asiainfo.baas.marathon.baseType.TimePeriod;

/**
 * A number or text that can be assigned to a ProductSpecCharacteristic.
 */
public class ProductSpecCharacteristicValue {

    private List<ProdSpecCharValueUse> prodSpecCharValueUse;
    private ProductSpecCharacteristic productSpecCharacteristic;
    private List<ProdSpecCharValueRelationship> prodSpecCharValueRelationship;
    /**
     * A kind of value that the characteristic can take on, such as numeric, text, and so forth.
     */
    private String valueType;
    /**
     * Indicates if the value is the default value for a characteristic.
     */
    private boolean isDefault;
    /**
     * A discrete value that the characteristic can take on.
     */
    private String value;
    /**
     * A length, surface, volume, dry measure, liquid measure, money, weight, time, and the like. Iin general, a determinate quantity or magnitude of the kind designated, taken as a standard of comparison for others of the same kind, in assigning to them numerical values, as 1 foot, 1 yard, 1 mile, 1 square foot.
     */
    private String unitOfMeasure;
    /**
     * The low range value that a characteristic can take on.
     */
    private String valueFrom;
    /**
     * The upper range value that a characteristic can take on.
     */
    private String valueTo;
    /**
     * An indicator that specifies the inclusion or exclusion of the valueFrom and valueTo attributes.
     * 
     * Possible values are "open", "closed", "closedBottom" and "closedTop".
     */
    private String rangeInterval;
    /**
     * The period of time for which a value is applicable.
     */
    private TimePeriod validFor;

    public List<ProdSpecCharValueUse> getProdSpecCharValueUse() {
        return this.prodSpecCharValueUse;
    }

    public void setProdSpecCharValueUse(List<ProdSpecCharValueUse> prodSpecCharValueUse) {
        this.prodSpecCharValueUse = prodSpecCharValueUse;
    }

    public List<ProdSpecCharValueRelationship> getProdSpecCharValueRelationship() {
        return this.prodSpecCharValueRelationship;
    }

    public void setProdSpecCharValueRelationship(List<ProdSpecCharValueRelationship> prodSpecCharValueRelationship) {
        this.prodSpecCharValueRelationship = prodSpecCharValueRelationship;
    }

    public String getValueType() {
        return this.valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    public boolean isIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnitOfMeasure() {
        return this.unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getValueFrom() {
        return this.valueFrom;
    }

    public void setValueFrom(String valueFrom) {
        this.valueFrom = valueFrom;
    }

    public String getValueTo() {
        return this.valueTo;
    }

    public void setValueTo(String valueTo) {
        this.valueTo = valueTo;
    }

    public String getRangeInterval() {
        return this.rangeInterval;
    }

    public void setRangeInterval(String rangeInterval) {
        this.rangeInterval = rangeInterval;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param valueType
     * @param isDefault
     * @param unitOfMeasure
     * @param validFor
     * @param value
     */
    public ProductSpecCharacteristicValue(String valueType, boolean isDefault, String unitOfMeasure, TimePeriod validFor, String value) {
    	this.valueType=valueType;
    	this.isDefault=isDefault;
    	this.unitOfMeasure=unitOfMeasure;
    	this.validFor=validFor;
    	this.value=value;
    }

    /**
     * 
     * @param valueType
     * @param isDefault
     * @param unitOfMeasure
     * @param validFor
     * @param valueFrom
     * @param valueTo
     * @param rangeInterval
     */
    public ProductSpecCharacteristicValue(String valueType, boolean isDefault, String unitOfMeasure, TimePeriod validFor, String valueFrom, String valueTo, String rangeInterval) {
    	this.valueType=valueType;
    	this.isDefault=isDefault;
    	this.unitOfMeasure=unitOfMeasure;
    	this.validFor=validFor;
    	this.valueFrom=valueFrom;
    	this.valueTo=valueTo;
    	this.rangeInterval=rangeInterval;
    }

    /**
     * 
     * @param unitOfMeasure
     * @param value
     */
    public void setValue(String unitOfMeasure, String value) {
    	this.unitOfMeasure=unitOfMeasure;
    	this.value=value;
    }

    /**
     * 
     * @param unitOfMeasure
     * @param valueFrom
     * @param valueTo
     * @param rangeInterval
     */
    public void setValue(String unitOfMeasure, String valueFrom, String valueTo, String rangeInterval) {
    	this.unitOfMeasure=unitOfMeasure;
    	this.valueFrom=valueFrom;
    	this.valueTo=valueTo;
    	this.rangeInterval=rangeInterval;
    }

    /**
     * 
     * @param charValue
     * @param relationType
     * @param validFor
     */
    public void addRelatedCharValue(ProductSpecCharacteristicValue charValue, String relationType, TimePeriod validFor) {
    	if(this.prodSpecCharValueRelationship==null) prodSpecCharValueRelationship=new ArrayList<ProdSpecCharValueRelationship>();
    	ProdSpecCharValueRelationship relationShip=new ProdSpecCharValueRelationship(this,charValue,relationType,validFor); 
    	prodSpecCharValueRelationship.add(relationShip);
    }

    /**
     * 
     * @param charValue
     */
    public void removeRelatedCharValue(ProductSpecCharacteristicValue charValue) {
    	
    	if(prodSpecCharValueRelationship != null){
        	prodSpecCharValueRelationship.remove(charValue);
    	}
    	
    }

   

    /**
     * 
     * @param type
     * @param time
     */
    public ProductSpecCharacteristicValue[] queryRelatedCharValue(String type, Date time) {
    	
    	List<ProductSpecCharacteristicValue> prodSpecCharValues=null;
    	if(this.prodSpecCharValueRelationship != null && prodSpecCharValueRelationship.size() > 0){
    		prodSpecCharValues=new ArrayList<ProductSpecCharacteristicValue>();
    		for (ProdSpecCharValueRelationship relationship : prodSpecCharValueRelationship) {
    			if(relationship.getCharValueRelationshipType() != null && type.equals(relationship.getCharValueRelationshipType()) && relationship.getValidFor().isInPeriod(time )){
    				prodSpecCharValues.add(relationship.getTargetCharValue());
    			}
			}
    		return (ProductSpecCharacteristicValue[])prodSpecCharValues.toArray(new ProductSpecCharacteristicValue[prodSpecCharValues.size()]);
    	}else{
    		return null;
    	}
    	
    }

  
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((rangeInterval == null) ? 0 : rangeInterval.hashCode());
		result = prime * result
				+ ((unitOfMeasure == null) ? 0 : unitOfMeasure.hashCode());
		result = prime * result
				+ ((validFor == null) ? 0 : validFor.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		result = prime * result
				+ ((valueFrom == null) ? 0 : valueFrom.hashCode());
		result = prime * result + ((valueTo == null) ? 0 : valueTo.hashCode());
		result = prime * result
				+ ((valueType == null) ? 0 : valueType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSpecCharacteristicValue other = (ProductSpecCharacteristicValue) obj;
		if (rangeInterval == null) {
			if (other.rangeInterval != null)
				return false;
		} else if (!rangeInterval.equals(other.rangeInterval))
			return false;
		if (unitOfMeasure == null) {
			if (other.unitOfMeasure != null)
				return false;
		} else if (!unitOfMeasure.equals(other.unitOfMeasure))
			return false;
		if (validFor == null) {
			if (other.validFor != null)
				return false;
		} else if (!validFor.equals(other.validFor))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (valueFrom == null) {
			if (other.valueFrom != null)
				return false;
		} else if (!valueFrom.equals(other.valueFrom))
			return false;
		if (valueTo == null) {
			if (other.valueTo != null)
				return false;
		} else if (!valueTo.equals(other.valueTo))
			return false;
		if (valueType == null) {
			if (other.valueType != null)
				return false;
		} else if (!valueType.equals(other.valueType))
			return false;
		return true;
	}

	public ProductSpecCharacteristic getProductSpecCharacteristic() {
		return productSpecCharacteristic;
	}

	public void setProductSpecCharacteristic(ProductSpecCharacteristic productSpecCharacteristic) {
		this.productSpecCharacteristic = productSpecCharacteristic;
	}
}