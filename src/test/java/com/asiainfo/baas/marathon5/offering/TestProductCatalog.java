package com.asiainfo.baas.marathon5.offering;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;
import com.asiainfo.baas.marathon.offering.catalog.ProductCatalog;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class TestProductCatalog {

    @Test
    public void testPublishOffering() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        TimePeriod validFor5S = new TimePeriod();
        try {
            validFor5S.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor5S.endDateTime = format.parse("2015-09-21 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);
        iPhone5SSpecification.setValidFor(validFor5S);

        String id = "1";
        String offeringName = "iPhone5S Offering";
        String offeringDescription = "iPhone5S Offering";
        String status = "1";
        TimePeriod validForOffering = new TimePeriod();
        try {
            validForOffering.startDateTime = format.parse("2015-02-03 12:00:00");
            validForOffering.endDateTime = format.parse("2015-09-21 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleProductOffering offering = new SimpleProductOffering(id, offeringName, validForOffering, status,
                iPhone5SSpecification, offeringDescription);

        String catalogId = "1";
        String catalogName = "ÊÖ»ú";
        String catalogType = ProductConst.PRODUCT_CATALOG_TYPE_MOBILEPHONE;
        TimePeriod validForCatalog = new TimePeriod();
        try {
            validForOffering.startDateTime = format.parse("2015-02-03 12:00:00");
            validForOffering.endDateTime = format.parse("2015-09-21 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }

        ProductCatalog catalog = new ProductCatalog(catalogId, catalogName, catalogType, validForCatalog);

        TimePeriod publishTimePeriod = new TimePeriod();
        try {
            publishTimePeriod.startDateTime = format.parse("2015-02-03 12:00:00");
            publishTimePeriod.endDateTime = format.parse("2015-09-21 23:59:59");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        catalog.publishOffering(offering, publishTimePeriod);

        System.out.println("catalog £º");
        CommonUtils.printProperty(null, null, catalog);
        System.out.println("catalog.prodCatalogProdOffers £º");
        CommonUtils.printProperty(null, catalog.getProdCatalogProdOffers(), null);
        System.out.println("catalog.prodCatalogProdOffers.getProdOffering £º");
        CommonUtils.printProperty(null, null, catalog.getProdCatalogProdOffers().get(0).getProdOffering());
    }
}
