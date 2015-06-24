package com.asiainfo.baas.marathon.specification;

import com.asiainfo.baas.marathon.baseType.*;

/**
 * A type of ProductSpecCharacteristic, such as color, whose may be assigned during the ordering or some other process.
 */
public class ConfigurableProductSpecCharacteristic extends ProductSpecCharacteristic {

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
    public ConfigurableProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor, String unique, int minCardinality, int maxCardinality) {
    	super(id, name, valueType, validFor, unique, minCardinality, maxCardinality);
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
    public ConfigurableProductSpecCharacteristic(String id, String name, String valueType, TimePeriod validFor, String unique, int minCardinality, int maxCardinality, boolean extensible, String description, String derivationFormula) {
    	super(id, name, valueType, validFor, unique, minCardinality, maxCardinality, extensible, description, derivationFormula);   
    	throw new UnsupportedOperationException();
    }

}