package com.asiainfo.baas.marathon5.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

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

public class TestProductCreateSpecification {

	static List<ProductSpecCharacteristic> productSpecChars;
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
	}
	
	@Test
	public void createProductSpecification() throws Exception{
		
		TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		ProductSpecification productSpec=new AtomicProductSpecification("mac-13","13-inch MacBook Pro","apple","in_active");
		ProductSpecCharacteristic prodSpecChar=this.getCharByCharName("size and weight");
		productSpec.addCharacteristic(prodSpecChar, true, true, validFor);
		prodSpecChar=this.getCharByCharName("Height");
		productSpec.addCharacteristic(prodSpecChar, true, true, validFor);
		ProductSpecCharacteristicValue[] values=this.getCharValue(prodSpecChar,new int[]{0});
		if(values!=null){
			for (ProductSpecCharacteristicValue productSpecCharacteristicValue : values) {
				productSpec.attachCharacteristicValue(prodSpecChar,productSpecCharacteristicValue, true, validFor);
			}
		}
		//添加成本
		Money cost=new Money("min",109);
		productSpec.addCost(cost, validFor);
		productSpec.setVersion("1.0.0", "",new Date(), validFor);
		 
//		System.out.println(JSONObject.fromObject(productSpec).toString());
		System.out.print("规格信息:");
		System.out.println("name:"+productSpec.getName()+"  ,   brand:"+productSpec.getProductNumber()+""+productSpec.getBrand());
		
		List<ProductSpecCharUse> productSpecCharUses=productSpec.getProdSpecCharUse();
		for (ProductSpecCharUse productSpecCharUse : productSpecCharUses) {
			ProductSpecCharacteristic useprodSpecChar=productSpecCharUse.getProdSpecChar();
			System.out.println("特征：");
			System.out.println("name:"+useprodSpecChar.getName()+"  ,   valueType:"+useprodSpecChar.getValueType());
			List<ProdSpecCharValueUse> valueUses=productSpecCharUse.getProdSpecCharValueUse();
			if(valueUses!=null){
				for (ProdSpecCharValueUse prodSpecCharValueUse : valueUses) {
					ProductSpecCharacteristicValue charValue=prodSpecCharValueUse.getProdSpecCharValue();
					System.out.println("特征值：");	

					if(charValue.getValue()!=null){
						System.out.println(" value:"+charValue.getValueFrom());	

					}else{
						System.out.println(" valueFrom:"+charValue.getValueFrom()+" ,   valueTo: "+charValue.getValueTo());	
					}
					
				}
			}
			
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
	
}
