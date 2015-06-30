package com.asiainfo.baas.marathon.specification;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.asiainfo.baas.marathon.baseType.*;

/**
 * A migration, substitution, dependency, or exclusivity relationship
 * between/among ProductSpecifications.
 */
public class ProductSpecificationRelationship {

    private ProductSpecification targetProdSpec;
    private ProductSpecification srcProdSpec;
    /**
     * A categorization of the relationship, such as migration, substitution,
     * dependency, exclusivity.
     */
    private String type;
    /**
     * The period for which the relationship is applicable.
     */
    private TimePeriod validFor;

    public ProductSpecification getTargetProdSpec() {
        return this.targetProdSpec;
    }

    public void setTargetProdSpec(ProductSpecification targetProdSpec) {
        this.targetProdSpec = targetProdSpec;
    }

    public ProductSpecification getSrcProdSpec() {
        return this.srcProdSpec;
    }

    public void setSrcProdSpec(ProductSpecification srcSpec) {
        this.srcProdSpec = srcSpec;
    }

    /**
     * 
     * @param srcSpec
     * @param targetSpec
     * @param type
     * @param validFor
     */
    public ProductSpecificationRelationship(ProductSpecification srcSpec, ProductSpecification targetSpec, String type,
            TimePeriod validFor) {
        this.srcProdSpec = srcSpec;
        this.targetProdSpec = targetSpec;
        this.type = type;
        this.validFor = validFor;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
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

}