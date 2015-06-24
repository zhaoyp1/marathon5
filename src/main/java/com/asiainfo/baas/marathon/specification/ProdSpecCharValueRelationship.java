package com.asiainfo.baas.marathon.specification;

import com.asiainfo.baas.marathon.baseType.*;

public class ProdSpecCharValueRelationship {

    private ProductSpecCharacteristicValue targetCharValue;
    private ProductSpecCharacteristicValue sourceCharValue;
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

    public ProductSpecCharacteristicValue getSourceCharValue() {
        return this.sourceCharValue;
    }

    public void setSourceCharValue(ProductSpecCharacteristicValue sourceCharValue) {
        this.sourceCharValue = sourceCharValue;
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
        // TODO - implement ProdSpecCharValueRelationship.ProdSpecCharValueRelationship
        throw new UnsupportedOperationException();
    }

}