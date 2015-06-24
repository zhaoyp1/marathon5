package com.asiainfo.baas.marathon.specification;

import com.asiainfo.baas.marathon.baseType.*;

/**
 * A aggregation, migration, substitution, dependency, or exclusivity relationship between/among ProductSpecCharacteristics.
 */
public class ProductSpecCharRelationship {

    private ProductSpecCharacteristic targetProdSpecChar;
    private ProductSpecCharacteristic sourceProdSpecChar;
    /**
     * A categorization of the relationship, such as aggregation, migration, substitution, dependency, exclusivity.
     */
    private String charRelationshipType;
    /**
     * The order in which a CharacteristicSpecification appears within another CharacteristicSpecification that defines a grouping of CharacteristicSpecifications.
     * 
     * For example, a grouping may represent the name of an individual. The given name is first, the middle name is second, and the last name is third.
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

    public ProductSpecCharacteristic getSourceProdSpecChar() {
        return this.sourceProdSpecChar;
    }

    public void setSourceProdSpecChar(ProductSpecCharacteristic sourceProdSpecChar) {
        this.sourceProdSpecChar = sourceProdSpecChar;
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
    public ProductSpecCharRelationship(ProductSpecCharacteristic srcProdSpecChar, ProductSpecCharacteristic targetProdSpecChar, String relationType, TimePeriod validFor) {
        // TODO - implement ProductSpecCharRelationship.ProductSpecCharRelationship
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param srcProdSpecChar
     * @param targetProdSpecChar
     * @param relationType
     * @param validFor
     * @param specSeq
     */
    public ProductSpecCharRelationship(ProductSpecCharacteristic srcProdSpecChar, ProductSpecCharacteristic targetProdSpecChar, String relationType, TimePeriod validFor, int specSeq) {
        // TODO - implement ProductSpecCharRelationship.ProductSpecCharRelationship
        throw new UnsupportedOperationException();
    }

}