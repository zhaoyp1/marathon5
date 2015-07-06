package com.asiainfo.baas.marathon.baseType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.asiainfo.baas.common.DateUtils;

/**
 * A base / value business entity used to represent a period of time, between
 * two time points
 */
public class TimePeriod {

    /**
     * An instant of time, starting at the TimePeriod
     * 
     * Notes: If null, then represents to the beginning of time
     */
    public Date startDateTime;
    /**
     * An instant of time, ending at the TimePeriod:
     * 
     * Notes: If null, then represents to the end of time
     */
    public Date endDateTime;

    public TimePeriod(String startDateTime, String endDateTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            if (StringUtils.isNotEmpty(startDateTime)) {
                this.startDateTime = format.parse(startDateTime);
            }
            if (StringUtils.isNotEmpty(endDateTime)) {
                this.endDateTime = format.parse(endDateTime);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public TimePeriod() {

    }

    /**
     * 校验时间点是否在TimePeriod内
     * 
     * 
     * 
     * @param d1
     * @param d2
     */
    public boolean isInPeriod(Date targetDate) {

        // 如果时间段未设置，默认永久有效
        if (this.startDateTime != null && DateUtils.compareDate(targetDate, this.startDateTime) < 0) {
            return false;
        }

        if (this.endDateTime != null && DateUtils.compareDate(targetDate, this.endDateTime) > 0) {
            return false;
        }
        return true;
    }

    public  boolean isOverlap(TimePeriod validFor){  
        if (endDateTime.compareTo(validFor.getStartDateTime())>=0) return true;  
    	else return false; 
    }  
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Map <String,String> vaildFor=new HashMap<String,String>();
        vaildFor.put("startDateTime", this.startDateTime == null ? "" : format.format(this.startDateTime));
        vaildFor.put("endDateTime", this.endDateTime == null ? "" : format.format(this.endDateTime));
        return  vaildFor.toString();
    }

}
