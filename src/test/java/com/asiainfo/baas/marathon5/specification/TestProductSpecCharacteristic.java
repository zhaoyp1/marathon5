package com.asiainfo.baas.marathon5.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon5.common.CommonUtils;


public class TestProductSpecCharacteristic {

	@Test
	public void  addValue(){
		 TimePeriod validFor = new TimePeriod();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
	        try {
	            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
	            validFor.endDateTime = format.parse("2015-07-21 23:59:59");
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
		 ProductSpecCharacteristic productSpecification=new ProductSpecCharacteristic("1","颜色", "",validFor , "",1,6,true, "", "");
		 ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue("",true,"",validFor,"黑色");
		 productSpecification.addValue(value);
	}
	
	@Test
	public void getValue(){
		TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-07-21 23:59:59");
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","颜色", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue("",true,"",validFor,"黑色");
			productSpecCharacteristic.addValue(value);
			CommonUtils.printProperty(productSpecCharacteristic.getValue(format.parse("2015-07-20 23:59:59")),null,null);
        } catch (ParseException e) {
            e.printStackTrace();
        }

	}
	@Test
	public void setDefaultValue(){
		TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-07-21 23:59:59");
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","颜色", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue("",true,"",validFor,"黑色");
			productSpecCharacteristic.setDefaultValue(value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	@Test
	public void getDefaultValue(){
		TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-07-21 23:59:59");
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","颜色", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue("",true,"",validFor,"黑色");
			productSpecCharacteristic.setDefaultValue(value);
			ProductSpecCharacteristicValue defaultValue=productSpecCharacteristic.getDefaultValue();
			System.out.println(defaultValue.getValue());
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	public void setLeafValue(){
		TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-07-21 23:59:59");
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","尺寸", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subProductSpecCharLength=new ProductSpecCharacteristic("1","长", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subproductSpecCharWidth=new ProductSpecCharacteristic("1","宽", "",validFor , "",1,6,true, "", "");

			productSpecCharacteristic.addLeafCharacteristic(subProductSpecCharLength, validFor);
			productSpecCharacteristic.addLeafCharacteristic(subproductSpecCharWidth, validFor);
			 
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void getLeafValue(){
		TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-07-21 23:59:59");
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","尺寸", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subProductSpecCharLength=new ProductSpecCharacteristic("1","长", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subproductSpecCharWidth=new ProductSpecCharacteristic("1","宽", "",validFor , "",1,6,true, "", "");

			productSpecCharacteristic.addLeafCharacteristic(subProductSpecCharLength, validFor);
			productSpecCharacteristic.addLeafCharacteristic(subproductSpecCharWidth, validFor);
			ProductSpecCharacteristic[] subProdSpecChars=productSpecCharacteristic.getLeafCharacteristic();
			for (ProductSpecCharacteristic subProdSpecChar : subProdSpecChars) {
				System.out.println(subProdSpecChar.getName());
			}
			ProductSpecCharacteristic[] relatedProdSpecChars=productSpecCharacteristic.getRelatedCharacteristic("aggregation");
			for (ProductSpecCharacteristic relatedProdSpecChar : relatedProdSpecChars) {
				System.out.println(relatedProdSpecChar.getName());
			}
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	
	@Test
	public void getRelatedValue(){
		TimePeriod validFor = new TimePeriod();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        
        try {
            validFor.startDateTime = format.parse("2015-02-03 12:00:00");
            validFor.endDateTime = format.parse("2015-07-21 23:59:59");
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","尺寸", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subProductSpecCharLength=new ProductSpecCharacteristic("1","长", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subproductSpecCharWidth=new ProductSpecCharacteristic("1","宽", "",validFor , "",1,6,true, "", "");

			productSpecCharacteristic.addLeafCharacteristic(subProductSpecCharLength, validFor);
			productSpecCharacteristic.addLeafCharacteristic(subproductSpecCharWidth, validFor);
			 
			ProductSpecCharacteristic[] relatedProdSpecChars=productSpecCharacteristic.getRelatedCharacteristic("aggregation");
			for (ProductSpecCharacteristic relatedProdSpecChar : relatedProdSpecChars) {
				System.out.println(relatedProdSpecChar.getName());
			}
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
}
