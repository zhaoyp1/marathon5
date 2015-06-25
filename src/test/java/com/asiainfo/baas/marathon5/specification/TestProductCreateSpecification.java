package com.asiainfo.baas.marathon5.specification;

import java.util.List;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class TestProductCreateSpecification {

	List<ProductSpecCharacteristic> productSpecChars;
	public void createProductSpecChar(){
		TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		ProductSpecCharacteristic productSpecCharProcessor=new ProductSpecCharacteristic("1","processor(´¦ÀíÆ÷)", "number",validFor , "unique",1,1);
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
		productSpecChars.add(productSpecCharSizeAndWeight);
		productSpecChars.add(productSpecCharProcessor);
	}
}
