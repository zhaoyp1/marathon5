package com.asiainfo.baas.marathon.baseType;

import java.util.Date;

/**
 * A base / value business entity used to represent a period of time, between two time points
 */
public class TimePeriod {

    /**
     * An instant of time, starting at the TimePeriod
     * 
     * Notes:
     * If null, then represents to the beginning of time
     */
    public Date startDateTime;
    /**
     * An instant of time, ending at the TimePeriod:
     * 
     * Notes:
     * If null, then represents to the end of time
     */
    public Date endDateTime;

}