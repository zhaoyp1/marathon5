package com.asiainfo.baas.marathon.specification;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

import com.asiainfo.baas.marathon.baseType.TimePeriod;

public class ProductSpecCharUse {

	private static Logger logger = Logger.getLogger(ProductSpecCharUse.class);
	
    private ProductSpecification prodSpec;
    private ProductSpecCharacteristic prodSpecChar;
    private List<ProdSpecCharValueUse> prodSpecCharValueUse;
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

    public List<ProdSpecCharValueUse> getProdSpecCharValueUse() {
        return this.prodSpecCharValueUse;
    }

    public void setProdSpecCharValueUse(List<ProdSpecCharValueUse> prodSpecCharValueUse) {
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
            TimePeriod validFor) {
        this.prodSpecChar = charSpec;
        this.canBeOveridden = canBeOveridden;
        this.isPackage = isPackage;
        this.validFor = validFor;
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
    	if(charValue == null){
    		logger.error("添加的值不能为空！");
    		return false;
    	}
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue, isDefault, validFor);
        if (this.prodSpecCharValueUse == null) {
            this.prodSpecCharValueUse = new ArrayList<ProdSpecCharValueUse>();
        }
        if(this.prodSpecCharValueUse.contains(charValueUse)){
        	logger.error("所添加的值已经存在，不能重复添加！");
    		return false;
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
        if (this.prodSpecCharValueUse != null) {
        	if(defaultValue == null){
        		logger.error("指定的特征值为空，不能设置默认值！");
        		return false;
        	}
        	ProdSpecCharValueUse valueUse = this.retrieveProdSpecCharValueUse(defaultValue);
        	if(valueUse == null ){
        		logger.error("该特征没有这个值，不能设置默认值！");
        		return false;
        	}
        	ProdSpecCharValueUse oldDefaultValueUse = this.retrieveDefaultCharacteristicValueUse();
        	if(oldDefaultValueUse != null)
        		oldDefaultValueUse.setIsDefault(false);
        	valueUse.setIsDefault(true);
        	return true;
        }else{
        	logger.error("该特征没有值，不能设置默认值！");
        	return false;
        }
    }
    
    private ProdSpecCharValueUse retrieveProdSpecCharValueUse(ProductSpecCharacteristicValue charValue){
    	for (ProdSpecCharValueUse valueUse : prodSpecCharValueUse) {
            if (valueUse.getProdSpecCharValue().equals(charValue)) 
                return valueUse;
        }
    	return null;
    }

    public ProdSpecCharValueUse retrieveDefaultCharacteristicValueUse() {
        if (prodSpecCharValueUse != null) {
            for (int i = 0; i < prodSpecCharValueUse.size(); i++) {
                ProdSpecCharValueUse valueUse = prodSpecCharValueUse.get(i);
                if (valueUse.isIsDefault())
                    return valueUse;
            }
        }
        return null;

    }

    /**
     * 
     * @param minCardinality
     * @param maxCardinality
     */
    public void setCardinality(int minCardinality, int maxCardinality) {
        this.minCardinality = minCardinality;
        this.maxCardinality = maxCardinality;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE);
        toStringBuilder.append("description", description);
        toStringBuilder.append("unique", unique);
        toStringBuilder.append("isPackage", isPackage);
        toStringBuilder.append("canBeOveridden", canBeOveridden);
        toStringBuilder.append("minCardinality", minCardinality);
        toStringBuilder.append("maxCardinality", maxCardinality);
        toStringBuilder.append("extensible", extensible);
        toStringBuilder.append("validFor", validFor);

        return toStringBuilder.toString();
    }

}
