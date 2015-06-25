package com.asiainfo.baas.marathon5.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        CommonUtils.printProperty(null, null, productSpecificationType);
        System.out.println("ProductLine.ProdSpecification £º");
        CommonUtils.printProperty(null, productSpecificationType.getProdSpec(), null);
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

        TimePeriod validFor5S = new TimePeriod();
        TimePeriod validFor6 = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            validFor5S.startDateTime = format.parse("2014-02-03 12:00:00");
            validFor5S.endDateTime = format.parse("2014-09-21 23:59:59");

            validFor6.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor6.endDateTime = format.parse("2015-09-21 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        iPhone5SSpecification.setValidFor(validFor5S);
        iPhone6Specification.setValidFor(validFor6);

        String type = "iPhone";
        String description = "mobile product line";
        ProductLine productSpecificationType = new ProductLine(type, description);
        productSpecificationType.addProdSpec(iPhone5SSpecification);
        productSpecificationType.addProdSpec(iPhone6Specification);

        System.out.println("ProductLine £º");
        CommonUtils.printProperty(null, null, productSpecificationType);
        System.out.println("ProductLine.ProdSpecification £º");
        CommonUtils.printProperty(productSpecificationType.queryProdSpec(new Date()), null, null);
    }
}
