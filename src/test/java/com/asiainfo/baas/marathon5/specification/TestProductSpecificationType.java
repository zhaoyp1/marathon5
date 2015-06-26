package com.asiainfo.baas.marathon5.specification;

import java.util.Date;

import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductLine;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class TestProductSpecificationType {

    @Test
    public void testAddProdSpec() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);

        String productNumber1 = "6";
        String name1 = "iPhone6";
        String brand1 = "Apple iPhone";
        String lifecycleStatus1 = "1";

        AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
                brand1, lifecycleStatus1);

        String type = "iPhone";
        String description = "mobile product line";
        ProductLine productSpecificationType = new ProductLine(type, description);
        productSpecificationType.addProdSpec(iPhone5SSpecification);
        productSpecificationType.addProdSpec(appleCareSpecification);

        System.out.println("ProductLine £º");
        CommonUtils.printPropertyToJson(null, null, productSpecificationType);
        System.out.println("ProductLine.ProdSpecification £º");
        CommonUtils.printPropertyToJson(null, productSpecificationType.getProdSpec(), null);
    }

    @Test
    public void testQueryProdSpec() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);

        String productNumber1 = "6";
        String name1 = "iPhone6";
        String brand1 = "Apple iPhone";
        String lifecycleStatus1 = "1";

        AtomicProductSpecification iPhone6Specification = new AtomicProductSpecification(productNumber1, name1, brand1,
                lifecycleStatus1);

        TimePeriod validFor5S = new TimePeriod("2014-02-03 12:00:00", "2014-09-21 23:59:59");
        TimePeriod validFor6 = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");

        iPhone5SSpecification.setValidFor(validFor5S);
        iPhone6Specification.setValidFor(validFor6);

        String type = "iPhone";
        String description = "mobile product line";
        ProductLine productSpecificationType = new ProductLine(type, description);
        productSpecificationType.addProdSpec(iPhone5SSpecification);
        productSpecificationType.addProdSpec(iPhone6Specification);

        System.out.println("ProductLine £º");
        CommonUtils.printPropertyToJson(null, null, productSpecificationType);
        System.out.println("ProductLine.ProdSpecification £º");
        CommonUtils.printPropertyToJson(productSpecificationType.queryProdSpec(new Date()), null, null);
    }
}
