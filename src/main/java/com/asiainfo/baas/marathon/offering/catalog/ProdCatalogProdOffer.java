package com.asiainfo.baas.marathon.offering.catalog;

import com.asiainfo.baas.marathon.offering.*;
import java.util.*;
import com.asiainfo.baas.marathon.offering.offeringPrice.*;
import com.asiainfo.baas.marathon.baseType.*;

/**
 * The appearance of a ProductOffering in a ProductCatalog.
 */
public class ProdCatalogProdOffer {

    private ProductOffering prodOffering;
    private List<ProductOfferingPrice> productOfferingPrice;
    /**
     * The period during which the ProductOffering appears in the ProductCatalog.
     */
    private TimePeriod validFor;
    private ProductCatalog productCatalog;

    public ProductOffering getProdOffering() {
        return this.prodOffering;
    }

    public void setProdOffering(ProductOffering prodOffering) {
        this.prodOffering = prodOffering;
    }

    public List<ProductOfferingPrice> getProductOfferingPrice() {
        return this.productOfferingPrice;
    }

    public void setProductOfferingPrice(List<ProductOfferingPrice> productOfferingPrice) {
        this.productOfferingPrice = productOfferingPrice;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public ProductCatalog getProductCatalog() {
        return this.productCatalog;
    }

    public void setProductCatalog(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    /**
     * 
     * @param catalog
     * @param offering
     * @param validFor
     */
    public ProdCatalogProdOffer(ProductCatalog catalog, ProductOffering offering, TimePeriod validFor) {
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param catalog
     * @param offering
     * @param validFor
     * @param price
     */
    public ProdCatalogProdOffer(ProductCatalog catalog, ProductOffering offering, TimePeriod validFor, ProductOfferingPrice[] price) {
        // TODO - implement ProdCatalogProdOffer.ProdCatalogProdOffer
        throw new UnsupportedOperationException();
    }

    

}