package com.asiainfo.baas.common;

public class ProductConst {
    /** 依赖 **/
    public static final String RELATIONSHIP_TYPE_DEPENDENCY = "DEPENDENCY";
    /** 互斥 **/
    public static final String RELATIONSHIP_TYPE_EXCLUSIVITY = "EXCLUSIVITY";
    /** 置换 **/
    public static final String RELATIONSHIP_TYPE_SUBSTITUTION = "SUBSTITUTION";
    /** 聚合 **/
    public static final String RELATIONSHIP_TYPE_AGGREGATION = "AGGREGATION ";

    /** 产品目录类型 ----日常类 **/
    public static final String PRODUCT_CATALOG_TYPE_REGULAR = "REGULAR";
    /** 产品目录类型-----促销 类 **/
    public static final String PRODUCT_CATALOG_TYPE_PROMOTION = "PROMOTION";

    /** 版本类型 -----大版本 **/
    public static final String VERSION_TYPE_MAJOR = "MAJOR";
    /** 规格状态 ----中版本 **/
    public static final String VERSION_TYPE_MINOR = "MINOR";
    /** 规格状态 ----小版本 **/
    public static final String VERSION_TYPE_PATCH = "PATCH";

    /** Offering状态-----planned **/
    public static final String OFFERING_STATUS_PLANNED = "PLANNED";
    /** Offering状态 ----obsolete **/
    public static final String OFFERING_STATUS_OBSOLETE = "OBSOLETE";
    /** Offering状态 ----active **/
    public static final String OFFERING_STATUS_ACTIVE = "ACTIVE";

    /** 特征类型-----数值型 **/
    public static final String CHARACRISTIC_TYPE_NUMBER = "NUMBER";
    /** 特征类型 ----文本 **/
    public static final String CHARACRISTIC_TYPE_TEXT = "TEXT";
    /** 特征值类型 ----数值型 **/
    public static final String CHARACRISTICVALUE_TYPE_NUMBER = "NUMBER";
    /** 特征值类型 ----文本 **/
    public static final String CHARACRISTICVALUE_TYPE_TEXT = "TEXT";

}
