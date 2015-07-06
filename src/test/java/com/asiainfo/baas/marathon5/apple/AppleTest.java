package com.asiainfo.baas.marathon5.apple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.BundledProductOffering;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;
import com.asiainfo.baas.marathon.offering.catalog.ProductCatalog;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.CompositeProductSpecification;
import com.asiainfo.baas.marathon.specification.ConfigurableProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProdSpecCharValueUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon.specification.ProductSpecification;

public class AppleTest {

    private Logger logger = Logger.getLogger(AppleTest.class);

    private static List<ProductSpecCharacteristic> productSpecChars;

    @Before
    public void createProductSpecChar() {

        logger.info("准备特征/特征值数据----------start");
        productSpecChars = new ArrayList<ProductSpecCharacteristic>();
        for (int i = 0; i < TestProductSpecificationData.specChar.length; i++) {
            String ID = TestProductSpecificationData.specChar[i][0].toString();
            logger.info("创建特征：" + "ID=" + ID + ",   name=" + TestProductSpecificationData.specChar[i][1].toString()
                    + "  是否可配置：" + TestProductSpecificationData.specChar[i][7].toString());
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
                if (ID.equals(TestProductSpecificationData.specCharValue[j][0].toString())) {
                    logger.info("      添加特征值：value="
                            + TestProductSpecificationData.specCharValue[j][5].toString()
                            + (TestProductSpecificationData.specCharValue[j][3].toString().equals("") ? ""
                                    : "， unitOfMeasure=") + TestProductSpecificationData.specCharValue[j][3].toString());
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

        // 创建规格（13’’MBP）
        ProductSpecification productSpecification1 = createProductSpecification(
                TestProductSpecificationData.specParameter, TestProductSpecificationData.one_charData);
        // 创建规格（操作系统）
        ProductSpecification productSpecification2 = createProductSpecification(
                TestProductSpecificationData.specParameter2, TestProductSpecificationData.two_charData);
        // 创建规格（保修）
        ProductSpecification productSpecification3 = createProductSpecification(
                TestProductSpecificationData.specParameter3, TestProductSpecificationData.three_charData);

        // 创建复合规格
        CompositeProductSpecification productSpecification4 = (CompositeProductSpecification) createProductSpecification(
                TestProductSpecificationData.specParameter4, new Object[][] {});

        // 添加子规格
        this.addSubspec(productSpecification4, productSpecification1, productSpecification2, productSpecification3);
        // 打印复合规格
        logger.info("复合规格整体内容：" + productSpecification4.toString());
        
        

    }

    /**
     * 创建规格
     * 
     * @return
     * @throws Exception
     */
    public ProductSpecification createProductSpecification(Object[] specParameter, Object[][] charData)
            throws Exception {
        ProductSpecification productSpec = null;
        if (specParameter != null) {
            if ("AtomicProductSpecification".equals(specParameter[10].toString())) {
                productSpec = new AtomicProductSpecification(specParameter[0].toString(), specParameter[1].toString(),
                        specParameter[2].toString(), specParameter[3].toString());
            } else {
                productSpec = new CompositeProductSpecification(specParameter[0].toString(),
                        specParameter[1].toString(), specParameter[2].toString(), specParameter[3].toString());
            }

            logger.info("创建规格:" + productSpec.toString());
            if (charData != null && charData.length > 0) {

                logger.info("    添加特征/特征值:");
            } else {
                logger.info("    无特征/特征值");

            }
            for (int i = 0; i < charData.length; i++) {

                ProductSpecCharacteristic prodSpecChar = null;
                prodSpecChar = this.getCharByCharId(charData[i][0].toString());
                productSpec
                        .addCharacteristic(prodSpecChar, (boolean) charData[i][1], (boolean) charData[i][2],
                                (TimePeriod) charData[i][3], charData[i][4].toString(), charData[i][5].toString(),
                                (int) charData[i][6], (int) charData[i][7], (boolean) charData[i][8],
                                charData[i][9].toString());

                if (Boolean.parseBoolean(charData[i][10].toString())) {
                    ProductSpecCharacteristicValue[] values = this.getCharValue(prodSpecChar,
                            (String[]) charData[i][11]);
                    if (values != null) {
                        for (int j = 0; j < values.length; j++) {
                            //TODO name未指定
                            productSpec.attachCharacteristicValue(prodSpecChar, "",values[j],
                                    ((boolean[]) charData[i][12])[j], (TimePeriod) specParameter[4]);
                        }
                    }
                }
                Iterator<ProductSpecCharUse> productSpecCharUses = productSpec.getProdSpecCharUse().iterator();
                ProductSpecCharUse use = null;
                while (productSpecCharUses.hasNext()) {
                    use = productSpecCharUses.next();
                    if (prodSpecChar.equals(use.getProdSpecChar())) {
                        break;
                    }

                }
                logger.info("    特征" + (i + 1) + "（" + use.getProdSpecChar().getName() + "）" + "：");
                logger.info(use.toString());
                logger.info("    特征" + (i + 1) + "的所用特征值：");
                Set<ProdSpecCharValueUse> prodSpecCharValueUseSet = use.getProdSpecCharValueUse();
                if (prodSpecCharValueUseSet == null || prodSpecCharValueUseSet.size() == 0) {
                    logger.info("null");
                } else {
                    for (ProdSpecCharValueUse prodSpecCharValueUse : prodSpecCharValueUseSet) {
                        logger.info(prodSpecCharValueUse.toString());
                    }
                }
            }

            return productSpec;
        }
        return null;
    }

    /**
     * 添加子规格
     * 
     * @return
     * @throws Exception
     */
    public void addSubspec(CompositeProductSpecification parentSpec, ProductSpecification... productSpecs)
            throws Exception {
        logger.info("**********添加子规格   start*************");
        logger.info("复合规格：" + parentSpec.getName());
        if (productSpecs != null && productSpecs.length > 0) {
            for (int i = 0; i < productSpecs.length; i++) {
                logger.info("    子规格" + i + "：" + productSpecs[i].getName());
                parentSpec.addSubProdSpec(productSpecs[i]);
            }
        }
        logger.info("**********添加子规格   end*************");
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

    public ProductSpecCharacteristicValue[] getCharValue(ProductSpecCharacteristic characteristic, String[] values) {
        if (values != null) {
            Set<ProductSpecCharacteristicValue> productValues = characteristic.getProductSpecCharacteristicValue();
            List<ProductSpecCharacteristicValue> prodSpecChars = new ArrayList<ProductSpecCharacteristicValue>();

            for (String value : values) {
                for (ProductSpecCharacteristicValue productValue : productValues) {
                    if (value.equals(productValue.getValue())) {
                        prodSpecChars.add(productValue);
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
