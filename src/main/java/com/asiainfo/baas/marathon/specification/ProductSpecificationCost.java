package com.asiainfo.baas.marathon.specification;

import com.asiainfo.baas.marathon.baseType.*;

/**
 * A monetary amount assigned to a ProductSpecification that represents the cost to the business to plan, develop, market, and implement the ProductSpecification.
 */
public class ProductSpecificationCost {

    private ProductSpecification productSpecification;
    /**
     * The monetary amount that represents the cost.
     */
    private Money costToBusiness;
    /**
     * The period during which the cost is applicable.
     */
    private TimePeriod validFor;

    public ProductSpecification getProductSpecification() {
        return this.productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public Money getCostToBusiness() {
        return this.costToBusiness;
    }

    public void setCostToBusiness(Money costToBusiness) {
        this.costToBusiness = costToBusiness;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param cost
     * @param validFor
     */
    public ProductSpecificationCost(Money cost, TimePeriod validFor) {
        // TODO - implement ProductSpecificationCost.ProductSpecificationCost
        throw new UnsupportedOperationException();
    }

}