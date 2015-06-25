package com.asiainfo.baas.marathon5.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.Money;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.CompositeProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecificationCost;
import com.asiainfo.baas.marathon.specification.ProductSpecificationVersion;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class TestProductSpecification {

    @Test
    public void addCost() {
        Money cost = new Money();
        cost.amount = 11;
        cost.units = "mine";
        TimePeriod timePeriod = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            timePeriod.startDateTime = format.parse("2015-02-03 12:00:00");
            timePeriod.endDateTime = format.parse("2015-07-21 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        AtomicProductSpecification prodSpec = new AtomicProductSpecification("1342", "343", "", "");
        prodSpec.addCost(cost, timePeriod);
    }

    @Test
    public void queryCost() {
        Money cost = new Money();
        cost.amount = 11;
        cost.units = "mine";
        TimePeriod timePeriod = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            timePeriod.startDateTime = format.parse("2015-02-03 12:00:00");
            timePeriod.endDateTime = format.parse("2015-07-21 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        AtomicProductSpecification prodSpec = new AtomicProductSpecification("1342", "343", "", "");
        prodSpec.addCost(cost, timePeriod);
        try {
            ProductSpecificationCost[] prodSpecCostList = prodSpec.queryCost(format.parse("2015-07-20 23:59:59"));
            for (ProductSpecificationCost productSpecificationCost : prodSpecCostList) {
                System.out.println(productSpecificationCost.getCostToBusiness().amount + ","
                        + productSpecificationCost.getCostToBusiness().units + ","
                        + format.format(productSpecificationCost.getValidFor().startDateTime) + " ,"
                        + format.format(productSpecificationCost.getValidFor().endDateTime));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetVersion() {
        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification atomicProductSpecification = new AtomicProductSpecification(productNumber, name,
                brand, lifecycleStatus);

        String version = "1.0.0";
        String description = "Available in silver, gold, and space gray, iPhone 6 features an A8 chip, Touch ID, faster LTE wireless, a new 8MP iSight camera with Focus Pixels, and iOS 8";
        Date revisionDate = new Date();
        TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-07-21 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            atomicProductSpecification.setVersion(version, description, revisionDate, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetCurrentVersion() {
        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification atomicProductSpecification = new AtomicProductSpecification(productNumber, name,
                brand, lifecycleStatus);

        String version = "1.0.0";
        String description = "Available in silver, gold, and space gray, iPhone 6 features an A8 chip, Touch ID, faster LTE wireless, a new 8MP iSight camera with Focus Pixels, and iOS 8";
        Date revisionDate = new Date();
        TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-09-21 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            atomicProductSpecification.setVersion(version, description, revisionDate, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProductSpecificationVersion[] currentVersion = atomicProductSpecification.getCurrentVersion();

        StringBuilder outText = new StringBuilder();
        for (ProductSpecificationVersion productSpecificationVersion : currentVersion) {
            outText.append(productSpecificationVersion.getProdSpecRevisionType());
            outText.append(" ");
            outText.append(productSpecificationVersion.getProdSpecRevisionNumber());
            outText.append(" ");
            outText.append(productSpecificationVersion.getDescription());
            outText.append(" ");
            outText.append(format.format(productSpecificationVersion.getProdSpecRevisionDate()));
            outText.append(" ");
            outText.append(format.format(productSpecificationVersion.getValidFor().startDateTime));
            outText.append(" ");
            outText.append(format.format(productSpecificationVersion.getValidFor().endDateTime));
            outText.append("\n");
        }

        System.out.println(outText.toString());
    }

    @Test
    public void testGetCurrentVersionString() {
        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification atomicProductSpecification = new AtomicProductSpecification(productNumber, name,
                brand, lifecycleStatus);

        String version = "1.0.0";
        String description = "Available in silver, gold, and space gray, iPhone 6 features an A8 chip, Touch ID, faster LTE wireless, a new 8MP iSight camera with Focus Pixels, and iOS 8";
        Date revisionDate = new Date();
        TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-09-21 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            atomicProductSpecification.setVersion(version, description, revisionDate, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String currentVersion = atomicProductSpecification.getCurrentVersionString();

        System.out.println(currentVersion);
    }

    @Test
    public void testAddRelatedProdSpec() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);

        String productNumber1 = "AC001";
        String name1 = "AppleCare For iPhone";
        String brand1 = "AppleCare";
        String lifecycleStatus1 = "1";

        AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
                brand1, lifecycleStatus1);

        // type:1=“¿¿µ 2=ª•≥‚
        String type = ProductConst.RELATIONSHIP_TYPE_DEPENDENCY;
        TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-09-21 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            iPhone5SSpecification.addRelatedProdSpec(appleCareSpecification, type, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryRelatedProdSpec() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);

        String productNumber1 = "AC001";
        String name1 = "AppleCare For iPhone";
        String brand1 = "AppleCare";
        String lifecycleStatus1 = "1";

        AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
                brand1, lifecycleStatus1);

        // type:1=“¿¿µ 2=ª•≥‚
        String type = ProductConst.RELATIONSHIP_TYPE_EXCLUSIVITY;
        TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-09-21 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            iPhone5SSpecification.addRelatedProdSpec(appleCareSpecification, type, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProductSpecification[] productSpecification = iPhone5SSpecification.queryRelatedProdSpec(type);
        CommonUtils.printProperty(productSpecification, null, null);

    }

    @Test
    public void testAddSubProdSpec() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);

        String productNumber1 = "AC001";
        String name1 = "AppleCare For iPhone";
        String brand1 = "AppleCare";
        String lifecycleStatus1 = "1";

        AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
                brand1, lifecycleStatus1);

        String productNumber2 = "iPhone5s&AC001";
        String name2 = "iPhone5s + AppleCare For iPhone";
        String brand2 = "AppleCare";
        String lifecycleStatus2 = "1";

        CompositeProductSpecification compositeProductSpecification = new CompositeProductSpecification(productNumber2,
                name2, brand2, lifecycleStatus2);

        compositeProductSpecification.addSubProdSpec(iPhone5SSpecification);
        compositeProductSpecification.addSubProdSpec(appleCareSpecification);

        System.out.println("compositeProductSpecification £∫");
        CommonUtils.printProperty(null, null, compositeProductSpecification);
        System.out.println("SubSpecification £∫");
        CommonUtils.printProperty(null, compositeProductSpecification.getProdSpec(), null);
    }
}
