package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProdSpecCharValueUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class ProductSpecCharUseTest {
	private Logger logger = Logger.getLogger(ProductSpecCharUseTest.class);
	private  ProductSpecCharUse specCharUse = null;
	private  TimePeriod validFor = null;
	private ProductSpecCharacteristic specChar = null;
	@Before
	public void initSpecCharAndValue(){
		validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		specCharUse = new ProductSpecCharUse(specChar,false,false,validFor,"处理器");
	}
	@Test
	public void testAddValue(){
		//添加一个值
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "8");
		boolean retFlag = specCharUse.addValue(charValue,false,validFor);
		ProductSpecCharUse exceptSpecCharUse = new ProductSpecCharUse(specChar,false,false,validFor,"处理器");
		Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue,false,validFor);
        expectCharValueUse.add(charValueUse);
		exceptSpecCharUse.setProdSpecCharValueUse(expectCharValueUse);
		assertEquals("添加一个值", exceptSpecCharUse,specCharUse);
		 
		//添加一个已经存在的值
		retFlag = specCharUse.addValue(charValue,false,validFor);
		assertEquals("添加一个已经存在的值", exceptSpecCharUse,specCharUse);
		
		//添加一个空值
		try{
			specCharUse.addValue(null, false, validFor);
			fail("添加一个空值");
		}catch(Exception e){}
	}
	
	@Test
	public void testSpecifyDefaultCharacteristicValueUse(){
		ProductSpecCharacteristicValue defaultValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		//添加值
		specCharUse.addValue(defaultValue,false,validFor);
		specCharUse.addValue(charValue,true,validFor);
		
		ProductSpecCharUse exceptSpecCharUse = new ProductSpecCharUse(specChar,false,false,validFor,"处理器");
		Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue,false,validFor);
        ProdSpecCharValueUse defcharValueUse = new ProdSpecCharValueUse(defaultValue,true,validFor);
        expectCharValueUse.add(charValueUse);
        expectCharValueUse.add(defcharValueUse);
		exceptSpecCharUse.setProdSpecCharValueUse(expectCharValueUse);
		
		//设置默认值
		boolean retFlag = specCharUse.specifyDefaultCharacteristicValueUse(defaultValue);
		assertEquals("设置默认值", exceptSpecCharUse,specCharUse);
		
		//设置默认值，传入的值对象为null
		defaultValue = null;
		try{
			specCharUse.specifyDefaultCharacteristicValueUse(defaultValue);
			fail("设置默认值，传入的值对象为null");
		}catch(Exception e){
		}
		
		//设置默认值，传入的值对象为不是该特征所有的
		ProductSpecCharacteristicValue defaultValue2 = new ProductSpecCharacteristicValue("number", false, "GB", new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59"), "32" );
		specCharUse.specifyDefaultCharacteristicValueUse(defaultValue2);
		assertEquals("设置默认值，传入的值对象为不是该特征所有的", exceptSpecCharUse,specCharUse);
	}
	
	@Test
	public void testRetrieveDefaultCharacteristicValueUse(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		//添加值
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		 ProdSpecCharValueUse exceptSDfcharValueUse = new ProdSpecCharValueUse(charValue2,true,validFor);
		
		//查询默认值
		List<ProdSpecCharValueUse> charValue = specCharUse.retrieveDefaultCharacteristicValueUse();
		assertNotNull("查询到值了", charValue);
		assertEquals("查询默认值",exceptSDfcharValueUse, charValue);
	}
	
	@Test
	public void testSetCardinality(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		//添加值
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		ProductSpecCharUse exceptSpecCharUse = new ProductSpecCharUse(specChar,false,false,validFor,"处理器");
		Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue1,false,validFor);
        ProdSpecCharValueUse defcharValueUse = new ProdSpecCharValueUse(charValue2,true,validFor);
        expectCharValueUse.add(charValueUse);
        expectCharValueUse.add(defcharValueUse);
		exceptSpecCharUse.setProdSpecCharValueUse(expectCharValueUse);
		//设置Cardinality
		specCharUse.setCardinality(1, 5);
		//assertEquals(1, specCharUse.getMinCardinality());
		//assertEquals(5, specCharUse.getMaxCardinality());
		assertEquals("查询默认值",exceptSpecCharUse, specCharUse);
	}
	@Test
	public void testClearDefaultValue(){
		ProductSpecCharacteristicValue charValue1 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "8");
		ProductSpecCharacteristicValue charValue2 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "16");
		ProductSpecCharacteristicValue charValue3 = new ProductSpecCharacteristicValue("number", true, "GB", new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59"), "32");
		//添加值
		specCharUse.addValue(charValue1,false,validFor);
		specCharUse.addValue(charValue2,true,validFor);
		
		//清除默认值，传入一个不是默认值的值
		boolean retFlag = specCharUse.clearDefaultValueUse(charValue1);
		assertFalse("清除默认值返回", retFlag);
		
		//清除默认值，传入一个没有被使用的值
		retFlag = specCharUse.clearDefaultValueUse(charValue3);
		assertFalse("清除默认值返回", retFlag);
		
		//清除默认值，传入一个null
		retFlag = specCharUse.clearDefaultValueUse(null);
		assertFalse("清除默认值返回", retFlag);
		
		//清除默认值
		retFlag = specCharUse.clearDefaultValueUse(charValue2);
		assertTrue("清除默认值返回", retFlag);
		
		
	}

}
