package com.asiainfo.baas.marathon.specification;

import com.asiainfo.baas.marathon.baseType.*;

/**
 * A use of the ProdSpecCharacteristicValue by an ProductSpecification to which additional properties (attributes) apply or override the properties of similar properties contained in ProdSpecCharacteristicValue.
 */
public class ProdSpecCharValueUse {

    private ProductSpecCharUse prodSpecCharUse;
    private ProductSpecCharacteristicValue prodSpecCharValue;
    /**
     * Indicates if the value is the default value for a characteristic.
     */
    private boolean isDefault;
    /**
     * The period of time for which the use of the CharacteristicSpecificationValue is applicable.
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
    public ProdSpecCharValueUse(ProductSpecCharacteristicValue prodSpecCharVal, boolean defaultFlg, TimePeriod validFor) {
        // TODO - implement ProdSpecCharValueUse.ProdSpecCharValueUse
        throw new UnsupportedOperationException();
    }

}