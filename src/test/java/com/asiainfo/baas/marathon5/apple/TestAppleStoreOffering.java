package com.asiainfo.baas.marathon5.apple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonBeanProcessor;
import net.sf.json.util.CycleDetectionStrategy;

import org.junit.Test;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.BundledProductOffering;
import com.asiainfo.baas.marathon.offering.ProductOffering;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;
import com.asiainfo.baas.marathon.offering.catalog.ProductCatalog;
import com.asiainfo.baas.marathon5.common.CommonUtils;
import com.asiainfo.baas.marathon5.common.JsonDateValueProcessor;
import com.asiainfo.baas.marathon5.common.JsonPropertyFilter;

public class TestAppleStoreOffering {

    /**
     * 创建三个offering，一个复合offering，两个原子offering
     */
    @Test
    public void createOffering() {

        List<ProductOffering> offering = this.createProductOffering();
        CommonUtils.printPropertyToJson(null, offering, null);

    }

    @Test
    public void createCatalog() {

        ProductCatalog macBookProproductCatalog = this.createProductCatalog();
        CommonUtils.printPropertyToJson(null, null, macBookProproductCatalog);

    }

    @Test
    public void publishOffering() {

        ProductCatalog macBookProproductCatalog = this.createProductCatalog();
        List<ProductOffering> offerings = this.createProductOffering();
        if (offerings != null) {
            for (ProductOffering productOffering : offerings) {
                TimePeriod validFor = new TimePeriod("2015-01-01 00:00:00", "2015-07-01 00:00:00");
                macBookProproductCatalog.publishOffering(productOffering, validFor);
            }
        }
        CommonUtils.printPropertyToJson(null, null, macBookProproductCatalog);
    }

    public ProductCatalog createProductCatalog() {
        StringBuilder description1 = new StringBuilder();
        description1.append("2.7GHz 双核 Intel Core i5 处理器");
        description1.append("\n");
        description1.append("Turbo Boost 高达 3.1GHz");
        description1.append("\n");
        description1.append("8GB 1866MHz LPDDR3 内存");
        description1.append("\n");
        description1.append("基于 PCIe 的 128GB 闪存1");
        description1.append("\n");
        description1.append("Intel Iris Graphics 6100");
        description1.append("\n");
        description1.append("内置电池 (10 小时)2");
        description1.append("\n");
        description1.append("Force Touch 触控板");
        description1.append("\n");

        TimePeriod validFor1 = new TimePeriod("2013-01-01 00:00:00", null);
        ProductCatalog macBookProproductCatalog = new ProductCatalog("1", "MacBook Pro", "MacBook", validFor1);
        return macBookProproductCatalog;

    }

    public List<ProductOffering> createProductOffering() {
        List<ProductOffering> productOfferings = new ArrayList<ProductOffering>();
        StringBuilder description1 = new StringBuilder();
        description1.append("2.7GHz 双核 Intel Core i5 处理器");
        description1.append("\n");
        description1.append("Turbo Boost 高达 3.1GHz");
        description1.append("\n");
        description1.append("8GB 1866MHz LPDDR3 内存");
        description1.append("\n");
        description1.append("基于 PCIe 的 128GB 闪存1");
        description1.append("\n");
        description1.append("Intel Iris Graphics 6100");
        description1.append("\n");
        description1.append("内置电池 (10 小时)2");
        description1.append("\n");
        description1.append("Force Touch 触控板");
        description1.append("\n");

        TimePeriod validFor1 = new TimePeriod("2013-01-01 00:00:00", null);
        SimpleProductOffering simpleProductOffering1 = new SimpleProductOffering("11", "2.7GHz 处理器\n128 GB 存储容量",
                validFor1, ProductConst.OFFERING_STATUS_ACTIVE, null, description1.toString());

        StringBuilder description2 = new StringBuilder();
        description2.append("2.7GHz 双核 Intel Core i5 处理器");
        description2.append("\n");
        description2.append("Turbo Boost 高达 3.1GHz");
        description2.append("\n");
        description2.append("8GB 1866MHz LPDDR3 内存");
        description2.append("\n");
        description2.append("基于 PCIe 的 128GB 闪存1");
        description2.append("\n");
        description2.append("Intel Iris Graphics 6100");
        description2.append("\n");
        description2.append("内置电池 (10 小时)2");
        description2.append("\n");
        description2.append("Force Touch 触控板");
        description2.append("\n");

        TimePeriod validFor2 = new TimePeriod("2013-01-01 00:00:00", null);
        SimpleProductOffering simpleProductOffering2 = new SimpleProductOffering("12", "2.7GHz 处理器\n256 GB 存储容量",
                validFor2, ProductConst.OFFERING_STATUS_ACTIVE, null, description2.toString());

        TimePeriod validFor4 = new TimePeriod("2013-01-01 00:00:00", null);
        BundledProductOffering bundledProductOffering = new BundledProductOffering("1",
                "13 英寸配备 Retina 显示屏的 MacBook Pro", validFor4, ProductConst.OFFERING_STATUS_ACTIVE, "");
        bundledProductOffering.addSubOffering(simpleProductOffering1);
        bundledProductOffering.addSubOffering(simpleProductOffering2);

        // 两个原子Offering是互斥关系
        simpleProductOffering1.addRelatedOffering(simpleProductOffering2, ProductConst.RELATIONSHIP_TYPE_EXCLUSIVITY,
                validFor1);
        simpleProductOffering2.addRelatedOffering(simpleProductOffering1, ProductConst.RELATIONSHIP_TYPE_EXCLUSIVITY,
                validFor2);

        productOfferings.add(simpleProductOffering1);
        productOfferings.add(simpleProductOffering2);
        productOfferings.add(bundledProductOffering);

        return productOfferings;
    }

}
