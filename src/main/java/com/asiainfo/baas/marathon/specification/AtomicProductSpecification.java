package com.asiainfo.baas.marathon.specification;

import com.asiainfo.baas.marathon.baseType.*;

/**
 * A type of ProductSpecification that does not have any subordinate ProductSpecifications, that is, an AtomicProductSpecification is a leaf-level ProductSpecification.
 */
public class AtomicProductSpecification extends ProductSpecification {

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     * @param lifecycleStatus
     */
    public AtomicProductSpecification(String productNumber, String name, String brand, String lifecycleStatus) {
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
    public AtomicProductSpecification(String productNumber, String name, String brand, String lifecycleStatus, String description, TimePeriod validFor) {
        super(productNumber, name, brand, lifecycleStatus, description, validFor);
    	throw new UnsupportedOperationException();
    }

}