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
    }

    /**
     * 
     * @param offering
     */
    public void addSubOffering(ProductOffering offering) {
    	BundledProdOfferOption option = new BundledProdOfferOption(offering,0,-1);
    	if(bundledProdOfferOption == null){
    		bundledProdOfferOption = new ArrayList<BundledProdOfferOption>();
    	}
    	bundledProdOfferOption.add(option);
    }

    /**
     * 
     * @param offering
     * @param lowerLimit
     * @param upperLimit
     */
    public void addSubOffering(ProductOffering offering, int lowerLimit, int upperLimit) {
    	BundledProdOfferOption option = new BundledProdOfferOption(offering,lowerLimit,upperLimit);
    	if(bundledProdOfferOption == null){
    		bundledProdOfferOption = new ArrayList<BundledProdOfferOption>();
    	}
    	bundledProdOfferOption.add(option);
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
    	ProductOffering[] offering = null;
    	if(bundledProdOfferOption != null){
    		offering = new ProductOffering[bundledProdOfferOption.size()];
    		for(int i = 0 ; i < bundledProdOfferOption.size() ; i++){
    			BundledProdOfferOption offerOption = bundledProdOfferOption.get(i);
    			offering[i] = offerOption.getProductOffering();
    		}
    	}
    	return offering;
    }

}