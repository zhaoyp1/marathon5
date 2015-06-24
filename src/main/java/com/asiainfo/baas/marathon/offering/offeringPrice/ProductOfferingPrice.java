package com.asiainfo.baas.marathon.offering.offeringPrice;

import java.util.*;
import com.asiainfo.baas.marathon.offering.*;
import com.asiainfo.baas.marathon.baseType.*;

/**
 * An amount, usually of money, that is asked for or allowed when a ProductOffering is bought, rented, or leased. The price is valid for a defined period of time and may not represent the actual price paid by a customer.
 */
public abstract class ProductOfferingPrice {

    private List<ProductOffering> productOffering;
    /**
     * A short descriptive name such as "affinity discount" .
     */
    private String name;
    /**
     * A narrative that explains in detail the semantics of this component.
     */
    private String description;
    /**
     * The period for which the price is valid.
     */
    private TimePeriod validFor;

    public List<ProductOffering> getProductOffering() {
        return this.productOffering;
    }

    public void setProductOffering(List<ProductOffering> productOffering) {
        this.productOffering = productOffering;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param name
     * @param validFor
     */
    public ProductOfferingPrice(String name, TimePeriod validFor) {
        // TODO - implement ProductOfferingPrice.ProductOfferingPrice
        throw new UnsupportedOperationException();
    }

}