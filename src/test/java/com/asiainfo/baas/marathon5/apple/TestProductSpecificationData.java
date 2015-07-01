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
            { "34", "重量", "number", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },

            { "4", "OS X Yosemite", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1,
                    1, false },
            { "5", "内置APP", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "6", "基础服务", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "7", "有偿服务", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false },
            { "8", "联系方式", "text", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "unique", 1, 1, false } };
    // 特征值
    public static Object[][] specCharValue = {
            { "11", "number", true, "英寸", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "13.3" },
            { "11", "number", false, "英寸", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "15.4" },
            { "12", "text", true, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2560 x 1600" },
            { "12", "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2880 x 1800" },
            { "13", "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1680 x 1050" },
            { "13", "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1440 x 900" },
            { "13", "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1024 x 640" },
            { "13", "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1920 x 1200" },
            { "13", "text", false, "像素 (Retina)", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "1280 x 800" },
            { "2", "text", false, "", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2.7GHz\n2.7GHz 双核 Intel \nCore i5 处理器 \n(Turbo Boost 高达 3.1GHz)，配备 3MB 共享三级缓存" },
            { "2", "text", false, "", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "2.9GHz\n2.9GHz 双核 Intel \nCore i5 处理器 \n(Turbo Boost 高达3.3GHz)，配备 3MB 共享三级缓存" },
            { "2", "text", false, "", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "3.1GHz\n3.1GHz 双核 Intel \nCore i7 处理器 \n(Turbo Boost 高达 3.4GHz)，配备 4MB 共享三级缓存" },
            { "31", "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "21.9" },
            { "31", "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "24.71" },
            { "32", "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "31.4" },
            { "32", "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "35.89" },
            { "33", "number", false, "厘米", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "1.8" },
            { "34", "number", false, "千克", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "1.58" },
            { "34", "number", false, "千克", new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "2.04" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "AirDrop" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "AirPlay" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "听写功能" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "Gatekeeper" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "通知中心" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "新浪微博整合" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "内置分享" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "Finder标签页和标记" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "iCloud" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "iCloud钥匙串" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "iCloudDrive" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "FamilySharing" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "腾讯微博整合" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "多屏显示支持" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "Spotlight搜索" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "Safari省电模式" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "AppNap" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "PowerNap" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "内存压缩技术" },
            { "4", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "Dashboard" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "照片" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "iMovie" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "GarageBand" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "Pages" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "Numbers" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "Keynote" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "地图" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "iBooks" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "Safari" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "邮件" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "FaceTime" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "信息" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "日历" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "通讯录" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "提醒事项" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "TimeMachine" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "PhotoBooth" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "iTunes" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "GameCenter" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "预览" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "备忘录" },
            { "5", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"), "MacAppStore" },
            {
                    "6",
                    "text",
                    false,
                    "",
                    new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"),
                    "中国的消费者权益保护法包括 ''三包'' 规定，赋予了消费者自购机之日起 2 年的主要部件质量问题保修服务。在此基础上，配备 Retina 显示屏的 MacBook Pro 也附带 90 天的免费电话技术支持以及来自 Apple 的 1 年有限保修服务。" },
            {
                    "7",
                    "text",
                    false,
                    "",
                    new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"),
                    "通过购买 AppleCare Protection Plan 全方位服务计划，将你的服务和支持期限延长至购买之日起 3 年。只有 AppleCare Protection Plan 全方位服务计划可以为你提供直接来自 Apple 技术专家的电话支持，并保证所有维修都是经 Apple 授权的技术人员使用 Apple 原厂配件进行维修。" },
            { "8", "text", false, "", new TimePeriod("2015-02-0312:00:00", "2019-07-2123:59:59"),
                    "要了解更多信息，请访问 Apple 技术支持或致电 (86) 4006-272273。" } };

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
    // pro）（charId,canBeOveridden,isPackage,validFor,name,unique,minCardinality,maxCardinality,extensible,description,isHaveValue,valueIndex,isDefault）
    public static Object[][] one_charData = {
            { "1", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "显示屏", "unique", 1, 1,
                    true, "", false, null, null },
            { "11", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "Retina 显示屏", "unique",
                    1, 1, true, "13.3 英寸 (对角线) LED 背光显示屏 (采用 IPS 技术)；分辨率 2560 x 1600 (227 ppi)，支持数百万色彩", true,
                    new int[] { 0 }, new boolean[] { true } },
            { "12", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "初始分辨率", "unique", 1, 1,
                    true, "", true, new int[] { 0 }, new boolean[] { true } },
            { "13", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "扩展分辨率", "unique", 1, 1,
                    true, "", true, new int[] { 0, 1, 2 }, new boolean[] { true, true, true } },
            { "2", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "处理器", "unique", 1, 1,
                    true, "", true, new int[] { 0, 1, 2 }, new boolean[] { true, false, false } },
            { "3", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "重量和尺寸", "unique", 1, 1,
                    true, "", false, null, null },
            { "31", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "深度", "unique", 1, 1,
                    true, "", true, new int[] { 0 }, new boolean[] { true } },
            { "32", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "宽度", "unique", 1, 1,
                    true, "", true, new int[] { 0 }, new boolean[] { true } },
            { "33", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "高度", "unique", 1, 1,
                    true, "", true, new int[] { 0 }, new boolean[] { true } },
            { "34", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "重量", "unique", 1, 1,
                    true, "", true, new int[] { 0 }, new boolean[] { true } }

    };
    // 使用特征值
    public static Object[][] two_charData = {
            {
                    "4",
                    false,
                    true,
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "OS X Yosemite",
                    "unique",
                    1,
                    1,
                    true,
                    "OS X Yosemite 的使用直观简易，更经过精心打造，使得 Mac 硬件的功能得到充分发挥。它设计优雅流畅，改进了你常用的 app，实现了 Mac 和 iOS 设备之间的绝妙合作。",
                    true,
                    new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 },
                    new boolean[] { true, false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false } },
            {
                    "5",
                    false,
                    true,
                    new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"),
                    "内置APP",
                    "unique",
                    1,
                    1,
                    true,
                    "",
                    true,
                    new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21 },
                    new boolean[] { true, false, false, false, false, false, false, false, false, false, false, false,
                            false, false, false, false, false, false, false, false, false, false } } };

    // 使用特征值
    public static Object[][] three_charData = {
        
            { "6", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "基础服务", "unique", 1, 1,
                    true, "", true, new int[] { 0 }, new boolean[] { true } },
            { "7", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "有偿服务", "unique", 1, 1,
                    true, "", true, new int[] { 0 }, new boolean[] { true } },
            { "8", false, true, new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), "联系方式", "unique", 1, 1,
                    true, "", true, new int[] { 0 }, new boolean[] { true } } };

    public static Object[] specParameter = new Object[] { "mac pro-13-9288", "13英寸配备Retina显示屏", "MacPro", "in_active",
            new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), null, "1.0.0", "", "min", 109,
            "AtomicProductSpecification" };

    public static Object[] specParameter2 = new Object[] { "OS", "操作系统", "apple os", "in_active",
            new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), null, "2.0.0", "", "min", 109,
            "AtomicProductSpecification" };

    public static Object[] specParameter3 = new Object[] { "service", "保修", "apple service", "in_active",
            new TimePeriod("2015-02-03 12:00:00", "2019-07-21 23:59:59"), null, "2.0.0", "", "min", 109,
            "AtomicProductSpecification" };

}
