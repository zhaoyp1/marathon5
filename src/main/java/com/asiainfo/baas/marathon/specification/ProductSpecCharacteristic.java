package com.asiainfo.baas.marathon.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;

import com.asiainfo.baas.common.ReflectionToStringBuilderBaas;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;

/**
 * A characteristic quality or distinctive feature of a ProductSpecification.
 * The characteristic can be take on a discrete value, such as color, can take
 * on a range of values, (for example, sensitivity of 100-240 mV), or can be
 * derived from a formula (for example, usage time (hrs) = 30 - talk time *3).
 * Certain characteristics, such as color, may be configured during the ordering
 * or some other process.
 */
public class ProductSpecCharacteristic {

    private Logger logger = Logger.getLogger(ProductSpecCharacteristic.class);

    private Set<ProductSpecCharacteristicValue> productSpecCharacteristicValue;
    private List<ProductSpecCharUse> prodSpecCharUse;
    private List<ProductSpecCharRelationship> prodSpecCharRelationship;
    /**
     * A unique identifier for the ProductSpecCharacteristic. ?
     */
    private String ID;
    /**
     * A word, term, or phrase by which the characteristic is known and
     * distinguished from characteristics.
     */
    private String name;
    /**
     * A narrative that explains the characteristic.
     */
    private String description;
    /**
     * An indicator that specifies if a value is unique for the specification.
     * 
     * Possible values are; "unique while value is in effect" and
     * "unique whether value is in effect or not"
     */
    private String unique;
    /**
     * A kind of value that the characteristic can take on, such as numeric,
     * text, and so forth.
     */
    private String valueType;
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
     * extended by adding new values when instantiating a characteristic for an
     * Entity.
     */
    private boolean extensible;
    /**
     * A rule or principle represented in symbols, numbers, or letters, often in
     * the form of an equation used to derive the value of a characteristic
     * value.
     */
    private String derivationFormula;
    /**
     * The period of time for which a characteristic is applicable.
     */
    private TimePeriod validFor;

    public List<ProductSpecCharUse> getProdSpecCharUse() {
        return this.prodSpecCharUse;
    }

    public void setProdSpecCharUse(List<ProductSpecCharUse> prodSpecCharUse) {
        this.prodSpecCharUse = prodSpecCharUse;
    }

    public List<ProductSpecCharRelationship> getProdSpecCharRelationship() {
        return this.prodSpecCharRelationship;
    }

    public void setProdSpecCharRelationship(List<ProductSpecCharRelationship> prodSpecCharRelationship) {
        this.prodSpecCharRelationship = prodSpecCharRelationship;
    }

    public String getID() {
        return this.ID;
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

    public String getValueType() {
        return this.valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
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

    public String getDerivationFormula() {
        return this.derivationFormula;
    }

    public void setDerivationFormula(String derivationFormula) {
        this.derivationFormula = derivationFormula;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param id
     * @param name
     * @param valueType
     * @param validFor
     * @param unique
     * @param minCardinality
     * @param maxCardinality
     */
    public ProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor, String unique,
            int minCardinality, int maxCardinality) {
    	
    	if ( StringUtils.isEmpty( id ) ) {
    		logger.error("id should not be null");
    		throw new IllegalArgumentException("id should not be null");
    	}
    	if ( StringUtils.isEmpty(valueType) ) {
    		logger.error("valueType should not be null");
    		throw new IllegalArgumentException("valueType should not be null");
    	}
    	if ( StringUtils.isEmpty(name)){
    		logger.error("name should not be null");
    		throw new IllegalArgumentException("name should not be null");
    	}
        this.ID = id;
        this.name = name;
        this.valueType = valueType;
        this.validFor = validFor;
        this.unique = unique;
        this.maxCardinality = maxCardinality;
        this.minCardinality = minCardinality;
    }

    /**
     * 
     * @param id
     * @param name
     * @param valueType
     * @param validFor
     * @param unique
     * @param minCardinality
     * @param maxCardinality
     * @param extensible
     * @param description
     * @param derivationFormula
     */
    public ProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor, String unique,
            int minCardinality, int maxCardinality, boolean extensible, String description, String derivationFormula) {
    	
    	if (StringUtils.isEmpty(id) ){
    		logger.error("id should not be null");
    		throw new IllegalArgumentException("id should not be null");
    	}
    	if ( StringUtils.isEmpty(name)){
    		logger.error("name should not be null");
    		throw new IllegalArgumentException("name should not be null");
    	}
    	if ( StringUtils.isEmpty(valueType) ) {
    		logger.error("valueType should not be null");
    		throw new IllegalArgumentException("valueType should not be null");
    	}
    	
    	this.ID = id;
        this.name = name;
        this.valueType = valueType;
        this.validFor = validFor;
        this.unique = unique;
        this.maxCardinality = maxCardinality;
        this.minCardinality = minCardinality;
        this.extensible = extensible;
        this.derivationFormula = derivationFormula;
        this.description = description;
    }

    /**
     * 
     * @param value
     */
    public boolean addValue(ProductSpecCharacteristicValue value) {

        if (null == value) {
        	logger.error("value should not be null");
            throw new IllegalArgumentException("value should not be null");
        }
        initContainValues();
        if (value.getValueType() != null && !this.getValueType().equals(value.getValueType())) {
        	logger.error("The value type of Character and CharacterValue value is different.");
        	throw new IllegalArgumentException("The value type of Character and CharacterValue value is different.");
        }
      
        productSpecCharacteristicValue.add(value);
        return true;

    }
    
    private void initContainValues(){
    	if (this.productSpecCharacteristicValue == null) {
            this.productSpecCharacteristicValue = new HashSet<ProductSpecCharacteristicValue>();
    	}
    }

    /**
     * 
     * @param value
     */
    public void removeValue(ProductSpecCharacteristicValue value) {
    	//TODO
    }

    /**
     * 
     * @param time
     */
    public List<ProductSpecCharacteristicValue> retrieveValue(Date time) {
    	
    	List<ProductSpecCharacteristicValue> productSpecCharValues = new ArrayList<ProductSpecCharacteristicValue>();
        if (null == time) {
        	logger.error("DateTime should not be null.");
        	throw new IllegalArgumentException("DateTime should not be null.");
        } 
        if ( null != this.productSpecCharacteristicValue ) {
             for (ProductSpecCharacteristicValue charValue : productSpecCharacteristicValue) {
                if (null != charValue.getValidFor() && charValue.getValidFor().isInPeriod(time)) {
                        productSpecCharValues.add(charValue);
                }
             }
         }

        return productSpecCharValues;
    }

    /**
     * 
     * @param charVal
     */

    public boolean specifyDefaultValue(ProductSpecCharacteristicValue charVal) {
    	
    	if (null == charVal ) {
    		logger.error("charVal should not be null.");
    		throw new IllegalArgumentException("charVal should not be null.");
    	}
    	if(null == this.productSpecCharacteristicValue || !productSpecCharacteristicValue.contains(charVal)){
    		 return false;
    	}
    	for (ProductSpecCharacteristicValue charValue : productSpecCharacteristicValue) {
    		if(charValue.equals(charVal)){
    			if(charVal.isIsDefault()){
    				logger.warn("The current value has been set to the default value"+charValue.toString());
    			}else{
    				charVal.setIsDefault(true);
    			}
    			break;
    		} 
    	}
    	return true;
    }


    public List<ProductSpecCharacteristicValue> retrieveDefaultValue() {
    	
    	List<ProductSpecCharacteristicValue> defaultSpecCharValue=new ArrayList<ProductSpecCharacteristicValue>();
    	
    	if ( null != this.productSpecCharacteristicValue) {
    		for (ProductSpecCharacteristicValue charValue : productSpecCharacteristicValue) {
    			if(charValue.isIsDefault()){
    				defaultSpecCharValue.add(charValue);
    			}
			}
    	}
    	return defaultSpecCharValue;
    }
    
    public boolean clearDefaultValue(ProductSpecCharacteristicValue value){
    	
    	if (null == value ) {
    		logger.error("charVal should not be null.");
    		throw new IllegalArgumentException("charVal should not be null.");
    	}
    	
    	if(null == this.productSpecCharacteristicValue || !productSpecCharacteristicValue.contains(value)){
    		 return false;
    	}
    	for (ProductSpecCharacteristicValue productSpecCharacteristicValue : productSpecCharacteristicValue) {
			if(productSpecCharacteristicValue.equals(value)){
				productSpecCharacteristicValue.setIsDefault(false);
				break;
			}
		}
		return true;
    	
 
    }
    /**
     * 
     * @param characteristic
     * @throws Exception 
     */
    public boolean addLeafCharacteristic(ProductSpecCharacteristic characteristic, int charSpecSeq,TimePeriod validFor){
    	  
    	return this.addRelatedCharacteristic(characteristic, RelationshipType.AGGREGATION.getValue(), charSpecSeq, validFor);
    
    }

    /**
     * 
     * @param characteristic
     */
    public void removeLeafCharacteristic(ProductSpecCharacteristic characteristic) {

    }

    public List<ProductSpecCharacteristic> retrieveLeafCharacteristic() {
    	
    	 return this.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());

    }

    /**
     * 
     * @param characteristic
     * @param type
     * @param charSpecSeq
     * @param validFor
     */
    public boolean  addRelatedCharacteristic(ProductSpecCharacteristic characteristic, String type, int charSpecSeq,
            TimePeriod validFor) {
           	 
	   	if ( null == characteristic ) {
	   		logger.error("characteristic  should not be null");
	   		throw new IllegalArgumentException("characteristic  should not be null"); 
	   	}
	   	if( null == type){
	   		logger.error("type should not be null");
	   		throw new IllegalArgumentException("type should not be null"); 
	   	}
	   	
	   	if(this.equals(characteristic)){
	   		
	   		logger.warn("the  SourceChar is"+this.basicInfoToString());
        	
	   		logger.warn("this TargetChar is"+characteristic.basicInfoToString());
        	
        	return false;	   	
        }
	   	
	   	ProductSpecCharRelationship relationship=this.retrieveRelatedCharacteristic(characteristic);
	   	if(relationship!=null){
	   		//compare
	   		if(relationship.getValidFor().isOverlap(validFor)){
	   			
	        	logger.warn("Characteristic have been created in the specified time");
	        	logger.warn("the exists relationship :"+relationship.toString());
	        	return false;
	        	
	   		}
	   	}
	   	
       ProductSpecCharRelationship productSpecCharValueRelationShip = new ProductSpecCharRelationship(this,
               characteristic, type, validFor,charSpecSeq);
       
      if(prodSpecCharRelationship==null) prodSpecCharRelationship=new ArrayList<ProductSpecCharRelationship>();
      
       this.prodSpecCharRelationship.add(productSpecCharValueRelationShip);
       
       return true;
    }
    
    public boolean updateRelatedCharValidPeriod(ProductSpecCharacteristic prodSpecChar,TimePeriod validFor){
    	
    	if (null == prodSpecChar ) {
    		logger.error("prodSpecChar should not be null .");
    		throw new IllegalArgumentException("prodSpecChar should not be null .");
    	}
    	if (null == validFor ){
    		logger.error("validFor should not be null .");
    		throw new IllegalArgumentException("validFor should not be null .");
    	}
    	
    	if ( null!=prodSpecCharRelationship ) {
	    	for (ProductSpecCharRelationship productSpecRelationship : prodSpecCharRelationship) {
	    		if ( productSpecRelationship.getTargetProdSpecChar().equals(prodSpecChar)) {
	    			productSpecRelationship.setValidFor(validFor);
	    			return true;
	    		}
			}
    	}
    	return false;
    }

    /**
     * 
     * @param characteristic
     */
    public void removeRelatedCharacteristic(ProductSpecCharacteristic characteristic) {
    }

    /**
     * 
     * @param type
     */
    public List<ProductSpecCharacteristic> retrieveRelatedCharacteristic(String type) {
    	
    	if(StringUtils.isEmpty(type)){
    		logger.error("type should not be null.");
    		throw new IllegalArgumentException("type should not be null.");
    		
    	}
    	List<ProductSpecCharacteristic>  characteristic=new ArrayList<ProductSpecCharacteristic>();
    	
    	if ( null != prodSpecCharRelationship ) {
    		for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
        		if(type.equals(productSpecCharRelationship.getCharRelationshipType())){
        			characteristic.add(productSpecCharRelationship.getTargetProdSpecChar());
        		}
    		}
    	} 
    	return characteristic;
 
    }
    
    private ProductSpecCharRelationship retrieveRelatedCharacteristic(ProductSpecCharacteristic characteristic ){
    	
    	if ( null == characteristic ) {
    		logger.error("characteristic  should not be null");
	   		throw new IllegalArgumentException("characteristic  should not be null"); 
	   	}
    	if (null !=prodSpecCharRelationship) {
    		for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
    			if( productSpecCharRelationship.getTargetProdSpecChar().equals(characteristic)){
        			return productSpecCharRelationship;
        		}
    		}
    	}
    	return null;
    }
    
    public List<ProductSpecCharacteristic> retrieveRelatedCharacteristic(String type,Date time) {
    	
    	if (StringUtils.isEmpty(type) ) {
    		logger.error("type   should not be null");
    		throw new IllegalArgumentException("type or dateTime  should not be null"); 
    	} 
    	if ( null == time) {
    		logger.error(" dateTime  should not be null");
    		throw new IllegalArgumentException(" dateTime  should not be null");
    	}
    	List<ProductSpecCharacteristic>  characteristic=new ArrayList<ProductSpecCharacteristic>();;

    	if (null !=prodSpecCharRelationship ) {
    		for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
        		if(type.equals(productSpecCharRelationship.getCharRelationshipType()) && productSpecCharRelationship.getValidFor().isInPeriod(time)){
        			characteristic.add(productSpecCharRelationship.getTargetProdSpecChar());
        		}
    		}
    	} 
    	return characteristic;
    }

    /**
     * 
     * @param minCardinality
     * @param maxCardinality
     */

    public void specifyCardinality(int minCardinality, int maxCardinality) {
    	
    	if ( maxCardinality < minCardinality ){
    		logger.error("maxCardinality is less than minCardinality");
    		throw new IllegalArgumentException("maxCardinality is less than minCardinality"); 
    	}
    	
        this.setMinCardinality(minCardinality);
        this.setMaxCardinality(maxCardinality);
    }

    public Set<ProductSpecCharacteristicValue> getProductSpecCharacteristicValue() {
        return productSpecCharacteristicValue;
    }

    public void setProductSpecCharacteristicValue(Set<ProductSpecCharacteristicValue> productSpecCharacteristicValue) {
        this.productSpecCharacteristicValue = productSpecCharacteristicValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
    	Map <String,Object> result=getBasicInfoToMap();
    	result.put("prodSpecCharValue", productSpecCharacteristicValue);
    	result.put("charRelationShip", prodSpecCharRelationship);
    	return result.toString();
    }
    
    public String basicInfoToString(){
    	return getBasicInfoToMap().toString();
    }
    private Map<String,Object> getBasicInfoToMap(){
    	Map <String,Object> result=new HashMap<String,Object>();
    	result.put("name",name);
    	result.put("id", ID);
    	result.put("unique", unique);
    	result.put("valueType",valueType);
    	result.put("extensible",extensible);
    	result.put("derivationFormula",derivationFormula);
    	result.put("maxCardinality",maxCardinality);
    	result.put("minCardinality",minCardinality);
    	result.put("validFor",validFor);
    	result.put("description",description);
    	return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ID == null) ? 0 : ID.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
    	
        if (this == obj) return true;
        
        if (null == obj) return false;
        
        if (getClass() != obj.getClass()) return false;
        
        ProductSpecCharacteristic other = (ProductSpecCharacteristic) obj;
        
        if (ID == null) {
            if (other.ID != null)
                return false;
        } else if (!ID.equals(other.ID))
            return false;

        return true;
    }
    
    
    
}
