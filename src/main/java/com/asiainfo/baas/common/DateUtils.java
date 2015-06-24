package com.asiainfo.baas.common;

import java.util.Date;

import com.asiainfo.baas.marathon.baseType.TimePeriod;

public class DateUtils {

    /**
     * 比较两个日期大小
     * 
     * d1=d2 : 0; d1<d2: 1; d1>d2: -1
     * 
     * @param d1
     * @param d2
     */
    public static int compareDate(Date d1, Date d2) {
        return d1.compareTo(d2);
    }

    /**
     * 校验时间点是否在TimePeriod内
     * 
     * 
     * 
     * @param d1
     * @param d2
     */
    public static boolean isInPeriod(Date targetDate, TimePeriod validFor) {

        // 如果时间段未设置，默认永久有效
        if (validFor.startDateTime != null && compareDate(targetDate, validFor.startDateTime) < 0) {
            return false;
        }
        if (validFor.endDateTime != null && compareDate(targetDate, validFor.endDateTime) > 0) {
            return false;
        }
        return true;
    }

}
