package com.asiainfo.baas.marathon.specification;

import java.util.*;
import com.asiainfo.baas.marathon.baseType.*;

/**
 * A type of ProductSpecification that is formed by aggregating other ProductSpecifications, which may be Composite or Atomic ProductSpecifications.
 */
public class CompositeProductSpecification extends ProductSpecification {

    private List<ProductSpecification> prodSpec;

    public List<ProductSpecification> getProdSpec() {
        return this.prodSpec;
    }

    public void setProdSpec(List<ProductSpecification> prodSpec) {
        this.prodSpec = prodSpec;
    }

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     * @param lifecycleStatus
     */
    public CompositeProductSpecification(String productNumber, String name, String brand, String lifecycleStatus) {
    	super(productNumber, name, brand, lifecycleStatus);
    	throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     * @param lifecycleStatus
     * @param description
     * @param validFor
     */
    public CompositeProductSpecification(String productNumber, String name, String brand, String lifecycleStatus, String description, TimePeriod validFor) {
    	super(productNumber, name, brand, lifecycleStatus, description, validFor);
    	throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpec
     */
    public void addSubProdSpec(ProductSpecification prodSpec) {
        // TODO - implement CompositeProductSpecification.addSubProdSpec
        throw new UnsupportedOperationException();
    }

}