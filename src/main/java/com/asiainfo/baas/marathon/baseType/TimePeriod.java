package com.asiainfo.baas.marathon.baseType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    
    public TimePeriod(String startDateTime,String endDateTime){
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
        	if("".equals(startDateTime)){
        		 this.startDateTime = format.parse(startDateTime);
        	}
        	if("".equals(endDateTime)){
        		this.endDateTime = format.parse(endDateTime);
        	}
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public TimePeriod(){
    	
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

}