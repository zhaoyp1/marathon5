package com.asiainfo.baas.marathon.specification;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

import com.asiainfo.baas.marathon.baseType.*;

/**
 * A use of the ProdSpecCharacteristicValue by an ProductSpecification to which
 * additional properties (attributes) apply or override the properties of
 * similar properties contained in ProdSpecCharacteristicValue.
 */
public class ProdSpecCharValueUse {
	private static Logger logger = Logger.getLogger(ProductSpecCharUse.class);
    private ProductSpecCharUse prodSpecCharUse;
    private ProductSpecCharacteristicValue prodSpecCharValue;
    /**
     * Indicates if the value is the default value for a characteristic.
     */
    private boolean isDefault;
    /**
     * The period of time for which the use of the
     * CharacteristicSpecificationValue is applicable.
     */
    private TimePeriod validFor;

    public ProductSpecCharUse getProdSpecCharUse() {
        return this.prodSpecCharUse;
    }

    public void setProdSpecCharUse(ProductSpecCharUse prodSpecCharUse) {
        this.prodSpecCharUse = prodSpecCharUse;
    }

    public ProductSpecCharacteristicValue getProdSpecCharValue() {
        return this.prodSpecCharValue;
    }

    public void setProdSpecCharValue(ProductSpecCharacteristicValue prodSpecCharValue) {
        this.prodSpecCharValue = prodSpecCharValue;
    }

    public boolean isIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param prodSpecCharVal
     * @param defaultFlg
     * @param validFor
     */
    public ProdSpecCharValueUse(ProductSpecCharacteristicValue prodSpecCharVal, boolean isDefault, TimePeriod validFor) {
    	this.paramIsEmpty(prodSpecCharVal);
        this.prodSpecCharValue = prodSpecCharVal;
        this.isDefault = isDefault;
        this.validFor = validFor;
    }
    
    private void paramIsEmpty(Object obj){
    	if(null == obj ){
    		logger.error("The parameter is null");
    		throw new IllegalArgumentException();
    	}
    }
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((null == this.prodSpecCharValue) ? 0 : this.prodSpecCharValue
						.hashCode());
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
		ProdSpecCharValueUse other = (ProdSpecCharValueUse) obj;
		if (null == this.prodSpecCharValue) {
			if (null != other.prodSpecCharValue)
				return false;
		} else if (!this.prodSpecCharValue.equals(other.prodSpecCharValue))
			return false;
		return true;
	}

}