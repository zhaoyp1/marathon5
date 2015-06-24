package com.asiainfo.baas.marathon.offering;

import java.util.*;
import java.util.Date;

import com.asiainfo.baas.marathon.offering.offeringPrice.*;
import com.asiainfo.baas.marathon.offering.catalog.*;
import com.asiainfo.baas.marathon.baseType.*;
import com.asiainfo.baas.marathon.dateType.*;

/**
 * The presentation of one or more ProductSpecifications to the marketplace for sale, rental, or lease for a ProductOfferingPrice. A ProductOffering may target one or more MarketSegments, be included in one or more ProductCatalog, presented in support of one or more ProductStrategies, and made available in one or more Places. ProductOffering may represent a simple offering of a single ProductSpecification or could represent a bundling of one or more other ProductOffering.
 */
public abstract class ProductOffering {

    private List<ProductOfferingPrice> productOfferingPrice;
    private List<ProdCatalogProdOffer> prodCatalogProdOffer;
    private List<ProductOfferingRelationship> prodOfferingRelationship;
    /**
     * A unique identifier for the ProductOffering.
     */
    private String id;
    /**
     * A word, term, or phrase by which the ProductOffeirng is known and distinguished from other ProductOfferings.
     */
    private String name;
    /**
     * A narrative that explains what the offering is.
     */
    private String description;
    /**
     * The period during which the offering is applicable.
     */
    private TimePeriod validFor;
    /**
     * The condition in which the offering exists, such as planned, obsolete, active
     */
    private String status;

    public List<ProductOfferingPrice> getProductOfferingPrice() {
        return this.productOfferingPrice;
    }

    public void setProductOfferingPrice(List<ProductOfferingPrice> productOfferingPrice) {
        this.productOfferingPrice = productOfferingPrice;
    }

    public List<ProdCatalogProdOffer> getProdCatalogProdOffer() {
        return this.prodCatalogProdOffer;
    }

    public void setProdCatalogProdOffer(List<ProdCatalogProdOffer> prodCatalogProdOffer) {
        this.prodCatalogProdOffer = prodCatalogProdOffer;
    }

    public List<ProductOfferingRelationship> getProdOfferingRelationship() {
        return this.prodOfferingRelationship;
    }

    public void setProdOfferingRelationship(List<ProductOfferingRelationship> prodOfferingRelationship) {
        this.prodOfferingRelationship = prodOfferingRelationship;
    }

    public String getId() {
        return this.id;
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

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @param id
     * @param name
     * @param status
     * @param description
     * @param validFor
     */
    public ProductOffering(String id, String name, String status, String description, TimePeriod validFor) {
        // TODO - implement ProductOffering.ProductOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param relationType
     * @param validFor
     */
    public void addRelatedOffering(ProductOffering offering, String relationType, TimePeriod validFor) {
        // TODO - implement ProductOffering.addRelatedOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     */
    public void removeRelatedOffering(ProductOffering offering) {
        // TODO - implement ProductOffering.removeRelatedOffering
        throw new UnsupportedOperationException();
    }

    

    /**
     * 
     * @param price
     */
    public void addPrice(ProductOfferingPrice price) {
        // TODO - implement ProductOffering.addPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param price
     */
    public void setPrice(ProductOfferingPrice[] price) {
        // TODO - implement ProductOffering.setPrice
        throw new UnsupportedOperationException();
    }

  

    /**
     * 
     * @param relationType
     * @param time
     */
    public ProductOffering[] queryRelatedOffering(String relationType, Date time) {
        // TODO - implement ProductOffering.queryRelatedOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public ProductOfferingPrice[] queryPrice(Date time) {
        // TODO - implement ProductOffering.queryPrice
        throw new UnsupportedOperationException();
    }

}