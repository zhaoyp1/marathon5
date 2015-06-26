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
import com.asiainfo.baas.marathon5.common.ProdSpecCharParameter;

public class TestApple {

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
	        ProductSpecification productSpecification1 = createProductSpecification(TestProductSpecificationDate.specParameter,TestProductSpecificationDate.one_charData);
	        ProductSpecification productSpecification2 = createProductSpecification(TestProductSpecificationDate.specParameter2,TestProductSpecificationDate.two_charData);

	        // 创建SimpleOffering
	        ProductOffering offering1 = this.createSimpleProductOffering(productSpecification1,TestOfferingData.offeringData[0]);
	        // 创建SimpleOffering
	        ProductOffering offering2 = this.createSimpleProductOffering(productSpecification2,TestOfferingData.offeringData[1]);

	        // 创建BundledOffering
	        BundledProductOffering bundledOffering = this.createBundledProductOffering(TestOfferingData.bundledOfferingData[0]);
	       
	        //给BundledOffering添加SubOffering
	        bundledOffering.addSubOffering(offering1);
	        bundledOffering.addSubOffering(offering2);
	        
	        // 两个原子Offering是互斥关系
	        TimePeriod validFor = new TimePeriod("2015-01-01 00:00:00", "2015-07-01 00:00:00");
	        offering1.addRelatedOffering(offering2, ProductConst.RELATIONSHIP_TYPE_EXCLUSIVITY,
	                validFor);
	        
	        // 创建catalog
	        ProductCatalog catalog = this.createProductCatalog(TestOfferingData.offeringCalatlog[0]);
	        
	        //发布商品到catalog
	        TimePeriod validFor1 = new TimePeriod("2015-01-01 00:00:00", "2015-07-01 00:00:00");
            catalog.publishOffering(bundledOffering, validFor1);
	       
	        //
	        ProductOffering[] productOfferings = catalog.getProductOffering(ProductConst.OFFERING_STATUS_ACTIVE);

	        CommonUtils.printPropertyToJson(null, null, catalog);
	        //CommonUtils.printPropertyToJson(productOfferings, null, null);

	    }

	    /**
	     * 创建两个规格
	     * 
	     * @return
	     * @throws Exception
	     */
	    public ProductSpecification createProductSpecification(Object[] specParameter,Object[][] charData ) throws Exception {

	    	if(specParameter!=null){
	    		ProductSpecification productSpec=new AtomicProductSpecification(TestProductSpecificationDate.specParameter[0].toString(),TestProductSpecificationDate.specParameter[1].toString(),TestProductSpecificationDate.specParameter[2].toString(),TestProductSpecificationDate.specParameter[3].toString());
				for (int i=0 ; i<charData.length ; i++) {
					ProductSpecCharacteristic prodSpecChar=null;
						prodSpecChar=this.getCharByCharName(charData[i][0].toString());
						productSpec.addCharacteristic(prodSpecChar, (boolean)charData[i][1], (boolean)charData[i][2], (TimePeriod)charData[i][3]);
						if(Boolean.parseBoolean(charData[i][4].toString())){
							ProductSpecCharacteristicValue[] values=this.getCharValue(prodSpecChar,(int[])charData[i][5]);
							if(values!=null){
								for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values) {
									productSpec.attachCharacteristicValue(prodSpecChar,productSpecCharacteristicValue, true, (TimePeriod)TestProductSpecificationDate.specParameter[4]);
								}
							}	
						} 
				 }
				
				//添加成本
				Money cost=new Money(TestProductSpecificationDate.specParameter[8].toString(),Long.parseLong(TestProductSpecificationDate.specParameter[9].toString()));
				productSpec.addCost(cost, (TimePeriod)TestProductSpecificationDate.specParameter[4]);
				productSpec.setVersion(TestProductSpecificationDate.specParameter[6].toString(), TestProductSpecificationDate.specParameter[7].toString(),new Date(), (TimePeriod)TestProductSpecificationDate.specParameter[4]);
				
				
				 				
	        return productSpec;
	    }
	    	return null;
	   }

	    public ProductCatalog createProductCatalog(String[] catalogData) {
	        TimePeriod validFor = new TimePeriod(catalogData[3], null);
	        ProductCatalog macBookProproductCatalog = new ProductCatalog(catalogData[0], catalogData[1], catalogData[2], validFor);
	        return macBookProproductCatalog;
	    }

	    public SimpleProductOffering createSimpleProductOffering(ProductSpecification productSpecification,String[] offeringData) {
	        TimePeriod validFor = new TimePeriod(offeringData[3], null);
	        SimpleProductOffering simpleProductOffering = new SimpleProductOffering(offeringData[0], offeringData[1],
	                validFor, offeringData[2], productSpecification, offeringData[4]);
	        return simpleProductOffering;
	    }
	    
	    public BundledProductOffering createBundledProductOffering(String[] bundledofferingData) {
	        TimePeriod validFor = new TimePeriod(bundledofferingData[3], null);
	        BundledProductOffering bundledProductOffering = new BundledProductOffering(bundledofferingData[0], bundledofferingData[1],
	                validFor, bundledofferingData[2],"");
	        return bundledProductOffering;
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


