package com.asiainfo.baas.marathon5.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon5.common.CommonUtils;


public class TestProductSpecCharacteristic {

	@Test
	public void  addValue(){
		 TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		 ProductSpecCharacteristic productSpecification=new ProductSpecCharacteristic("1","颜色", "",validFor , "",1,6,true, "", "");
		 ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue("",true,"",validFor,"黑色");
		 productSpecification.addValue(value);
	}
	
	@Test
	public void getValue(){
		TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
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
		TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
        
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","颜色", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue("",true,"",validFor,"黑色");
			productSpecCharacteristic.setDefaultValue(value);
         
	}
	@Test
	public void getDefaultValue(){
		TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
         
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","颜色", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue("",true,"",validFor,"黑色");
			productSpecCharacteristic.setDefaultValue(value);
			ProductSpecCharacteristicValue defaultValue=productSpecCharacteristic.getDefaultValue();
			System.out.println(defaultValue.getValue());
         
	}
	public void setLeafValue(){
		TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
        
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","尺寸", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subProductSpecCharLength=new ProductSpecCharacteristic("1","长", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subproductSpecCharWidth=new ProductSpecCharacteristic("1","宽", "",validFor , "",1,6,true, "", "");

			productSpecCharacteristic.addLeafCharacteristic(subProductSpecCharLength, validFor);
			productSpecCharacteristic.addLeafCharacteristic(subproductSpecCharWidth, validFor);
         
	}
	
	@Test
	public void getLeafValue(){
		TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
        
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","尺寸", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subProductSpecCharLength=new ProductSpecCharacteristic("1","长", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subproductSpecCharWidth=new ProductSpecCharacteristic("1","宽", "",validFor , "",1,6,true, "", "");

			productSpecCharacteristic.addLeafCharacteristic(subProductSpecCharLength, validFor);
			productSpecCharacteristic.addLeafCharacteristic(subproductSpecCharWidth, validFor);
			ProductSpecCharacteristic[] subProdSpecChars=productSpecCharacteristic.getLeafCharacteristic();
			for (ProductSpecCharacteristic subProdSpecChar : subProdSpecChars) {
				System.out.println(subProdSpecChar.getName());
			}
			ProductSpecCharacteristic[] relatedProdSpecChars=productSpecCharacteristic.getRelatedCharacteristic(ProductConst.RELATIONSHIP_TYPE_DEPENDENCY);
			for (ProductSpecCharacteristic relatedProdSpecChar : relatedProdSpecChars) {
				System.out.println(relatedProdSpecChar.getName());
			}
        
	}
	
	@Test
	public void getRelatedValue(){
		TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
         
			ProductSpecCharacteristic productSpecCharacteristic=new ProductSpecCharacteristic("1","尺寸", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subProductSpecCharLength=new ProductSpecCharacteristic("1","长", "",validFor , "",1,6,true, "", "");
			ProductSpecCharacteristic subproductSpecCharWidth=new ProductSpecCharacteristic("1","宽", "",validFor , "",1,6,true, "", "");

			productSpecCharacteristic.addLeafCharacteristic(subProductSpecCharLength, validFor);
			productSpecCharacteristic.addLeafCharacteristic(subproductSpecCharWidth, validFor);
			 
			ProductSpecCharacteristic[] relatedProdSpecChars=productSpecCharacteristic.getRelatedCharacteristic(ProductConst.RELATIONSHIP_TYPE_DEPENDENCY);
			for (ProductSpecCharacteristic relatedProdSpecChar : relatedProdSpecChars) {
				System.out.println(relatedProdSpecChar.getName());
			}
        
	}
}
