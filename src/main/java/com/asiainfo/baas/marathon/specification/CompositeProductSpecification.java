package com.asiainfo.baas.marathon.specification;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon5.specification.TestProductCreateSpecification;

/**
 * A type of ProductSpecification that is formed by aggregating other
 * ProductSpecifications, which may be Composite or Atomic
 * ProductSpecifications.
 */
public class CompositeProductSpecification extends ProductSpecification {

    private static Logger logger = Logger.getLogger(CompositeProductSpecification.class);
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
    public CompositeProductSpecification(String productNumber, String name, String brand, String lifecycleStatus,
            String description, TimePeriod validFor) {
        super(productNumber, name, brand, lifecycleStatus, description, validFor);
    }

    /**
     * 
     * @param prodSpec
     */
    public void addSubProdSpec(ProductSpecification prodSpec) {
        if (null == this.prodSpec) {
            this.prodSpec = new ArrayList<ProductSpecification>();
        }
        if (null == prodSpec) {
            logger.error("Parameter [prodSpec] cannot be null.");
            return;
        }
        if (this.prodSpec.contains(prodSpec)) {
            logger.error("the subProdSpec already exist, Cannot repeatedly create subProdSpec. ProductNumber="
                    + prodSpec.getProductNumber());
            throw new IllegalArgumentException("the subProdSpec already exist, Cannot repeatedly create subProdSpec.");
        }
        if (this.equals(prodSpec)) {
            logger.error("Cannot add subProdSpec with it self!");
            throw new IllegalArgumentException("Cannot add subProdSpec with it self!");
        }
        this.prodSpec.add(prodSpec);
    }

}