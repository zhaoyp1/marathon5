package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProdSpecCharValueUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class ProductSpecCharUseTest {
	private Logger logger = Logger.getLogger(ProductSpecCharUseTest.class);
	private static ProductSpecCharUse specCharUse = null;
	private static TimePeriod validFor = null;
	@BeforeClass
	public static void initSpecCharAndValue(){
		validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic("1", "processor(处理器)", "Number",validFor, "unique",1,1);
		specCharUse = new ProductSpecCharUse(specChar,false,false,validFor);
	}
	@Test
	public void testAddValue(){
		logger.info("添加一个值");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "8");
		boolean retFlag = specCharUse.addValue(charValue,false,validFor);
		 assertTrue("添加成功",retFlag);
		 logger.info("添加成功");
		logger.info("添加一个已经存在的值");
		retFlag = specCharUse.addValue(charValue,false,validFor);
		assertFalse("添加失败",retFlag);
		logger.info("添加一个空值");
		retFlag = specCharUse.addValue(null, false, validFor);
		assertFalse("添加失败",retFlag);
	}
	
	@Test
	public void testSpecifyDefaultCharacteristicValueUse(){
		ProductSpecCharacteristicValue defaultValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		logger.info("添加值");
		specCharUse.addValue(defaultValue,false,validFor);
		specCharUse.addValue(charValue,true,validFor);
		
		logger.info("设置默认值");
		boolean retFlag = specCharUse.specifyDefaultCharacteristicValueUse(defaultValue);
		assertTrue("默认值设置成功",retFlag);
		logger.info("默认值设置成功！");
		
		logger.info("设置默认值，传入的值对象为null");
		defaultValue = null;
		retFlag = specCharUse.specifyDefaultCharacteristicValueUse(defaultValue);
		assertFalse("默认值设置失败",retFlag);
		logger.info("默认值设置失败");
		
		logger.info("设置默认值，传入的值对象为不是该特征所有的");
		ProductSpecCharacteristicValue defaultValue2 = new ProductSpecCharacteristicValue("number", false, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "32" );
		retFlag = specCharUse.specifyDefaultCharacteristicValueUse(defaultValue2);
		assertFalse("默认值设置失败",retFlag);
		logger.info("默认值设置失败");
	}
	
	@Test
	public void testRetrieveDefaultCharacteristicValueUse(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		logger.info("添加值");
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		logger.info("查询默认值");
		ProdSpecCharValueUse charValue = specCharUse.retrieveDefaultCharacteristicValueUse();
		assertNotNull("查询到值了", charValue);
		assertTrue("是默认值", charValue.isIsDefault());
		logger.info("默认值查询成功！默认值为："+charValue.toString());
	}
	
	@Test
	public void testSetCardinality(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		logger.info("添加值");
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		logger.info("设置Cardinality");
		specCharUse.setCardinality(1, 5);
		assertEquals(1, specCharUse.getMinCardinality());
		logger.info("MinCardinality设置成功！");
		assertEquals(5, specCharUse.getMaxCardinality());
		logger.info("MaxCardinality设置成功！");
	}

}
