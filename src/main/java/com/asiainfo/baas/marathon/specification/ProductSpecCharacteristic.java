package com.asiainfo.baas.marathon.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.common.ReflectionToStringBuilderBaas;
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

    private List<ProductSpecCharacteristicValue> productSpecCharacteristicValue;
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

        if (value == null) {
            logger.error("特征值ProductSpecCharacteristicValue为空，不能添加");
            return false;
        }
        if (this.productSpecCharacteristicValue == null) {
            this.productSpecCharacteristicValue = new ArrayList<ProductSpecCharacteristicValue>();
        }
        if (productSpecCharacteristicValue.contains(value)) {
            logger.error("特征值ProductSpecCharacteristicValue已经存在");
            return false;
        }
        if (value.getValueType() != null && !this.getValueType().equals(value.getValueType())) {
            logger.error("特征值ProductSpecCharacteristicValue与特征ProductSpecCharacteristic的valueType不相同");
            return false;
        }
        productSpecCharacteristicValue.add(value);
        return true;

    }

    /**
     * 
     * @param value
     */
    public void removeValue(ProductSpecCharacteristicValue value) {

    }

    /**
     * 
     * @param time
     */
    public List<ProductSpecCharacteristicValue> retrieveValue(Date time) {
        List<ProductSpecCharacteristicValue> productSpecCharValues = null;
        if (time == null) {
            logger.error("时间不能为空");
        } else {
            if (this.productSpecCharacteristicValue != null) {
                productSpecCharValues = new ArrayList<ProductSpecCharacteristicValue>();
                for (ProductSpecCharacteristicValue charValue : productSpecCharacteristicValue) {
                    if (charValue.getValidFor() != null && charValue.getValidFor().isInPeriod(time)) {
                        productSpecCharValues.add(charValue);
                    }
                }
            } else {
                logger.warn("productSpecCharacteristic不存在特征值");
            }
        }

        return productSpecCharValues;
    }

    /**
     * 
     * @param charVal
     */

    public boolean specifyDefaultValue(ProductSpecCharacteristicValue charVal) {
    	
    	if(charVal==null){
    		 logger.error("特征值不能设置为空");
    		 return false;
    	}
    	if(this.productSpecCharacteristicValue==null){
    		 logger.error("当前特征不存在特征值");
    		 return false;
    	}
    	if(!productSpecCharacteristicValue.contains(charVal)){
    		logger.error("当前特征不包含指定特征值");
    		return false;
    	}
    	List<ProductSpecCharacteristicValue> defaultValue=this.retrieveDefaultValue();
    	if(defaultValue!=null && defaultValue.contains(charVal)){
    		logger.warn("指定特征值已经设置为默认值，无需进行重复设置");
    		return true;
    	}

    	for (ProductSpecCharacteristicValue charValue : productSpecCharacteristicValue) {
    		 
    		if(charValue.equals(charVal)){
    			charVal.setIsDefault(true);
    		} 
    	}
    	return true;
    	

    }


    public List<ProductSpecCharacteristicValue> retrieveDefaultValue() {
    	List<ProductSpecCharacteristicValue> defaultSpecCharValue=null;
    	if(this.productSpecCharacteristicValue!=null){
    		defaultSpecCharValue=new ArrayList<ProductSpecCharacteristicValue>();
    		for (ProductSpecCharacteristicValue charValue : productSpecCharacteristicValue) {
    			if(charValue.isIsDefault()){
    				defaultSpecCharValue.add(charValue);
    			}
			}
    		if(defaultSpecCharValue.size()==0){
    			logger.info("\t特征没有设置默认值");
    			return null;
    		}else{
    			return defaultSpecCharValue;
    		}
    	}
    	logger.info("\t特征没有特征值");
    	return null;
    }
    public boolean clearDefaultValue(ProductSpecCharacteristicValue value){
    	
    	if(value==null){
    		logger.error("特征值为空");
    		return false;
    	}
    	if(productSpecCharacteristicValue==null){
    		logger.error("当前特征没有特征值");
    		return false;
    	}
    	if(!productSpecCharacteristicValue.contains(value)){
    		logger.error("当前特征不包含指定特征值");
    		return false;
    	}
    	List<ProductSpecCharacteristicValue> defaults=this.retrieveDefaultValue();
    	if(defaults==null){
    		return false;
    	}else{
    		if(!defaults.contains(value)){
    			logger.error("指定特征值不是当前特征的默认值");
    			return false;
    		}
    		for (ProductSpecCharacteristicValue productSpecCharacteristicValue : defaults) {
				if(productSpecCharacteristicValue.equals(value)){
					productSpecCharacteristicValue.setIsDefault(false);
					break;
				}
			}
    		
    	}
		return true;
    	
 
    }

    /**
     * 
     * @param characteristic
     * @throws Exception 
     */
    public boolean addLeafCharacteristic(ProductSpecCharacteristic characteristic, TimePeriod validFor){
    	 if (this.prodSpecCharRelationship == null)
             prodSpecCharRelationship = new ArrayList<ProductSpecCharRelationship>();
    	 
    	if(characteristic==null||validFor==null){
    		logger.error("特征和有效期不能为空");
    		return false;
    	}
    	if(this.equals(characteristic)){
    		logger.error("当前特征与指定特征相同，不能创建关系");
    		return false;
    	}
    	ProductSpecCharRelationship leafRelationship=this.retrieveRelatedCharacteristic(characteristic);
    	if(leafRelationship!=null){
    		//比较是否有重复
    		if(leafRelationship.getValidFor().isOverlap(validFor)){
    			logger.error("当前特征已与指定特征创建聚合关系，无需在重新创建");
    			return false;
    		}
    	}
        ProductSpecCharRelationship productSpecCharValueRelationShip = new ProductSpecCharRelationship(this,
                characteristic, RelationshipType.AGGREGATION.getValue(), validFor);
        if(prodSpecCharRelationship==null) prodSpecCharRelationship=new ArrayList<ProductSpecCharRelationship>();
        this.prodSpecCharRelationship.add(productSpecCharValueRelationShip);
        return true;
    }

    /**
     * 
     * @param characteristic
     */
    public void removeLeafCharacteristic(ProductSpecCharacteristic characteristic) {

    }

    public List<ProductSpecCharacteristic> retrieveLeafCharacteristic() {
    	List<ProductSpecCharacteristic>  leafCharacteristic=null;
    	if(prodSpecCharRelationship!=null){
    		leafCharacteristic=new ArrayList<ProductSpecCharacteristic>();
    		for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
        		if(RelationshipType.AGGREGATION.getValue().equals(productSpecCharRelationship.getCharRelationshipType())){
        			leafCharacteristic.add(productSpecCharRelationship.getTargetProdSpecChar());
        		}
    		}
    		if(leafCharacteristic.size()==0){
    			logger.info("当前特征没有聚合关系的特征");
    			return null;
    		}
    		return leafCharacteristic;
    	}else{
    		logger.info("当前特征没有相关联的特征");
    	}
    	return null;

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
           	 
	   	if(characteristic==null||validFor==null||type==null){
	   		logger.error("特征、有效期和类型、不能为空");
	   		return false;
	   	}
	   	if(this.equals(characteristic)){
	   		logger.error("当前特征与指定特征相同，不能创建关系");
	   		return false;
	   	}
	   	ProductSpecCharRelationship relationship=this.retrieveRelatedCharacteristic(characteristic);
	   	if(relationship!=null){
	   		//比较是否有重复
	   		if(relationship.getValidFor().isOverlap(validFor)){
	   			logger.error("当前特征已与指定特征创建了指定的关系，无需在重新创建");
	   			return false;
	   		}
	   	}
       ProductSpecCharRelationship productSpecCharValueRelationShip = new ProductSpecCharRelationship(this,
               characteristic, type, validFor,charSpecSeq);
      if(prodSpecCharRelationship==null) prodSpecCharRelationship=new ArrayList<ProductSpecCharRelationship>();
       this.prodSpecCharRelationship.add(productSpecCharValueRelationShip);
       return true;
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
    	List<ProductSpecCharacteristic>  characteristic=null;
    	if(StringUtils.isEmpty(type)){
    		logger.error("指定的关系类型为空");
    		return null;
    	}
    	if(prodSpecCharRelationship!=null){
    		characteristic=new ArrayList<ProductSpecCharacteristic>();
    		for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
        		if(type.equals(productSpecCharRelationship.getCharRelationshipType())){
        			characteristic.add(productSpecCharRelationship.getTargetProdSpecChar());
        		}
    		}
    		if(characteristic.size()==0){
    			logger.info("当前特征没有指定关系的特征");
    			return null;
    		}
    		return characteristic;
    	}else{
    		logger.info("当前特征没有相关联的特征");
    	}
    	return null;
 
    }
    private ProductSpecCharRelationship retrieveRelatedCharacteristic(ProductSpecCharacteristic characteristic ){
    	if(prodSpecCharRelationship!=null){
    		for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
        		if( productSpecCharRelationship.getTargetProdSpecChar().equals(characteristic)){
        			return productSpecCharRelationship;
        		}
    		}
    	}
    	return null;
    }
    
    public List<ProductSpecCharacteristic> retrieveRelatedCharacteristic(String type,Date time) {
    	List<ProductSpecCharacteristic>  characteristic=null;
    	if(StringUtils.isEmpty(type)){
    		logger.error("指定的关系类型为空");
    		return null;
    	}
    	if(time==null){
    		logger.error("查询时间点为空");
    		return null;
    	}
    	if(prodSpecCharRelationship!=null){
    		characteristic=new ArrayList<ProductSpecCharacteristic>();
    		for (ProductSpecCharRelationship productSpecCharRelationship : prodSpecCharRelationship) {
        		if(type.equals(productSpecCharRelationship.getCharRelationshipType()) && productSpecCharRelationship.getValidFor().isInPeriod(time)){
        			characteristic.add(productSpecCharRelationship.getTargetProdSpecChar());
        		}
    		}
    		if(characteristic.size()==0){
    			logger.info("当前特征在指定时间点内没有指定关系的特征");
    			return null;
    		}
    		return characteristic;
    	}else{
    		logger.info("当前特征没有相关联的特征");
    	}
    	return null;
    }

    /**
     * 
     * @param minCardinality
     * @param maxCardinality
     */

    public void specifyCardinality(int minCardinality, int maxCardinality) {
        this.setMinCardinality(minCardinality);
        this.setMaxCardinality(maxCardinality);
    }

    public List<ProductSpecCharacteristicValue> getProductSpecCharacteristicValue() {
        return productSpecCharacteristicValue;
    }

    public void setProductSpecCharacteristicValue(List<ProductSpecCharacteristicValue> productSpecCharacteristicValue) {
        this.productSpecCharacteristicValue = productSpecCharacteristicValue;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((ID == null) ? 0 : ID.hashCode());
        result = prime * result + maxCardinality;
        result = prime * result + minCardinality;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((unique == null) ? 0 : unique.hashCode());
        result = prime * result + ((validFor == null) ? 0 : validFor.hashCode());
        result = prime * result + ((valueType == null) ? 0 : valueType.hashCode());
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
        ProductSpecCharacteristic other = (ProductSpecCharacteristic) obj;
        if (ID == null) {
            if (other.ID != null)
                return false;
        } else if (!ID.equals(other.ID))
            return false;

        return true;
    }

}
