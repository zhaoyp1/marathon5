package com.asiainfo.baas.marathon.specification;

import java.util.*;

/**
 * A classification that groups ProductSpecifications that share common characteristics.
 */
public abstract class ProductSpecificationType {

    private List<ProductSpecification> prodSpec;
    /**
     * The name of the product specification type.
     */
    private String type;
    /**
     * A narrative that explains in detail what the product spec is.
     */
    private String description;

    public List<ProductSpecification> getProdSpec() {
        return this.prodSpec;
    }

    public void setProdSpec(List<ProductSpecification> prodSpec) {
        this.prodSpec = prodSpec;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @param type
     * @param description
     */
    public ProductSpecificationType(String type, String description) {
        // TODO - implement ProductSpecificationType.ProductSpecificationType
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpec
     */
    public void addProdSpec(ProductSpecification prodSpec) {
        // TODO - implement ProductSpecificationType.addProdSpec
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param prodSpec
     */
    public void removeProdSpec(ProductSpecification prodSpec) {
        // TODO - implement ProductSpecificationType.removeProdSpec
        throw new UnsupportedOperationException();
    }

}