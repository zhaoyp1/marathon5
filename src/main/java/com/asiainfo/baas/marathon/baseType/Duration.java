package com.asiainfo.baas.marathon.baseType;

/**
 * A base / value business entity used to represent a length of time.Duration is a type of Quantity
 */
public abstract class Duration {

    /**
     * The unit of measure for the quantity, such as seconds,minutes, hours.
     */
    public String units;
    /**
     * A quantity of something, typically the total of a thing or things in number, size, value, or extent.
     */
    public long amount;

}