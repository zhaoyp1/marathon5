package com.asiainfo.baas.marathon.offering.catalog;

import java.util.*;
import java.util.Date;

import com.asiainfo.baas.marathon.baseType.*;
import com.asiainfo.baas.marathon.offering.*;
import com.asiainfo.baas.marathon.offering.offeringPrice.*;
import com.asiainfo.baas.marathon.dateType.*;

/**
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 *  
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 * ? 
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 *  
 * A list of ProductOfferings for sale, with prices and illustrations, for example in book form or on the web. ProductCatalogs can be used by Customers during a self-care ordering process and may be used across one or more DistributionChannels.
 * ? ?
 */
public class ProductCatalog {

    private List<ProdCatalogProdOffer> prodCatalogProdOffers;
    private String ID;
    private String type;
    private TimePeriod validFor;
    private String name;

    public List<ProdCatalogProdOffer> getProdCatalogProdOffers() {
        return this.prodCatalogProdOffers;
    }

    public void setProdCatalogProdOffers(List<ProdCatalogProdOffer> prodCatalogProdOffers) {
        this.prodCatalogProdOffers = prodCatalogProdOffers;
    }

    public String getID() {
        return this.ID;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @param id
     * @param name
     * @param type
     * @param validFor
     */
    public ProductCatalog(String id, String name, String type, TimePeriod validFor) {
        // TODO - implement ProductCatalog.ProductCatalog
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    public void publishOffering(ProductOffering offering, TimePeriod validFor) {
        // TODO - implement ProductCatalog.publishOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param validFor
     * @param price
     */
    public void publishOffering(ProductOffering offering, TimePeriod validFor, ProductOfferingPrice[] price) {
        // TODO - implement ProductCatalog.publishOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     */
    public void cancelPublishedOffering(ProductOffering offering) {
        // TODO - implement ProductCatalog.cancelPublishedOffering
        throw new UnsupportedOperationException();
    }

   

    /**
     * 
     * @param offering
     * @param price
     */
    public void setPrice(ProductOffering offering, ProductOfferingPrice[] price) {
        // TODO - implement ProductCatalog.setPrice
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param time
     */
    public ProductOfferingPrice[] queryPrice(ProductOffering offering, Date time) {
        // TODO - implement ProductCatalog.queryPrice
        throw new UnsupportedOperationException();
    }

}