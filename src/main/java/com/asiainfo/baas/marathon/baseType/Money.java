package com.asiainfo.baas.marathon.baseType;

/**
 * A base / value business entity used to represent money
 */
public class Money {

    /**
     * Currency(Notes:refer to [ISO 4217]) or non-currency terms, such as loyalty points.
     */
    public String units;
    /**
     * A positive floating point number.
     */
    public long amount;

}