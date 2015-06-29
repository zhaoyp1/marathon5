package com.asiainfo.baas.marathon5.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.Money;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon5.apple.TestProductSpecificationData;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class TestProductCreateSpecification {

	public  List<ProductSpecCharacteristic> productSpecChars;
	private static Logger logger = Logger.getLogger(TestProductCreateSpecification.class);
	@Before
	public void createProductSpecChar(){
		 	productSpecChars = new ArrayList<ProductSpecCharacteristic>();
		 	for (int i = 0; i < TestProductSpecificationData.specChar.length; i++) {
		 		
		 		ProductSpecCharacteristic productSpecCharProcessor1 = new ProductSpecCharacteristic(TestProductSpecificationData.specChar[i][0].toString(), TestProductSpecificationData.specChar[i][1].toString(),
		 				TestProductSpecificationData.specChar[i][2].toString(), (TimePeriod)TestProductSpecificationData.specChar[i][3], TestProductSpecificationData.specChar[i][4].toString(), (int)TestProductSpecificationData.specChar[i][5], (int)TestProductSpecificationData.specChar[i][6]);
		 		for(int j=0 ;j<TestProductSpecificationData.specCharValue.length;j++){
		 			if((int)TestProductSpecificationData.specCharValue[j][0]==i){
		 				ProductSpecCharacteristicValue oneprocessorValue1 = new ProductSpecCharacteristicValue(TestProductSpecificationData.specCharValue[j][1].toString(), (boolean)TestProductSpecificationData.specCharValue[j][2], TestProductSpecificationData.specCharValue[j][3].toString(),
		 		                (TimePeriod)TestProductSpecificationData.specCharValue[j][4], TestProductSpecificationData.specCharValue[j][5].toString()); 
		 				productSpecCharProcessor1.addValue(oneprocessorValue1);
		 			}
		 		}
		 		productSpecChars.add(productSpecCharProcessor1);
			}
		 	
		 	for(int i=0 ; i<TestProductSpecificationData.relateSpecChar.length ; i++){
		 		String srcId=TestProductSpecificationData.relateSpecChar[i][0].toString();
		 		String targetId=TestProductSpecificationData.relateSpecChar[i][1].toString();
		 		ProductSpecCharacteristic srcChar= this.getProdSpecCharById(srcId);
		 		ProductSpecCharacteristic targetChar = this.getProdSpecCharById(targetId);
		 		srcChar.addRelatedCharacteristic(targetChar, TestProductSpecificationData.relateSpecChar[i][2].toString(), Integer.parseInt( TestProductSpecificationData.relateSpecChar[i][3].toString()), (TimePeriod)TestProductSpecificationData.relateSpecChar[i][4]);
		 	}
	}
	public ProductSpecCharacteristic getProdSpecCharById(String id){
		if(productSpecChars!=null){
			for (ProductSpecCharacteristic productSpecCharacteristic : productSpecChars) {
				if(id.equals(productSpecCharacteristic.getID())){
					return productSpecCharacteristic;
				}
			}
		}
		return null;
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
		logger.info("开始");
		if(TestProductSpecificationData.specParameter!=null){
				ProductSpecification productSpec=new AtomicProductSpecification(TestProductSpecificationData.specParameter[0].toString(),TestProductSpecificationData.specParameter[1].toString(),TestProductSpecificationData.specParameter[2].toString(),TestProductSpecificationData.specParameter[3].toString());
				for (int i=0 ; i<TestProductSpecificationData.one_charData.length ; i++) {
					ProductSpecCharacteristic prodSpecChar=null;
						prodSpecChar=this.getCharByCharName(TestProductSpecificationData.one_charData[i][0].toString());
						productSpec.addCharacteristic(prodSpecChar, (boolean)TestProductSpecificationData.one_charData[i][1], (boolean)TestProductSpecificationData.one_charData[i][2], (TimePeriod)TestProductSpecificationData.one_charData[i][3],TestProductSpecificationData.one_charData[i][6].toString(),TestProductSpecificationData.one_charData[i][7].toString(),(int)TestProductSpecificationData.one_charData[i][8],(int)TestProductSpecificationData.one_charData[i][9],(boolean)TestProductSpecificationData.one_charData[i][10],TestProductSpecificationData.one_charData[i][11].toString());
						if(Boolean.parseBoolean(TestProductSpecificationData.one_charData[i][4].toString())){
							ProductSpecCharacteristicValue[] values=this.getCharValue(prodSpecChar,(int[])TestProductSpecificationData.one_charData[i][5]);
							if(values!=null){
								for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values) {
									productSpec.attachCharacteristicValue(prodSpecChar,productSpecCharacteristicValue, true, (TimePeriod)TestProductSpecificationData.specParameter[4]);
								}
							}	
						} 
				 }
				
				//添加成本
				Money cost=new Money(TestProductSpecificationData.specParameter[8].toString(),Long.parseLong(TestProductSpecificationData.specParameter[9].toString()));
				productSpec.addCost(cost, (TimePeriod)TestProductSpecificationData.specParameter[4]);
				productSpec.setVersion(TestProductSpecificationData.specParameter[6].toString(), TestProductSpecificationData.specParameter[7].toString(),new Date(), (TimePeriod)TestProductSpecificationData.specParameter[4]);
				
				CommonUtils.printPropertyToJson(null,null,productSpec);
		} 
		
	}
	@After
	public void printSpecInfo(){
		
	}
}

