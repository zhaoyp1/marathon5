package com.asiainfo.baas.marathon.offering;

import com.asiainfo.baas.marathon.specification.*;
import com.asiainfo.baas.marathon.baseType.*;

/**
 * A type of ProductOffering that does not have any subordinate ProductOfferings, that is, an SimpleProductOffering is a leaf-level ProductOffering.
 */
public class SimpleProductOffering extends ProductOffering {

    private ProductSpecification productSpecification;

    public ProductSpecification getProductSpecification() {
        return this.productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    /**
     * 
     * @param id
     * @param name
     * @param validFor
     * @param status
     * @param prodSpec
     * @param description
     */
    public SimpleProductOffering(String id, String name, TimePeriod validFor, String status, ProductSpecification prodSpec, String description) {
    	super(id, name, status, description, validFor);
    	throw new UnsupportedOperationException();
    }

}