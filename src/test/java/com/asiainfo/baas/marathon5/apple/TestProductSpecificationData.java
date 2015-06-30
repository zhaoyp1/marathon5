package com.asiainfo.baas.marathon5.apple;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.TimePeriod;

public class TestProductSpecificationData {

    // ����
    public static Object[][] specChar = {
            { "1", "processor(������)", "number", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "unique",
                    1, 1 },
            { "2", "memory", "text", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "", 1, 1 } };
    // ����ֵ
    public static Object[][] specCharValue = {
            { 0, "number", true, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "8" },
            { 0, "number", false, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "16" },
            { 1, "number", true, "GHz", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "2.9" },
            { 1, "number", false, "GHz", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "2.7" }, };

    public static Object[][] relateSpecChar = { { "1", "2", ProductConst.RELATIONSHIP_TYPE_EXCLUSIVITY, "1",
            new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59") } };
    // ʹ������ֵ
    public static Object[][] one_charData = {
            { "processor(������)", false, true, new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), true,
                    new int[] { 0 }, "cpu", "unique", 1, 1, true, "CPU" },
            { "memory", false, true, new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), true,
                    new int[] { 0 }, "memory", "unique", 1, 1, true, "memory" },

    };
    // ʹ������ֵ
    public static Object[][] two_charData = {
            { "processor(������)", false, true, new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), true,
                    new int[] { 1 }, "cpu", "unique", 1, 1, true, "CPU" },
            { "memory", false, true, new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), true,
                    new int[] { 1 }, "memory", "unique", 1, 1, true, "memory" } };

    public static Object[] specParameter = new Object[] { "mac-13", "13-inch MacBook Pro", "apple", "in_active",
            new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), null, "1.0.0", "", "min", 109 };

    public static Object[] specParameter2 = new Object[] { "11", "2.7GHz ������ 256 GB �洢����", "apple", "in_active",
            new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), null, "2.0.0", "", "min", 109 };

}