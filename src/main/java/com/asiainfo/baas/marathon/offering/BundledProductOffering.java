package com.asiainfo.baas.marathon.offering;

import java.util.*;
import com.asiainfo.baas.marathon.baseType.*;

public class BundledProductOffering extends ProductOffering {

    private List<BundledProdOfferOption> bundledProdOfferOption;

    public List<BundledProdOfferOption> getBundledProdOfferOption() {
        return this.bundledProdOfferOption;
    }

    public void setBundledProdOfferOption(List<BundledProdOfferOption> bundledProdOfferOption) {
        this.bundledProdOfferOption = bundledProdOfferOption;
    }

    /**
     * 
     * @param id
     * @param name
     * @param validFor
     * @param status
     * @param description
     */
    public BundledProductOffering(String id, String name, TimePeriod validFor, String status, String description) {
    	super(id, name, status, description, validFor);
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     */
    public void addSubOffering(ProductOffering offering) {
        // TODO - implement BundledProductOffering.addSubOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public void addSubOffering(ProductOffering offering, int lowerLimit, int upperLimit) {
        // TODO - implement BundledProductOffering.addSubOffering
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public void updateSubOfferingOption(ProductOffering offering, int lowerLimit, int upperLimit) {
        // TODO - implement BundledProductOffering.updateSubOfferingOption
        throw new UnsupportedOperationException();
    }

    public ProductOffering[] getSubOffering() {
        // TODO - implement BundledProductOffering.getSubOffering
        throw new UnsupportedOperationException();
    }

}