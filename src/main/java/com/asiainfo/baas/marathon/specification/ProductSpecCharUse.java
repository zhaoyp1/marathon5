package com.asiainfo.baas.marathon.specification;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

import com.asiainfo.baas.common.ReflectionToStringBuilderBaas;
import com.asiainfo.baas.marathon.baseType.TimePeriod;

public class ProductSpecCharUse {

	private static Logger logger = Logger.getLogger(ProductSpecCharUse.class);
	
    private ProductSpecification prodSpec;
    private ProductSpecCharacteristic prodSpecChar;
    private Set<ProdSpecCharValueUse> prodSpecCharValueUse;
    /**
     * A word, term, or phrase by which the CharacteristicSpecification is known
     * and distinguished from other CharacteristicSpecifications.
     */
    private String name;
    /**
     * A narrative that explains the CharacteristicSpecification.
     */
    private String description;
    /**
     * An indicator that specifies if a value is unique for the specification.
     * 
     * Possible values are: "unique while value is in effect" and
     * "unique whether value is in effect or not"
     */
    private String unique;
    /**
     * An indicator that specifies if the associated CharacteristicSpecification
     * is a composite.
     */
    private boolean isPackage;
    /**
     * An indicator that specifies that the CharacteristicSpecValues associated
     * with the CharacteristicSpec cannot be changed when instantiating a
     * ServiceCharacteristicValue. For example, a bandwidth of 64 MB cannot be
     * changed.
     */
    private boolean canBeOveridden;
    /**
     * The minimum number of instances a CharacteristicValue can take on. For
     * example, zero to five phone numbers in a group calling plan, where zero
     * is the value for the minCardinality.
     */
    private int minCardinality;
    /**
     * The maximum number of instances a CharacteristicValue can take on. For
     * example, zero to five phone numbers in a group calling plan, where five
     * is the value for the maxCardinality.
     */
    private int maxCardinality;
    /**
     * An indicator that specifies that the values for the characteristic can be
     * extended by adding new values when instantiating a characteristic for a
     * Service.
     */
    private boolean extensible;
    private TimePeriod validFor;

    public ProductSpecification getProdSpec() {
        return this.prodSpec;
    }

    public void setProdSpec(ProductSpecification prodSpec) {
        this.prodSpec = prodSpec;
    }

    public ProductSpecCharacteristic getProdSpecChar() {
        return this.prodSpecChar;
    }

    public void setProdSpecChar(ProductSpecCharacteristic prodSpecChar) {
        this.prodSpecChar = prodSpecChar;
    }

    public Set<ProdSpecCharValueUse> getProdSpecCharValueUse() {
		return prodSpecCharValueUse;
	}

	public void setProdSpecCharValueUse(
			Set<ProdSpecCharValueUse> prodSpecCharValueUse) {
		this.prodSpecCharValueUse = prodSpecCharValueUse;
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

    public String getUnique() {
        return this.unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public boolean isIsPackage() {
        return this.isPackage;
    }

    public void setIsPackage(boolean isPackage) {
        this.isPackage = isPackage;
    }

    public boolean isCanBeOveridden() {
        return this.canBeOveridden;
    }

    public void setCanBeOveridden(boolean canBeOveridden) {
        this.canBeOveridden = canBeOveridden;
    }

    public int getMinCardinality() {
        return this.minCardinality;
    }

    public void setMinCardinality(int minCardinality) {
        this.minCardinality = minCardinality;
    }

    public int getMaxCardinality() {
        return this.maxCardinality;
    }

    public void setMaxCardinality(int maxCardinality) {
        this.maxCardinality = maxCardinality;
    }

    public boolean isExtensible() {
        return this.extensible;
    }

    public void setExtensible(boolean extensible) {
        this.extensible = extensible;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param charSpec
     * @param canBeOveridden
     * @param isPackage
     * @param validFor
     */
    public ProductSpecCharUse(ProductSpecCharacteristic charSpec, boolean canBeOveridden, boolean isPackage,
            TimePeriod validFor,String name) {
    	this.isEmpty(charSpec);
    	this.isEmpty(name);
        this.prodSpecChar = charSpec;
        this.canBeOveridden = canBeOveridden;
        this.isPackage = isPackage;
        this.validFor = validFor;
        this.name = name;
    }

    /**
     * 
     * @param charSpec
     * @param canBeOveridden
     * @param isPackage
     * @param validFor
     * @param name
     * @param unique
     * @param minCardinality
     * @param maxCardinality
     * @param extensible
     * @param description
     */
    public ProductSpecCharUse(ProductSpecCharacteristic charSpec, boolean canBeOveridden, boolean isPackage,
            TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality,
            boolean extensible, String description) {
    	this.isEmpty(charSpec);
    	this.isEmpty(name);
        this.prodSpecChar = charSpec;
        this.canBeOveridden = canBeOveridden;
        this.isPackage = isPackage;
        this.validFor = validFor;
        this.name = name;
        this.unique = unique;
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
        this.extensible = extensible;
        this.description = description;
    }

    /**
     * 
     * @param charValue
     * @param isDefault
     * @param validFor
     */
    public boolean addValue(ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
    	this.isEmpty(charValue);
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue, isDefault, validFor);
        if (null == this.prodSpecCharValueUse) {
            this.prodSpecCharValueUse = new HashSet<ProdSpecCharValueUse>();
        }
        this.prodSpecCharValueUse.add(charValueUse);
        return true;
    }

    /**
     * 
     * @param charValue
     */
    public void removeValue(ProductSpecCharacteristicValue charValue) {
        // TODO - implement ProductSpecCharUse.removeValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param defaultValue
     */
    public boolean specifyDefaultCharacteristicValueUse(ProductSpecCharacteristicValue defaultValue) {
        if (null != this.prodSpecCharValueUse) {
        	this.isEmpty(defaultValue);
        	ProdSpecCharValueUse valueUse = this.retrieveProdSpecCharValueUse(defaultValue);
        	this.isEmpty(valueUse);
        	valueUse.setIsDefault(true);
        	return true;
        }else{
        	logger.error(this.prodSpecCharValueUse);
        	return false;
        }
    }
    
    public boolean clearDefaultValueUse(ProductSpecCharacteristicValue defaultValue){
    	this.isEmpty(defaultValue);
    	ProdSpecCharValueUse oldDefaultValueUse = this.retrieveProdSpecCharValueUse(defaultValue);
    	if(null != oldDefaultValueUse){
    			if(oldDefaultValueUse.isIsDefault()){
    				oldDefaultValueUse.setIsDefault(false);
    				return true;
    			}else{
    				logger.error(oldDefaultValueUse);
    	    		return false;
    			}
    	}else
    		this.isEmpty(oldDefaultValueUse);
    	return false;

    }

    public List<ProdSpecCharValueUse> retrieveDefaultCharacteristicValueUse() {
    	List<ProdSpecCharValueUse> defaultValueUse = new ArrayList<ProdSpecCharValueUse>();
        if (null != prodSpecCharValueUse) {
            for (ProdSpecCharValueUse valueUse : prodSpecCharValueUse) {
                if (valueUse.isIsDefault())
                	defaultValueUse.add(valueUse);
            }
        }
        return defaultValueUse;

    }

    /**
     * 
     * @param minCardinality
     * @param maxCardinality
     */
    public void setCardinality(int minCardinality, int maxCardinality) {
    	if(minCardinality <= maxCardinality){
	        this.minCardinality = minCardinality;
	        this.maxCardinality = maxCardinality;
    	}else
    		throw new UnsupportedOperationException();
    }

    private ProdSpecCharValueUse retrieveProdSpecCharValueUse(ProductSpecCharacteristicValue charValue){
    	for (ProdSpecCharValueUse valueUse : prodSpecCharValueUse) {
            if (valueUse.getProdSpecCharValue().equals(charValue)) 
                return valueUse;
        }
    	return null;
    }
    
    private void isEmpty(Object obj){
    	if(null == obj ){
    		logger.error(obj);
    		throw new UnsupportedOperationException();
    	}
    }
   

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((null == this.name) ? 0 : this.name.hashCode());
		result = prime * result
				+ ((null == this.prodSpecChar) ? 0 : this.prodSpecChar.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductSpecCharUse other = (ProductSpecCharUse) obj;
		if (null == this.name) {
			if (other.name != null)
				return false;
		} else if (!this.name.equals(other.name))
			return false;
		if (null == this.prodSpecChar) {
			if (other.prodSpecChar != null)
				return false;
		} else if (!this.prodSpecChar.equals(other.prodSpecChar))
			return false;
		return true;
	}

	/*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        ReflectionToStringBuilderBaas stringBuilder = new ReflectionToStringBuilderBaas(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
        return stringBuilder.toString();
    }

}
