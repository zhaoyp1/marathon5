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
						productSpec.addCharacteristic(prodSpecChar, (boolean)TestProductSpecificationDate.one_charData[i][1], (boolean)TestProductSpecificationDate.one_charData[i][2], (TimePeriod)TestProductSpecificationDate.one_charData[i][3],TestProductSpecificationDate.one_charData[i][6].toString(),TestProductSpecificationDate.one_charData[i][7].toString(),(int)TestProductSpecificationDate.one_charData[i][8],(int)TestProductSpecificationDate.one_charData[i][9],(boolean)TestProductSpecificationDate.one_charData[i][10],TestProductSpecificationDate.one_charData[i][11].toString());
						if(Boolean.parseBoolean(TestProductSpecificationDate.one_charData[i][4].toString())){
							ProductSpecCharacteristicValue[] values=this.getCharValue(prodSpecChar,(int[])TestProductSpecificationDate.one_charData[i][5]);
							if(values!=null){
								for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values) {
									productSpec.attachCharacteristicValue(prodSpecChar,productSpecCharacteristicValue, true, (TimePeriod)TestProductSpecificationDate.specParameter[4]);
								}
							}	
						} 
				 }
				
				//Ìí¼Ó³É±¾
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

