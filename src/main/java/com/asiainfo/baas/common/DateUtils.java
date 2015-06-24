package com.asiainfo.baas.common;

import java.util.Date;

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

}
