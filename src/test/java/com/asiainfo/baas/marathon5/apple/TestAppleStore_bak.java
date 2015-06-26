package com.asiainfo.baas.marathon5.apple;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.Money;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.BundledProductOffering;
import com.asiainfo.baas.marathon.offering.ProductOffering;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;
import com.asiainfo.baas.marathon.offering.catalog.ProductCatalog;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class TestAppleStore_bak {

    private static List<ProductSpecCharacteristic> productSpecChars;

    @Before
    public void createProductSpecChar() {
        TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
        // 处理器
        ProductSpecCharacteristic productSpecCharProcessor = new ProductSpecCharacteristic("1", "processor(处理器)",
                "number", validFor, "unique", 1, 1);
        ProductSpecCharacteristicValue oneprocessorValue = new ProductSpecCharacteristicValue("number", true, "GHz",
                validFor, "2.9");
        ProductSpecCharacteristicValue twoprocessorValue = new ProductSpecCharacteristicValue("number", false, "GHz",
                validFor, "2.7");
        productSpecCharProcessor.addValue(oneprocessorValue);
        productSpecCharProcessor.addValue(twoprocessorValue);

        // 内存
        ProductSpecCharacteristic productSpecCharMemory = new ProductSpecCharacteristic("2", "memory", "text",
                validFor, "", 1, 1);
        ProductSpecCharacteristicValue memoryValue1 = new ProductSpecCharacteristicValue("text", false, "", validFor,
                "8GB 1866MHz LPDDR3 SDRAM");
        ProductSpecCharacteristicValue memoryValue2 = new ProductSpecCharacteristicValue("text", false, "", validFor,
                "16GB 1866MHz LPDDR3 SDRAM");
        productSpecCharMemory.addValue(memoryValue1);
        productSpecCharMemory.addValue(memoryValue2);

        productSpecChars = new ArrayList<ProductSpecCharacteristic>();
        productSpecChars.add(productSpecCharProcessor);
        productSpecChars.add(productSpecCharMemory);

    }

    @Test
    public void appleStore() throws Exception {

        // 创建规格
        List<ProductSpecification> productSpecifications = createProductSpecification();

        // 创建offering
        List<ProductOffering> offerings = this.createProductOffering(productSpecifications);

        // 创建catalog
        ProductCatalog catalog = this.createProductCatalog();

        // 发布offering
        if (offerings != null) {
            for (ProductOffering productOffering : offerings) {
                TimePeriod validFor = new TimePeriod("2015-01-01 00:00:00", "2015-07-01 00:00:00");
                catalog.publishOffering(productOffering, validFor);
            }
        }

        //
        ProductOffering[] productOfferings = catalog.getProductOffering(ProductConst.OFFERING_STATUS_ACTIVE);

        CommonUtils.printProperty(null, null, catalog);
        CommonUtils.printProperty(productOfferings, null, null);

    }

    /**
     * 创建两个规格
     * 
     * @return
     * @throws Exception
     */
    public List<ProductSpecification> createProductSpecification() throws Exception {

        List<ProductSpecification> productSpecs = new ArrayList<ProductSpecification>();

        /** 规格1 ***/
        // 处理器
        TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
        ProductSpecification productSpec = new AtomicProductSpecification("11", "2.7GHz 处理器 128 GB 存储容量", "apple",
                "in_active");
        ProductSpecCharacteristic prodSpecChar = this.getCharByCharName("processor(处理器)");
        productSpec.addCharacteristic(prodSpecChar, true, true, validFor);

        ProductSpecCharacteristicValue[] values = this.getCharValue(prodSpecChar, new int[] { 1 });
        if (values != null) {
            for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values) {
                productSpec.attachCharacteristicValue(prodSpecChar, productSpecCharacteristicValue, true, validFor);
            }
        }

        // 内存
        prodSpecChar = this.getCharByCharName("memory");
        productSpec.addCharacteristic(prodSpecChar, true, true, validFor);
        values = this.getCharValue(prodSpecChar, new int[] { 0 });
        if (values != null) {
            for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values) {
                productSpec.attachCharacteristicValue(prodSpecChar, productSpecCharacteristicValue, true, validFor);
            }
        }
        // 添加成本
        Money cost = new Money("min", 109);
        productSpec.addCost(cost, validFor);
        productSpec.setVersion("1.0.0", "", new Date(), validFor);
        /** 规格1 ***/

        /** 规格2 ***/
        // 处理器
        TimePeriod validFor1 = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
        ProductSpecification productSpec1 = new AtomicProductSpecification("11", "2.7GHz 处理器 256 GB 存储容量", "apple",
                "in_active");
        ProductSpecCharacteristic prodSpecChar1 = this.getCharByCharName("processor(处理器)");
        productSpec1.addCharacteristic(prodSpecChar1, true, true, validFor1);

        ProductSpecCharacteristicValue[] values1 = this.getCharValue(prodSpecChar1, new int[] { 1 });
        if (values1 != null) {
            for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values1) {
                productSpec1.attachCharacteristicValue(prodSpecChar1, productSpecCharacteristicValue, true, validFor1);
            }
        }

        // 内存
        prodSpecChar1 = this.getCharByCharName("memory");
        productSpec1.addCharacteristic(prodSpecChar1, true, true, validFor1);
        values1 = this.getCharValue(prodSpecChar1, new int[] { 1 });
        if (values1 != null) {
            for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values1) {
                productSpec1.attachCharacteristicValue(prodSpecChar1, productSpecCharacteristicValue, true, validFor1);
            }
        }
        // 添加成本
        Money cost1 = new Money("min", 109);
        productSpec1.addCost(cost1, validFor1);
        productSpec1.setVersion("1.0.0", "", new Date(), validFor1);
        /** 规格2 ***/

        productSpecs.add(productSpec);
        productSpecs.add(productSpec1);

        return productSpecs;
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

    public List<ProductOffering> createProductOffering(List<ProductSpecification> productSpecifications) {
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
                validFor1, ProductConst.OFFERING_STATUS_ACTIVE, productSpecifications.get(0), description1.toString());

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
                validFor2, ProductConst.OFFERING_STATUS_ACTIVE, productSpecifications.get(1), description2.toString());

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

    public ProductSpecCharacteristic getCharByCharName(String name) {
        ProductSpecCharacteristic prodSpecChar = null;
        for (int i = 0; i < productSpecChars.size(); i++) {
            prodSpecChar = productSpecChars.get(i);
            if (name.equals(prodSpecChar.getName())) {
                return prodSpecChar;
            }
        }
        return null;

    }

    public ProductSpecCharacteristicValue[] getCharValue(ProductSpecCharacteristic characteristic, int[] ids) {
        if (ids != null) {
            List<ProductSpecCharacteristicValue> productValues = characteristic.getProductSpecCharacteristicValue();
            List<ProductSpecCharacteristicValue> prodSpecChars = new ArrayList<ProductSpecCharacteristicValue>();

            for (int id : ids) {
                for (int i = 0; i < productValues.size(); i++) {
                    if (id == i) {
                        prodSpecChars.add(productValues.get(i));
                        break;
                    }
                }
            }
            return (ProductSpecCharacteristicValue[]) prodSpecChars
                    .toArray(new ProductSpecCharacteristicValue[prodSpecChars.size()]);
        }
        return null;

    }

}
