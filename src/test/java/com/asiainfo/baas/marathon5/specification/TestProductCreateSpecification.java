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
import com.asiainfo.baas.marathon5.common.ProdSpecCharParameter;

public class TestProductCreateSpecification {

	public  List<ProductSpecCharacteristic> productSpecChars;
	
	public Object[] specParameter;
	@Before
	public void createProductSpecChar(){
		TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		ProductSpecCharacteristic productSpecCharProcessor=new ProductSpecCharacteristic("1","processor(处理器)", "number",validFor , "unique",1,1);
		ProductSpecCharacteristic productSpecCharSizeAndWeight=new ProductSpecCharacteristic("1","size and weight", "text",validFor , "",1,1);
		ProductSpecCharacteristic productSpecCharHeight=new ProductSpecCharacteristic("1","Height", "number",validFor , "",1,1);
		ProductSpecCharacteristic productSpecCharWidth=new ProductSpecCharacteristic("1","Width", "number",validFor , "",1,1);
		ProductSpecCharacteristic productSpecCharDepth=new ProductSpecCharacteristic("1","Depth", "number",validFor , "",1,1);
		ProductSpecCharacteristic productSpecCharWeight=new ProductSpecCharacteristic("1","Weight", "number",validFor , "",1,1);
		ProductSpecCharacteristicValue oneprocessorValue=new ProductSpecCharacteristicValue("number",true,"GHz",validFor,"2.9");
		ProductSpecCharacteristicValue twoprocessorValue=new ProductSpecCharacteristicValue("number",false,"GHz",validFor,"2.7");
		ProductSpecCharacteristicValue heightValue=new ProductSpecCharacteristicValue("number",false,"cm",validFor,"1.8");
		ProductSpecCharacteristicValue widthValue=new ProductSpecCharacteristicValue("number",false,"cm",validFor,"31.4");
		ProductSpecCharacteristicValue depthValue=new ProductSpecCharacteristicValue("number",false,"cm",validFor,"21.9");
		ProductSpecCharacteristicValue weightValue=new ProductSpecCharacteristicValue("number",false,"kg",validFor,"1.58");

		productSpecCharProcessor.addValue(oneprocessorValue);
		productSpecCharProcessor.addValue(twoprocessorValue);
		productSpecCharHeight.addValue(heightValue);
		productSpecCharWidth.addValue(widthValue);
		productSpecCharDepth.addValue(depthValue);
		productSpecCharWeight.addValue(weightValue);
		productSpecCharSizeAndWeight.addLeafCharacteristic(productSpecCharHeight, validFor);
		productSpecCharSizeAndWeight.addLeafCharacteristic(productSpecCharWidth, validFor);
		productSpecCharSizeAndWeight.addLeafCharacteristic(productSpecCharDepth, validFor);
		productSpecCharSizeAndWeight.addLeafCharacteristic(productSpecCharWeight, validFor);
		productSpecChars=new ArrayList<ProductSpecCharacteristic>();
		productSpecChars.add(productSpecCharSizeAndWeight);
		productSpecChars.add(productSpecCharHeight);
		productSpecChars.add(productSpecCharWidth);
		productSpecChars.add(productSpecCharDepth);
		productSpecChars.add(productSpecCharWeight);
		productSpecChars.add(productSpecCharProcessor);
		//{productNumber,name,brand,lifecyleStatus,validFor,parameters,version,versionDescription,cost,amount}
		ProdSpecCharParameter[] charParameter=new ProdSpecCharParameter[2];
		charParameter[0]=new ProdSpecCharParameter();
		charParameter[0].setName("size and weight");
		charParameter[0].setCanBeOveridden(true);
		charParameter[0].setPackage(true);
		charParameter[0].setContainValue(false);
		charParameter[0].setValidFor(validFor);
		charParameter[0].setValueIndex(null);
		charParameter[1]=new ProdSpecCharParameter();
		charParameter[1].setName("Height");
		charParameter[1].setCanBeOveridden(true);
		charParameter[1].setPackage(true);
		charParameter[1].setContainValue(true);
		charParameter[1].setValidFor(validFor);
		charParameter[1].setValueIndex(new int[]{0});
		specParameter=new Object[]{"mac-13","13-inch MacBook Pro","apple","in_active",validFor,charParameter,"1.0.0","","min",109};
		
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
		if(specParameter!=null){
				ProductSpecification productSpec=new AtomicProductSpecification(specParameter[0].toString(),specParameter[1].toString(),specParameter[2].toString(),specParameter[3].toString());
				ProdSpecCharParameter[]	charParameter=(ProdSpecCharParameter[])specParameter[5];
				for (ProdSpecCharParameter prodCharParameter : charParameter) {
					ProductSpecCharacteristic prodSpecChar=null;
						prodSpecChar=this.getCharByCharName(prodCharParameter.getName());
						productSpec.addCharacteristic(prodSpecChar, prodCharParameter.isCanBeOveridden(), prodCharParameter.isPackage(), prodCharParameter.getValidFor());
						if(prodCharParameter.isContainValue()){
							ProductSpecCharacteristicValue[] values=this.getCharValue(prodSpecChar,prodCharParameter.getValueIndex());
							if(values!=null){
								for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values) {
									productSpec.attachCharacteristicValue(prodSpecChar,productSpecCharacteristicValue, true, (TimePeriod)specParameter[4]);
								}
							}	
						} 
				 }
				
				//添加成本
				Money cost=new Money(specParameter[8].toString(),Long.parseLong(specParameter[9].toString()));
				productSpec.addCost(cost, (TimePeriod)specParameter[4]);
				productSpec.setVersion(specParameter[6].toString(), specParameter[7].toString(),new Date(), (TimePeriod)specParameter[4]);
		} 
		
	}
	@After
	public void printSpecInfo(){
		
	}
}

