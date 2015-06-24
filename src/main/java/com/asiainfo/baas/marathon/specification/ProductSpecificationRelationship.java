package com.asiainfo.baas.marathon.specification;

import com.asiainfo.baas.marathon.baseType.*;

/**
 * A migration, substitution, dependency, or exclusivity relationship between/among ProductSpecifications.
 */
public class ProductSpecificationRelationship {

    private ProductSpecification targetProdSpec;
    private ProductSpecification sourceSpec;
    /**
     * A categorization of the relationship, such as migration, substitution, dependency, exclusivity.
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

    public ProductSpecification getSourceSpec() {
        return this.sourceSpec;
    }

    public void setSourceSpec(ProductSpecification sourceSpec) {
        this.sourceSpec = sourceSpec;
    }

    /**
     * 
     * @param sourceSpec
     * @param targetSpec
     * @param type
     * @param validFor
     */
    public ProductSpecificationRelationship(ProductSpecification sourceSpec, ProductSpecification targetSpec, String type, TimePeriod validFor) {
        // TODO - implement ProductSpecificationRelationship.ProductSpecificationRelationship
        throw new UnsupportedOperationException();
    }

}