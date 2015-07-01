package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.asiainfo.baas.common.CharacristicValueType;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class ProductSpecCharacteristicValueTest {
	

	private ProductSpecCharacteristic specChar;
	private ProductSpecCharacteristicValue memoryCharValue;
	private ProductSpecCharacteristicValue charValue;
	private static TimePeriod validFor;
	private Logger logger=Logger.getLogger(ProductSpecCharacteristicValueTest.class);

	@BeforeClass
	public static void setUpBeforeClass(){
		  validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		  
	}
	@Before
	public void initValue(){
		specChar = new ProductSpecCharacteristic("1", "摄像头", CharacristicValueType.NUMBER.getValue(),validFor, "unique",1,1);
		memoryCharValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		charValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.9");

	}
	@Test
	public void testProductSpecCharacteristicValue(){
		logger.info("ProductSpecCharacteristicValue构造函数：");
		logger.info("1.validType为空");
		try{
			memoryCharValue=new ProductSpecCharacteristicValue(null,false,"GHz",validFor,"2.7");
	    } catch (IllegalArgumentException ex) {
	    	assertNotNull(ex);
		    assertTrue(ex instanceof IllegalArgumentException);
		    assertTrue(ex.getMessage().contains("valueType should not be null"));
	    }
		//fail("expected IllegalArgumentException for validType");
		logger.info("2.validFor为空");
		try{
			memoryCharValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",null,"2.9");
	    } catch (IllegalArgumentException ex) {
	    	assertNotNull(ex);
		    assertTrue(ex instanceof IllegalArgumentException);
		    assertTrue(ex.getMessage().contains("validFor should not be null"));
	    }
		//fail("expected IllegalArgumentException for validFor");
		logger.info("3.正常情况");
		memoryCharValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.9");
		assertNotNull(memoryCharValue);
		
	}
	@Test
	public void testAddRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristicValue添加相关联的特征值：");
		
		logger.info("\t1.ProductSpecCharacteristicValue添加相关联的特征值,指定特征值为null");
		boolean result=memoryCharValue.addRelatedCharValue(null, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		assertEquals(false, result);
		
		logger.info("\t2.ProductSpecCharacteristicValue添加相关联的特征值,指定特征值与当前特征值相同");
		 result=memoryCharValue.addRelatedCharValue(memoryCharValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		assertEquals(false, result);
		
		logger.info("\t3.ProductSpecCharacteristicValue添加相关联的特征值,指定特征值与当前特征值不相同");
		result=memoryCharValue.addRelatedCharValue(charValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		assertEquals(true, result);
		logger.info("添加成功");
		logger.info("\t4.ProductSpecCharacteristicValue添加相关联的特征值,指定特征值已经建立互斥关系");
		result=memoryCharValue.addRelatedCharValue(charValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);		
		assertEquals(false, result);
		logger.info("\t5.ProductSpecCharacteristicValue添加相关联的特征值,指定特征值已经建立互斥关系,是否可以建立其他关系");
		result=memoryCharValue.addRelatedCharValue(charValue, RelationshipType.DEPENDENCY.getValue(), validFor);	
		assertEquals(false, result);
	}
	@Test
	public void testQueryRelatedCharValue(){
		logger.info("ProductSpecCharacteristicValue查询相关联的特征值：");
		
		logger.info("\t1.ProductSpecCharacteristicValue查询相关联的特征,指定类型和时间为null");
		List<ProductSpecCharacteristicValue> charValues=memoryCharValue.queryRelatedCharValue(null,null);
		assertNull(charValues);
		
		logger.info("\t2.ProductSpecCharacteristicValue查询相关联的特征,当前特征值没有指定关系的特征");
		 charValues=memoryCharValue.queryRelatedCharValue(RelationshipType.EXCLUSIVITY.getValue(),new Date());
		assertNull(charValues);
		
		logger.info("\t3.ProductSpecCharacteristicValue查询相关联的特征,当前特征值存在互斥关系特征");
		memoryCharValue.addRelatedCharValue(charValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		charValues=memoryCharValue.queryRelatedCharValue(RelationshipType.EXCLUSIVITY.getValue(),new Date());
		assertNotNull(charValues);
		logger.info("\t4.ProductSpecCharacteristicValue查询相关联的特征值,当前特征值存在互斥关系特征,不存在依赖关系");
		charValues=memoryCharValue.queryRelatedCharValue(RelationshipType.DEPENDENCY.getValue(),new Date());
		assertNull(charValues);
	}
}
