package com.asiainfo.baas.marathon.specification;

import java.util.HashMap;
import java.util.Map;

import com.asiainfo.baas.marathon.baseType.TimePeriod;

public class ProdSpecCharValueRelationship {

    private ProductSpecCharacteristicValue targetCharValue;
    private ProductSpecCharacteristicValue srcCharValue;
    /**
     * A categorization of the relationship between values, such as aggregation, migration, substitution, dependency, exclusivity.
     */
    private String charValueRelationshipType;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    public ProductSpecCharacteristicValue getTargetCharValue() {
        return this.targetCharValue;
    }

    public void setTargetCharValue(ProductSpecCharacteristicValue targetCharValue) {
        this.targetCharValue = targetCharValue;
    }

    public ProductSpecCharacteristicValue getSrcCharValue() {
        return this.srcCharValue;
    }

    public void setSrcCharValue(ProductSpecCharacteristicValue sourceCharValue) {
        this.srcCharValue = sourceCharValue;
    }

    public String getCharValueRelationshipType() {
        return this.charValueRelationshipType;
    }

    public void setCharValueRelationshipType(String charValueRelationshipType) {
        this.charValueRelationshipType = charValueRelationshipType;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param srcProdSpecCharValue
     * @param targetProdSpecCharValue
     * @param relationType
     * @param validFor
     */
    public ProdSpecCharValueRelationship(ProductSpecCharacteristicValue srcProdSpecCharValue, ProductSpecCharacteristicValue targetProdSpecCharValue, String relationType, TimePeriod validFor) {
    	this.srcCharValue=srcProdSpecCharValue;
    	this.targetCharValue=targetProdSpecCharValue;
    	this.charValueRelationshipType=relationType;
    	this.validFor=validFor;
    }
    
    public String toString(){
    	Map<String,Object> charValue=new HashMap<String,Object>();
    	charValue.put("charValue", this.targetCharValue.basicInfoToString());
    	charValue.put("charValueRelationshipType", charValueRelationshipType);
    	charValue.put("validFor", validFor);
    	return charValue.toString();
    }

}