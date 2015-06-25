package com.asiainfo.baas.marathon.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A classification that groups ProductSpecifications that share common
 * characteristics.
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
        this.type = type;
        this.description = description;
    }

    /**
     * 
     * @param prodSpec
     */
    public void addProdSpec(ProductSpecification prodSpec) {
        if (this.prodSpec == null) {
            this.prodSpec = new ArrayList<ProductSpecification>();
        }
        this.prodSpec.add(prodSpec);
    }

    /**
     * 
     * @param prodSpec
     */
    public void removeProdSpec(ProductSpecification prodSpec) {
        if (this.prodSpec != null) {
            this.prodSpec.remove(prodSpec);
        }
    }

    /**
     * 
     * @param time
     */
    public ProductSpecification[] queryProdSpec(Date time) {
        List<ProductSpecification> productSpecifications = new ArrayList<ProductSpecification>();
        if (this.prodSpec != null) {
            for (int i = 0; i < this.prodSpec.size(); i++) {
                if (this.prodSpec.get(i).getValidFor().isInPeriod(time)) {
                    productSpecifications.add(this.prodSpec.get(i));
                }
            }
        }
        return productSpecifications.toArray(new ProductSpecification[0]);
    }

}