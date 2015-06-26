package com.asiainfo.baas.marathon5.apple;

import com.asiainfo.baas.marathon.baseType.TimePeriod;

public class TestProductSpecificationDate {
	 
	public static Object[][] one_charData = {
		{"processor(处理器)",false,true,new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"),true,new int[]{0}},
		{"memory",false,true,new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"),true,new int[]{0}},
	};
	public static Object[][] two_charData = {
		{"processor(处理器)",false,true,new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"),true,new int[]{1}},
		{"memory",false,true,new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"),true,new int[]{1}},	};
	
	public static Object[] specParameter = new Object[]{"mac-13","13-inch MacBook Pro","apple","in_active",new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"),null,"1.0.0","","min",109};
	
	public static Object[] specParameter2 = new Object[]{"11","2.7GHz 处理器 256 GB 存储容量","apple","in_active",new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"),null,"2.0.0","","min",109};


}
