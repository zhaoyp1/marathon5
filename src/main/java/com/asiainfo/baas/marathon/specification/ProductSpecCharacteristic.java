package com.asiainfo.baas.marathon.specification;

import java.util.*;
import java.util.Date;

import com.asiainfo.baas.marathon.baseType.*;
import com.asiainfo.baas.marathon.dateType.*;

/**
 * A characteristic quality or distinctive feature of a ProductSpecification. The characteristic can be take on a discrete value, such as color, can take on a range of values, (for example, sensitivity of 100-240 mV), or can be derived from a formula (for example, usage time (hrs) = 30 - talk time *3). Certain characteristics, such as color, may be configured during the ordering or some other process.
 */
public class ProductSpecCharacteristic {

    private List<ProductSpecCharacteristicValue> productSpecCharacteristicValue;
    private List<ProductSpecCharUse> prodSpecCharUse;
    private List<ProductSpecCharRelationship> prodSpecCharRelationship;
    /**
     * A unique identifier for the ProductSpecCharacteristic.
     * ?
     */
    private String ID;
    /**
     * A word, term, or phrase by which the characteristic is known and distinguished from characteristics.
     */
    private String name;
    /**
     * A narrative that explains the characteristic.
     */
    private String description;
    /**
     * An indicator that specifies if a value is unique for the specification.
     * 
     * Possible values are; "unique while value is in effect" and "unique whether value is in effect or not"
     */
    private String unique;
    /**
     * A kind of value that the characteristic can take on, such as numeric, text, and so forth.
     */
    private String valueType;
    /**
     * The minimum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where zero is the value for the minCardinality.
     */
    private int minCardinality;
    /**
     * The maximum number of instances a CharacteristicValue can take on. For example, zero to five phone numbers in a group calling plan, where five is the value for the maxCardinality.
     */
    private int maxCardinality;
    /**
     * An indicator that specifies that the values for the characteristic can be extended by adding new values when instantiating a characteristic for an Entity.
     */
    private boolean extensible;
    /**
     * A rule or principle represented in symbols, numbers, or letters, often in the form of an equation used to derive the value of a characteristic value.
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
    public ProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor, String unique, int minCardinality, int maxCardinality) {
        // TODO - implement ProductSpecCharacteristic.ProductSpecCharacteristic
        throw new UnsupportedOperationException();
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
    public ProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor, String unique, int minCardinality, int maxCardinality, boolean extensible, String description, String derivationFormula) {
        // TODO - implement ProductSpecCharacteristic.ProductSpecCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param value
     */
    public void addValue(ProductSpecCharacteristicValue value) {
        // TODO - implement ProductSpecCharacteristic.addValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param value
     */
    public void removeValue(ProductSpecCharacteristicValue value) {
        // TODO - implement ProductSpecCharacteristic.removeValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public ProductSpecCharacteristicValue[] getValue(Date time) {
        
    	throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param charVal
     */
    public void setDefaultValue(ProductSpecCharacteristicValue charVal) {
        // TODO - implement ProductSpecCharacteristic.setDefaultValue
        throw new UnsupportedOperationException();
    }

    public ProductSpecCharacteristicValue getDefaultValue() {
        // TODO - implement ProductSpecCharacteristic.getDefaultValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic
     */
    public void addLeafCharacteristic(ProductSpecCharacteristic characteristic) {
        // TODO - implement ProductSpecCharacteristic.addLeafCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic
     */
    public void removeLeafCharacteristic(ProductSpecCharacteristic characteristic) {
        // TODO - implement ProductSpecCharacteristic.removeLeafCharacteristic
        throw new UnsupportedOperationException();
    }

    public ProductSpecCharacteristic[] getLeafCharacteristic() {
        // TODO - implement ProductSpecCharacteristic.getLeafCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic
     * @param type
     * @param charSpecSeq
     * @param validFor
     */
    public void addRelatedCharacteristic(ProductSpecCharacteristic characteristic, String type, int charSpecSeq, TimePeriod validFor) {
        // TODO - implement ProductSpecCharacteristic.addRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic
     */
    public void removeRelatedCharacteristic(ProductSpecCharacteristic characteristic) {
        // TODO - implement ProductSpecCharacteristic.removeRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param type
     */
    public ProductSpecCharacteristic[] getRelatedCharacteristic(String type) {
        // TODO - implement ProductSpecCharacteristic.getRelatedCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param minCardinality
     * @param maxCardinality
     */
    public void setCardinality(int minCardinality, int maxCardinality) {
        // TODO - implement ProductSpecCharacteristic.setCardinality
        throw new UnsupportedOperationException();
    }

}