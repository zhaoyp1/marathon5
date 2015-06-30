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
        if (this.prodSpec == null) {
            this.prodSpec = new ArrayList<ProductSpecification>();
        }
        if (prodSpec == null) {
            logger.error("方法addSubProdSpec的参数不正确。prodSpec=" + prodSpec);
            return;
        }
        if (this.prodSpec.contains(prodSpec)) {
            logger.error("已存在此子规格，不能再次关联。ProductNumber=" + prodSpec.getProductNumber());
            return;
        }
        this.prodSpec.add(prodSpec);
    }

}