package com.asiainfo.baas.marathon5.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.Money;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProdSpecCharValueUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon5.apple.TestProductSpecificationDate;
import com.asiainfo.baas.marathon5.common.CommonUtils;
import com.asiainfo.baas.marathon5.common.ProdSpecCharParameter;

public class TestProductCreateSpecification {

	public  List<ProductSpecCharacteristic> productSpecChars;
	@Before
	public void createProductSpecChar(){
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
		//specParameter=new Object[]{"mac-13","13-inch MacBook Pro","apple","in_active",validFor,null,"1.0.0","","min",109};
		
	}
	public ProductSpecCharacteristic getCharByCharName(String name){
		ProductSpecCharacteristic prodSpecChar=null;
		for (int i = 0; i < productSpecChars.size(); i++) {
			prodSpecChar=productSpecChars.get(i);
			if(name.equals(prodSpecChar.getName())){
				return prodSpecChar;
			}
		}
		return null;
		 
		
	}
	
	public ProductSpecCharacteristicValue[] getCharValue(ProductSpecCharacteristic characteristic,int[] ids){
		if(ids!=null){
			 List<ProductSpecCharacteristicValue> productValues=characteristic.getProductSpecCharacteristicValue();
			 List<ProductSpecCharacteristicValue> prodSpecChars=new ArrayList<ProductSpecCharacteristicValue>();
			 
			 for (int id  : ids) {
				for (int i=0 ; i<productValues.size() ; i++) {
					if(id==i){
						prodSpecChars.add(productValues.get(i));
						break;
					}
				}
			 }	
			 return (ProductSpecCharacteristicValue[])prodSpecChars.toArray(new ProductSpecCharacteristicValue[prodSpecChars.size()]);
		}
		return null;
		
	}
	
	
	//[] {productNumber,name,brand,lifecyleStatus,validFor,parameters,version,versionDescription,cost,amount}
	@Test
	public void createProductSpecificationTest() throws Exception{
		if(TestProductSpecificationDate.specParameter!=null){
				ProductSpecification productSpec=new AtomicProductSpecification(TestProductSpecificationDate.specParameter[0].toString(),TestProductSpecificationDate.specParameter[1].toString(),TestProductSpecificationDate.specParameter[2].toString(),TestProductSpecificationDate.specParameter[3].toString());
				for (int i=0 ; i<TestProductSpecificationDate.one_charData.length ; i++) {
					ProductSpecCharacteristic prodSpecChar=null;
						prodSpecChar=this.getCharByCharName(TestProductSpecificationDate.one_charData[i][0].toString());
						productSpec.addCharacteristic(prodSpecChar, (boolean)TestProductSpecificationDate.one_charData[i][1], (boolean)TestProductSpecificationDate.one_charData[i][2], (TimePeriod)TestProductSpecificationDate.one_charData[i][3]);
						if(Boolean.parseBoolean(TestProductSpecificationDate.one_charData[i][4].toString())){
							ProductSpecCharacteristicValue[] values=this.getCharValue(prodSpecChar,(int[])TestProductSpecificationDate.one_charData[i][5]);
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
				
				CommonUtils.printPropertyToJson(null,null,productSpec);
		} 
		
	}
	@After
	public void printSpecInfo(){
		
	}
}

