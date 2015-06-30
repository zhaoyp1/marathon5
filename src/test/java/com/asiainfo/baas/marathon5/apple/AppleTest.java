package com.asiainfo.baas.marathon5.apple;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.BundledProductOffering;
import com.asiainfo.baas.marathon.offering.ProductOffering;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;
import com.asiainfo.baas.marathon.offering.catalog.ProductCatalog;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ConfigurableProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProdSpecCharValueUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class AppleTest {

    private Logger logger = Logger.getLogger(AppleTest.class);

    private static List<ProductSpecCharacteristic> productSpecChars;

    @Before
    public void createProductSpecChar() {

        logger.info("准备特征/特征值数据----------start");
        productSpecChars = new ArrayList<ProductSpecCharacteristic>();
        for (int i = 0; i < TestProductSpecificationData.specChar.length; i++) {
            String ID = TestProductSpecificationData.specChar[i][0].toString();
            logger.info("创建特征：" + "ID=" + ID + ",   " + TestProductSpecificationData.specChar[i][1].toString()
                    + "是否可配置：" + TestProductSpecificationData.specChar[i][7].toString());
            ProductSpecCharacteristic productSpecCharProcessor1 = null;
            if (Boolean.parseBoolean(TestProductSpecificationData.specChar[i][7].toString())) {
                productSpecCharProcessor1 = new ConfigurableProductSpecCharacteristic(ID,
                        TestProductSpecificationData.specChar[i][1].toString(),
                        TestProductSpecificationData.specChar[i][2].toString(),
                        (TimePeriod) TestProductSpecificationData.specChar[i][3],
                        TestProductSpecificationData.specChar[i][4].toString(),
                        (int) TestProductSpecificationData.specChar[i][5],
                        (int) TestProductSpecificationData.specChar[i][6]);
            } else {
                productSpecCharProcessor1 = new ProductSpecCharacteristic(ID,
                        TestProductSpecificationData.specChar[i][1].toString(),
                        TestProductSpecificationData.specChar[i][2].toString(),
                        (TimePeriod) TestProductSpecificationData.specChar[i][3],
                        TestProductSpecificationData.specChar[i][4].toString(),
                        (int) TestProductSpecificationData.specChar[i][5],
                        (int) TestProductSpecificationData.specChar[i][6]);
            }

            for (int j = 0; j < TestProductSpecificationData.specCharValue.length; j++) {
                if ((int) TestProductSpecificationData.specCharValue[j][0] == i) {
                    logger.info("      添加特征值：value=" + TestProductSpecificationData.specCharValue[j][5].toString()
                            + "， unitOfMeasure=" + TestProductSpecificationData.specCharValue[j][3].toString());
                    ProductSpecCharacteristicValue oneprocessorValue1 = new ProductSpecCharacteristicValue(
                            TestProductSpecificationData.specCharValue[j][1].toString(),
                            (boolean) TestProductSpecificationData.specCharValue[j][2],
                            TestProductSpecificationData.specCharValue[j][3].toString(),
                            (TimePeriod) TestProductSpecificationData.specCharValue[j][4],
                            TestProductSpecificationData.specCharValue[j][5].toString());
                    productSpecCharProcessor1.addValue(oneprocessorValue1);
                }
            }
            productSpecChars.add(productSpecCharProcessor1);
        }
        logger.info("建立特征关系：");
        for (int i = 0; i < TestProductSpecificationData.relateSpecChar.length; i++) {
            String srcId = TestProductSpecificationData.relateSpecChar[i][0].toString();
            String targetId = TestProductSpecificationData.relateSpecChar[i][1].toString();
            ProductSpecCharacteristic srcChar = this.getProdSpecCharById(srcId);
            ProductSpecCharacteristic targetChar = this.getProdSpecCharById(targetId);

            logger.info("    建立特征关系：srcCharName:" + srcChar.getName() + ",  targetCharName：" + targetChar.getName()
                    + ",   关联类型：" + TestProductSpecificationData.relateSpecChar[i][2].toString());
            srcChar.addRelatedCharacteristic(targetChar, TestProductSpecificationData.relateSpecChar[i][2].toString(),
                    Integer.parseInt(TestProductSpecificationData.relateSpecChar[i][3].toString()),
                    (TimePeriod) TestProductSpecificationData.relateSpecChar[i][4]);
        }
        logger.info("准备特征/特征值数据----------end");
    }

    @Test
    public void appleStore() throws Exception {

        // 创建规格
        ProductSpecification productSpecification1 = createProductSpecification(
                TestProductSpecificationData.specParameter, TestProductSpecificationData.one_charData);
        ProductSpecification productSpecification2 = createProductSpecification(
                TestProductSpecificationData.specParameter2, TestProductSpecificationData.two_charData);

        // 创建SimpleOffering
        ProductOffering offering1 = this.createSimpleProductOffering(productSpecification1,
                TestOfferingData.offeringData[0]);
        // 创建SimpleOffering
        ProductOffering offering2 = this.createSimpleProductOffering(productSpecification2,
                TestOfferingData.offeringData[1]);

        // 创建BundledOffering
        BundledProductOffering bundledOffering = this
                .createBundledProductOffering(TestOfferingData.bundledOfferingData[0]);

        // 给BundledOffering添加SubOffering
        bundledOffering.addSubOffering(offering1);
        bundledOffering.addSubOffering(offering2);

        // 创建catalog
        ProductCatalog catalog = this.createProductCatalog(TestOfferingData.offeringCalatlog[0]);

        // 发布商品到catalog
        TimePeriod validFor1 = new TimePeriod("2015-01-01 00:00:00", "2015-07-01 00:00:00");
        catalog.publishOffering(bundledOffering, validFor1);

        //
        ProductOffering[] productOfferings = catalog.getProductOffering(ProductConst.OFFERING_STATUS_ACTIVE);

        CommonUtils.printPropertyToJson(null, null, catalog);
        // CommonUtils.printPropertyToJson(productOfferings, null, null);

    }

    /**
     * 创建规格
     * 
     * @return
     * @throws Exception
     */
    public ProductSpecification createProductSpecification(Object[] specParameter, Object[][] charData)
            throws Exception {

        if (specParameter != null) {

            ProductSpecification productSpec = new AtomicProductSpecification(
                    TestProductSpecificationData.specParameter[0].toString(),
                    TestProductSpecificationData.specParameter[1].toString(),
                    TestProductSpecificationData.specParameter[2].toString(),
                    TestProductSpecificationData.specParameter[3].toString());
            logger.info("创建规格:" + productSpec.toString());
            logger.info("    添加特征/特征值:");
            for (int i = 0; i < charData.length; i++) {
                ProductSpecCharacteristic prodSpecChar = null;
                prodSpecChar = this.getCharByCharId(charData[i][0].toString());
                productSpec
                        .addCharacteristic(prodSpecChar, (boolean) charData[i][1], (boolean) charData[i][2],
                                (TimePeriod) charData[i][3], charData[i][4].toString(), charData[i][5].toString(),
                                (int) charData[i][6], (int) charData[i][7], (boolean) charData[i][8],
                                charData[i][9].toString());
                if (Boolean.parseBoolean(charData[i][10].toString())) {
                    ProductSpecCharacteristicValue[] values = this.getCharValue(prodSpecChar, (int[]) charData[i][11]);
                    if (values != null) {
                        for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values) {
                            productSpec.attachCharacteristicValue(prodSpecChar, productSpecCharacteristicValue, true,
                                    (TimePeriod) TestProductSpecificationData.specParameter[4]);
                        }
                    }
                }
                logger.info("    特征" + (i + 1) + "：");
                logger.info(productSpec.getProdSpecCharUse().get(i).toString());
                logger.info("    特征" + (i + 1) + "的所用特征值：");
                List<ProdSpecCharValueUse> prodSpecCharValueUseList = productSpec.getProdSpecCharUse().get(i)
                        .getProdSpecCharValueUse();
                if (prodSpecCharValueUseList == null || prodSpecCharValueUseList.size() == 0) {
                    logger.info("null");
                } else {
                    for (ProdSpecCharValueUse prodSpecCharValueUse : prodSpecCharValueUseList) {
                        logger.info(prodSpecCharValueUse.toString());
                    }
                }
            }

            return productSpec;
        }
        return null;
    }

    public ProductCatalog createProductCatalog(String[] catalogData) {
        TimePeriod validFor = new TimePeriod(catalogData[3], null);
        ProductCatalog macBookProproductCatalog = new ProductCatalog(catalogData[0], catalogData[1], catalogData[2],
                validFor);
        return macBookProproductCatalog;
    }

    public SimpleProductOffering createSimpleProductOffering(ProductSpecification productSpecification,
            String[] offeringData) {
        TimePeriod validFor = new TimePeriod(offeringData[3], null);
        SimpleProductOffering simpleProductOffering = new SimpleProductOffering(offeringData[0], offeringData[1],
                validFor, offeringData[2], productSpecification, offeringData[4]);
        return simpleProductOffering;
    }

    public BundledProductOffering createBundledProductOffering(String[] bundledofferingData) {
        TimePeriod validFor = new TimePeriod(bundledofferingData[3], null);
        BundledProductOffering bundledProductOffering = new BundledProductOffering(bundledofferingData[0],
                bundledofferingData[1], validFor, bundledofferingData[2], "");
        return bundledProductOffering;
    }

    public ProductSpecCharacteristic getCharByCharId(String id) {
        ProductSpecCharacteristic prodSpecChar = null;
        for (int i = 0; i < productSpecChars.size(); i++) {
            prodSpecChar = productSpecChars.get(i);
            if (id.equals(prodSpecChar.getID())) {
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

    public ProductSpecCharacteristic getProdSpecCharById(String id) {
        if (productSpecChars != null) {
            for (ProductSpecCharacteristic productSpecCharacteristic : productSpecChars) {
                if (id.equals(productSpecCharacteristic.getID())) {
                    return productSpecCharacteristic;
                }
            }
        }
        return null;
    }
}
