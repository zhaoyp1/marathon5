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

public class TestAppleStoreOffering {
	private static List<ProductSpecCharacteristic> productSpecChars;
	 @Before
	    public void createProductSpecChar() {
	    	productSpecChars = new ArrayList<ProductSpecCharacteristic>();
		 	for (int i = 0; i < TestProductSpecificationDate.specChar.length; i++) {
		 		
		 		ProductSpecCharacteristic productSpecCharProcessor1 = new ProductSpecCharacteristic(TestProductSpecificationDate.specChar[i][0].toString(), TestProductSpecificationDate.specChar[i][1].toString(),
		 				TestProductSpecificationDate.specChar[i][2].toString(), (TimePeriod)TestProductSpecificationDate.specChar[i][3], TestProductSpecificationDate.specChar[i][4].toString(), (int)TestProductSpecificationDate.specChar[i][5], (int)TestProductSpecificationDate.specChar[i][6]);
		 		for(int j=0 ;j<TestProductSpecificationDate.specCharValue.length;j++){
		 			if((int)TestProductSpecificationDate.specCharValue[j][0]==i){
		 				ProductSpecCharacteristicValue oneprocessorValue1 = new ProductSpecCharacteristicValue(TestProductSpecificationDate.specCharValue[j][1].toString(), (boolean)TestProductSpecificationDate.specCharValue[j][2], TestProductSpecificationDate.specCharValue[j][3].toString(),
		 		                (TimePeriod)TestProductSpecificationDate.specCharValue[j][4], TestProductSpecificationDate.specCharValue[j][5].toString()); 
		 				productSpecCharProcessor1.addValue(oneprocessorValue1);
		 			}
		 		}
		 		productSpecChars.add(productSpecCharProcessor1);
			}

	    }

    /**
     * 创建三个offering，一个复合offering，两个原子offering
     * @throws Exception 
     */
    @Test
    public void createOffering() throws Exception {
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

        CommonUtils.printPropertyToJson(null, null, bundledOffering);

    }

    @Test
    public void createCatalog() {

        ProductCatalog macBookProproductCatalog = this.createProductCatalog(TestOfferingData.offeringCalatlog[0]);
        CommonUtils.printPropertyToJson(null, null, macBookProproductCatalog);

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
					
					productSpec.addCharacteristic(prodSpecChar, (boolean)charData[i][1], (boolean)charData[i][2], (TimePeriod)charData[i][3],charData[i][6].toString(),charData[i][7].toString(),(int)charData[i][8],(int)charData[i][9],(boolean)charData[i][10],charData[i][11].toString());
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
