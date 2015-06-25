package com.asiainfo.baas.marathon.offering.catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.ProductOffering;
import com.asiainfo.baas.marathon.offering.offeringPrice.ProductOfferingPrice;

/**
 * A list of ProductOfferings for sale, with prices and illustrations, for
 * example in book form or on the web. ProductCatalogs can be used by Customers
 * during a self-care ordering process and may be used across one or more
 * DistributionChannels.
 * 
 * A list of ProductOfferings for sale, with prices and illustrations, for
 * example in book form or on the web. ProductCatalogs can be used by Customers
 * during a self-care ordering process and may be used across one or more
 * DistributionChannels. ? A list of ProductOfferings for sale, with prices and
 * illustrations, for example in book form or on the web. ProductCatalogs can be
 * used by Customers during a self-care ordering process and may be used across
 * one or more DistributionChannels.
 * 
 * A list of ProductOfferings for sale, with prices and illustrations, for
 * example in book form or on the web. ProductCatalogs can be used by Customers
 * during a self-care ordering process and may be used across one or more
 * DistributionChannels. ? ?
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
        this.ID = id;
        this.name = name;
        this.type = type;
        this.validFor = validFor;
    }

    /**
     * 
     * @param offering
     * @param validFor
     */
    public void publishOffering(ProductOffering offering, TimePeriod validFor) {
        if (this.prodCatalogProdOffers == null) {
            this.prodCatalogProdOffers = new ArrayList<ProdCatalogProdOffer>();
        }
        ProdCatalogProdOffer prodCatalogProdOffer = new ProdCatalogProdOffer(this, offering, validFor);
        this.prodCatalogProdOffers.add(prodCatalogProdOffer);
    }

    /**
     * 
     * @param offering
     * @param validFor
     * @param price
     */
    public void publishOffering(ProductOffering offering, TimePeriod validFor, ProductOfferingPrice[] price) {
        if (this.prodCatalogProdOffers == null) {
            this.prodCatalogProdOffers = new ArrayList<ProdCatalogProdOffer>();
        }

        ProdCatalogProdOffer prodCatalogProdOffer = null;
        if (price == null) {
            prodCatalogProdOffer = new ProdCatalogProdOffer(this, offering, validFor);
        } else {
            prodCatalogProdOffer = new ProdCatalogProdOffer(this, offering, validFor, price);
        }

        this.prodCatalogProdOffers.add(prodCatalogProdOffer);
    }

    /**
     * 
     * @param offering
     */
    public void retiredProductOffering(ProductOffering offering) {
        // TODO - implement ProductCatalog.cancelPublishedOffering
        throw new UnsupportedOperationException();
    }

    public ProductOffering[] getProductOffering(String status) {
        List<ProductOffering> productOfferings = new ArrayList<ProductOffering>();
        if (this.prodCatalogProdOffers != null) {
            for (int i = 0; i < this.prodCatalogProdOffers.size(); i++) {
                ProductOffering productOffering = this.prodCatalogProdOffers.get(i).getProdOffering();
                if (status.equals(productOffering.getStatus())) {
                    productOfferings.add(productOffering);
                }
            }
        }
        return productOfferings.toArray(new ProductOffering[0]);
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