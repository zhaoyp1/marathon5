package com.asiainfo.baas.marathon.baseType;

/**
 * A base / value business entity used to represent measurements.
 */
public abstract class Quantity {

    /**
     * A quantity of something, typically the total of a thing or things in number, size, value, extent, or money.
     */
    public long amount;
    /**
     * The unit of measure for the quantity, such as meters, cubic yards, kilograms [ISO 80000], and the currency referring to [ISO 4217].
     */
    public String units;

}