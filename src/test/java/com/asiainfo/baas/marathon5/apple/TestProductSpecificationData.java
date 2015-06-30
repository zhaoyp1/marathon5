package com.asiainfo.baas.marathon5.apple;

import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;

public class TestProductSpecificationData {

    // 特征 （ID，name）
    public static Object[][] specChar = {
            { "1", "显示屏", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "11", "Retina 显示屏", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1,
                    1, false },
            { "12", "初始分辨率", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1,
                    false },
            { "13", "扩展分辨率", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1,
                    false },
            { "2", "处理器", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, true },
            { "3", "尺寸和重量", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1,
                    false },
            { "31", "长", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "32", "宽", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "33", "高", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "34", "重量", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false } };
    // 特征值
    public static Object[][] specCharValue = {
            { 1, "number", true, "英寸", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "13.3" },
            { 1, "number", false, "英寸", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "15.4" },
            { 2, "text", true, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2560 x 1600" },
            { 2, "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2880 x 1800" },
            { 3, "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1680 x 1050" },
            { 3, "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1440 x 900" },
            { 3, "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1024 x 640" },
            { 3, "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1920 x 1200" },
            { 3, "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1280 x 800" },
            { 4, "text", false, "", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2.7GHz\n2.7GHz 双核 Intel \nCore i5 处理器 \n(Turbo Boost 高达 3.1GHz)，配备 3MB 共享三级缓存" },
            { 4, "text", false, "", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2.9GHz\n2.9GHz 双核 Intel \nCore i5 处理器 \n(Turbo Boost 高达3.3GHz)，配备 3MB 共享三级缓存" },
            { 4, "text", false, "", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "3.1GHz\n3.1GHz 双核 Intel \nCore i7 处理器 \n(Turbo Boost 高达 3.4GHz)，配备 4MB 共享三级缓存" },
            { 6, "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "21.9" },
            { 6, "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "24.71" },
            { 7, "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "31.4" },
            { 7, "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "35.89" },
            { 8, "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "1.8" },
            { 9, "number", false, "千克", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "1.58" },
            { 9, "number", false, "千克", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "2.04" } };

    public static Object[][] relateSpecChar = {
            { "1", "11", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "1", "12", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "1", "13", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "3", "31", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "3", "32", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "3", "33", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") },
            { "3", "34", RelationshipType.AGGREGATION, "1",
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59") } };
    // 使用特征值（13寸mac
    // pro）（charId,canBeOveridden,isPackage,validFor,name,unique,minCardinality,maxCardinality,extensible,description,isHaveValue）
    public static Object[][] one_charData = {
            { "1", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "显示屏", "unique", 1, 1,
                    true, "", false, null },
            { "11", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "Retina 显示屏", "unique",
                    1, 1, true, "13.3 英寸 (对角线) LED 背光显示屏 (采用 IPS 技术)；分辨率 2560 x 1600 (227 ppi)，支持数百万色彩", true,
                    new int[] { 0 } },
            { "12", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "Retina 显示屏", "unique",
                    1, 1, true, "13.3 英寸 (对角线) LED 背光显示屏 (采用 IPS 技术)；分辨率 2560 x 1600 (227 ppi)，支持数百万色彩", true,
                    new int[] { 0 } },
            { "13", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "Retina 显示屏", "unique",
                    1, 1, true, "13.3 英寸 (对角线) LED 背光显示屏 (采用 IPS 技术)；分辨率 2560 x 1600 (227 ppi)，支持数百万色彩", true,
                    new int[] { 0 } }

    };
    // 使用特征值
    public static Object[][] two_charData = {
            { "processor(处理器)", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), true,
                    new int[] { 1 }, "cpu", "unique", 1, 1, true, "CPU" },
            { "memory", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), true,
                    new int[] { 1 }, "memory", "unique", 1, 1, true, "memory" } };

    public static Object[] specParameter = new Object[] { "mac pro-13-9288", "13英寸配备Retina显示屏", "MacPro", "in_active",
            new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), null, "1.0.0", "", "min", 109 };

    public static Object[] specParameter2 = new Object[] { "11", "2.7GHz 处理器 256 GB 存储容量", "apple", "in_active",
            new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), null, "2.0.0", "", "min", 109 };

}
