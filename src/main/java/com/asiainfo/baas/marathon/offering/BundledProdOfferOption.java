package com.asiainfo.baas.marathon.offering;

/**
 * A set of numbers that specifies the lower and upper limits for a ProductOffering that can be procured as part of the related BundledProductOffering.
 * 
 * Values can range from 0 to unbounded.
 */
public class BundledProdOfferOption {

    ProductOffering productOffering;
    
    BundledProductOffering bundledProductOffering;
    /**
     * The lower limit of related ProductOfferings that can be procured as part of the BundledProductOffering.
     * 
     * Values can range from 0 to unbounded.
     */
    private int numberRelOfferLowerLimit;
    /**
     * The upper limit of related ProductOfferings that can be procured as part of the BundledProductOffering.
     * 
     * Values can range from 0 to unbounded.
     */
    private int numberRelOfferUpperLimit;

    public ProductOffering getProductOffering() {
        return this.productOffering;
    }

    public void setProductOffering(ProductOffering productOffering) {
        this.productOffering = productOffering;
    }

    public BundledProductOffering getBundledProductOffering() {
        return this.bundledProductOffering;
    }

    public void setBundledProductOffering(BundledProductOffering bundledProductOffering) {
        this.bundledProductOffering = bundledProductOffering;
    }

    public int getNumberRelOfferLowerLimit() {
        return this.numberRelOfferLowerLimit;
    }

    public void setNumberRelOfferLowerLimit(int numberRelOfferLowerLimit) {
        this.numberRelOfferLowerLimit = numberRelOfferLowerLimit;
    }

    public int getNumberRelOfferUpperLimit() {
        return this.numberRelOfferUpperLimit;
    }

    public void setNumberRelOfferUpperLimit(int numberRelOfferUpperLimit) {
        this.numberRelOfferUpperLimit = numberRelOfferUpperLimit;
    }

    /**
     * 
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public BundledProdOfferOption(ProductOffering offering, int lowerLimit, int upperLimit) {
        // TODO - implement BundledProdOfferOption.BundledProdOfferOption
        throw new UnsupportedOperationException();
    }

}