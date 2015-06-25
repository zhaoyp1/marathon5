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

    /** 产品目录类型 ----手机 **/
    public static final String PRODUCT_CATALOG_TYPE_MOBILEPHONE = "1";
    /** 产品目录类型-----笔记本 **/
    public static final String PRODUCT_CATALOG_TYPE_LAPTOP = "2";

    /** 状态类型 -----正常 **/
    public static final String STATUS_TYPE_NORMAL = "normal";
    /** 规格类型 ----删除 **/
    public static final String STATUS_TYPE_DELETE = "delete";

    /** 版本类型 -----大版本 **/
    public static final String VERSION_TYPE_MAJOR = "major";
    /** 规格状态 ----中版本 **/
    public static final String VERSION_TYPE_MINOR = "minor";
    /** 规格状态 ----小版本 **/
    public static final String version_TYPE_patch = "patch";

    /** Offering状态-----planned **/
    public static final String OFFERING_STATUS_PLANNED = "planned";
    /** Offering状态 ----obsolete **/
    public static final String OFFERING_STATUS_OBSOLETE = "obsolete";
    /** Offering状态 ----active **/
    public static final String OFFERING_STATUS_ACTIVE = "active";

}
