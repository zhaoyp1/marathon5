package com.asiainfo.baas.marathon.specification;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon5.common.CommonUtils;

/**
 * A aggregation, migration, substitution, dependency, or exclusivity
 * relationship between/among ProductSpecCharacteristics.
 */
public class ProductSpecCharRelationship {

    private ProductSpecCharacteristic targetProdSpecChar;
    private ProductSpecCharacteristic srcProdSpecChar;
    private Logger logger=Logger.getLogger(ProductSpecCharRelationship.class);
    /**
     * A categorization of the relationship, such as aggregation, migration,
     * substitution, dependency, exclusivity.
     */
    private String charRelationshipType;
    /**
     * The order in which a CharacteristicSpecification appears within another
     * CharacteristicSpecification that defines a grouping of
     * CharacteristicSpecifications.
     * 
     * For example, a grouping may represent the name of an individual. The
     * given name is first, the middle name is second, and the last name is
     * third.
     */
    private int charSpecSeq;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    public ProductSpecCharacteristic getTargetProdSpecChar() {
        return this.targetProdSpecChar;
    }

    public void setTargetProdSpecChar(ProductSpecCharacteristic targetProdSpecChar) {
        this.targetProdSpecChar = targetProdSpecChar;
    }

    public ProductSpecCharacteristic getSrcProdSpecChar() {
        return this.srcProdSpecChar;
    }

    public void setSrcProdSpecChar(ProductSpecCharacteristic sourceProdSpecChar) {
        this.srcProdSpecChar = sourceProdSpecChar;
    }

    public String getCharRelationshipType() {
        return this.charRelationshipType;
    }

    public void setCharRelationshipType(String charRelationshipType) {
        this.charRelationshipType = charRelationshipType;
    }

    public int getCharSpecSeq() {
        return this.charSpecSeq;
    }

    public void setCharSpecSeq(int charSpecSeq) {
        this.charSpecSeq = charSpecSeq;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param srcProdSpecChar
     * @param targetProdSpecChar
     * @param relationType
     * @param validFor
     */
    public ProductSpecCharRelationship(ProductSpecCharacteristic srcProdSpecChar,
            ProductSpecCharacteristic targetProdSpecChar, String relationType, TimePeriod validFor) {
    	if (null == srcProdSpecChar) {
    		logger.error("srcProdSpecChar should not be null");
    		throw new IllegalArgumentException("srcProdSpecChar should not be null"); 
    	}
    	if (null == targetProdSpecChar) {
    		logger.error("targetProdSpecChar should not be null");
    		throw new IllegalArgumentException("targetProdSpecChar should not be null"); 
    	}
        this.srcProdSpecChar = srcProdSpecChar;
        this.targetProdSpecChar = targetProdSpecChar;
        this.charRelationshipType = relationType;
        this.validFor = validFor;
    }

    /**
     * 
     * @param srcProdSpecChar
     * @param targetProdSpecChar
     * @param relationType
     * @param validFor
     * @param specSeq
     */
    public ProductSpecCharRelationship(ProductSpecCharacteristic srcProdSpecChar,
            ProductSpecCharacteristic targetProdSpecChar, String relationType, TimePeriod validFor, int specSeq) {
        
    	if (null == srcProdSpecChar) {
    		logger.error("srcProdSpecChar should not be null");
    		throw new IllegalArgumentException("srcProdSpecChar should not be null"); 
    	}
    	if (null == targetProdSpecChar) {
    		logger.error("targetProdSpecChar should not be null");
    		throw new IllegalArgumentException("targetProdSpecChar should not be null"); 
    	}
    	this.srcProdSpecChar = srcProdSpecChar;
        this.targetProdSpecChar = targetProdSpecChar;
        this.charRelationshipType = relationType;
        this.validFor = validFor;
        this.charSpecSeq = specSeq;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((charRelationshipType == null) ? 0 : charRelationshipType
						.hashCode());
		result = prime * result + charSpecSeq;
		result = prime
				* result
				+ ((targetProdSpecChar == null) ? 0 : targetProdSpecChar
						.hashCode());
		result = prime * result
				+ ((validFor == null) ? 0 : validFor.hashCode());
		return result;
	}
	
	@Override
	public String toString(){
		
		Map<String,Object> targetChar=new HashMap<String,Object>();
		targetChar.put("targetChar",targetProdSpecChar.basicInfoToString() );
		targetChar.put("type",charRelationshipType);
		targetChar.put("validFor",validFor);
		targetChar.put("charSpecSeq", charSpecSeq);
		return targetChar.toString();
	}
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductSpecCharRelationship other = (ProductSpecCharRelationship) obj;
        if (charRelationshipType == null) {
            if (other.charRelationshipType != null)
                return false;
        } else if (!charRelationshipType.equals(other.charRelationshipType))
            return false;
        if (targetProdSpecChar == null) {
            if (other.targetProdSpecChar != null)
                return false;
        } else if (!targetProdSpecChar.equals(other.targetProdSpecChar))
            return false;
        if (validFor == null) {
            if (other.validFor != null)
                return false;
        } else if (!validFor.equals(other.validFor))
            return false;
        return true;
    }

}
