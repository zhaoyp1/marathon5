package com.asiainfo.baas.marathon5.offering;

import org.junit.Test;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.ProductOffering;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;
import com.asiainfo.baas.marathon.offering.catalog.ProductCatalog;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class TestProductCatalog {

    @Test
    public void testPublishOffering() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        TimePeriod validFor5S = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);
        iPhone5SSpecification.setValidFor(validFor5S);

        String id = "1";
        String offeringName = "iPhone5S Offering";
        String offeringDescription = "iPhone5S Offering";
        String status = "1";
        TimePeriod validForOffering = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");

        SimpleProductOffering offering = new SimpleProductOffering(id, offeringName, validForOffering, status,
                iPhone5SSpecification, offeringDescription);

        String catalogId = "1";
        String catalogName = "日常";
        String catalogType = ProductConst.PRODUCT_CATALOG_TYPE_REGULAR;
        TimePeriod validForCatalog = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");

        ProductCatalog catalog = new ProductCatalog(catalogId, catalogName, catalogType, validForCatalog);

        TimePeriod publishTimePeriod = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");
        catalog.publishOffering(offering, publishTimePeriod);

        System.out.println("catalog ：");
        CommonUtils.printProperty(null, null, catalog);
        System.out.println("catalog.prodCatalogProdOffers ：");
        CommonUtils.printProperty(null, catalog.getProdCatalogProdOffers(), null);
        System.out.println("catalog.prodCatalogProdOffers.getProdOffering ：");
        CommonUtils.printProperty(null, null, catalog.getProdCatalogProdOffers().get(0).getProdOffering());
    }

    @Test
    public void testGetProductOffering() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        TimePeriod validFor5S = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);
        iPhone5SSpecification.setValidFor(validFor5S);

        String id = "1";
        String offeringName = "iPhone5S Offering";
        String offeringDescription = "iPhone5S Offering";
        String status = "1";
        TimePeriod validForOffering = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");

        SimpleProductOffering offering = new SimpleProductOffering(id, offeringName, validForOffering, status,
                iPhone5SSpecification, offeringDescription);

        String catalogId = "1";
        String catalogName = "日常";
        String catalogType = ProductConst.PRODUCT_CATALOG_TYPE_REGULAR;
        TimePeriod validForCatalog = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");

        ProductCatalog catalog = new ProductCatalog(catalogId, catalogName, catalogType, validForCatalog);

        TimePeriod publishTimePeriod = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");
        catalog.publishOffering(offering, publishTimePeriod);

        ProductOffering[] productOfferings = catalog.getProductOffering("1");

        System.out.println("catalog ：");
        CommonUtils.printProperty(null, null, catalog);
        System.out.println("catalog.getProductOffering(1)：");
        CommonUtils.printProperty(productOfferings, null, null);
    }
}
