package com.asiainfo.baas.marathon5.specification;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.asiainfo.baas.common.CharacristicValueType;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ConfigurableProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class ProductSpecCharacteristicTest {
	
	private ProductSpecCharacteristic specChar;
	private ProductSpecCharacteristic configSpecChar;
	private static TimePeriod validFor;
	private Logger logger=Logger.getLogger(ProductSpecCharacteristicTest.class);
	@BeforeClass
	public static void setUpBeforeClass(){
		  validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		  
	}
	@Before
	public void initValue(){
		specChar = new ProductSpecCharacteristic("1", "摄像头", CharacristicValueType.TEXT.getValue(),validFor, "unique",1,1);
		configSpecChar=new ConfigurableProductSpecCharacteristic("2", "Memory", CharacristicValueType.NUMBER.getValue(),validFor, "unique",1,1);
		ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		configSpecChar.addValue(value);
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.9");
		configSpecChar.addValue(value);
	}
	@Test
	public void testAddValue(){
		boolean result=false;
		logger.info("ProductSpecCharacteristic添加特征值:");
		
		logger.info("\t1:ProductSpecCharacteristic添加特征值为null");
		ProductSpecCharacteristicValue value  =null;
		specChar.addValue(value);
		
		logger.info("\t2:ProductSpecCharacteristic添加新的特征值,特征与特征值的valueType不相同");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.7");
		result=specChar.addValue(value);
		assertEquals(false,result);
		
		logger.info("\t3:ProductSpecCharacteristic添加新的特征值,特征与特征值的valueType相同");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"720pFace TimeHD高清摄像头");
		result=specChar.addValue(value);
		assertEquals(true,result);
		assertEquals(true,specChar.getProductSpecCharacteristicValue().contains(value));
		
		logger.info("\t4:ProductSpecCharacteristic添加重复的特征值");
		result=specChar.addValue(value);
		assertEquals(false,result);
		
		

	}
	@Test
	public void testRetrieveValue(){
		logger.info("ProductSpecCharacteristic查询特征值");
		
		logger.info("\t1:ProductSpecCharacteristic查询特征值,时间为null");
		Date time=null;
		List<ProductSpecCharacteristicValue> prodSpecCharValues=specChar.retrieveValue(time);
		assertEquals(null, prodSpecCharValues);
		
		logger.info("\t2:ProductSpecCharacteristic查询特征值,时间为正确时间值，特征不存在特征值");
		time=new Date();
		prodSpecCharValues=specChar.retrieveValue(time);
		assertEquals(null, prodSpecCharValues);
		
		logger.info("\t3:ProductSpecCharacteristic查询特征值,时间为正确时间值，特征存在特征值");
		prodSpecCharValues=configSpecChar.retrieveValue(time);
		assertEquals(2,prodSpecCharValues.size());
		logger.info("查询成功,特征包含2个特征值");
	}
	@Test
	public void testSpecifyDefaultValue(){
		boolean result=false;
		logger.info("ProductSpecCharacteristic指定默认特征值:");
		
		logger.info("\t1:ProductSpecCharacteristic指定默认特征值,特征值为空");
		ProductSpecCharacteristicValue value  =null;
		result=specChar.specifyDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t2:ProductSpecCharacteristic指定默认特征值,当前特征无特征值");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"2.7");
		result=specChar.specifyDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t3:ProductSpecCharacteristic指定默认特征值,指定特征值已设置为默认值");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals(true,result);
		
		logger.info("\t4:ProductSpecCharacteristic指定默认特征值,指定特征值不为当前特征的值");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.9");
		result=specChar.specifyDefaultValue(value);
		assertEquals(false,result);
		logger.info("\t5:ProductSpecCharacteristic指定默认特征值,指定特征值包含当前特征的值，并且不为默认值");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals(true,result);
	}
	@Test
	public void testRetrieveDefaultValue(){
		logger.info("ProductSpecCharacteristic查询默认特征值:");
		logger.info("\t1.ProductSpecCharacteristic查询默认特征值,没有特征值");
		List<ProductSpecCharacteristicValue> defaultCharValue=specChar.retrieveDefaultValue();
		assertEquals(null,defaultCharValue);
		
		logger.info("\t2.ProductSpecCharacteristic查询默认特征值,没有默认值");
		ProductSpecCharacteristicValue value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		specChar.addValue(value);
		defaultCharValue=specChar.retrieveDefaultValue();
		assertEquals(null,defaultCharValue);
		logger.info("\t2.ProductSpecCharacteristic查询默认特征值,存在默认值");
		defaultCharValue=configSpecChar.retrieveDefaultValue();
		assertNotNull(defaultCharValue);
		
	}
	@Test
	public void testClearDefaultValue(){
		logger.info("ProductSpecCharacteristic取消默认值 ");
		logger.info("\t1.ProductSpecCharacteristic取消默认特征值,没有特征值");
		ProductSpecCharacteristicValue value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		boolean result=specChar.clearDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t2.ProductSpecCharacteristic取消默认特征值,存在特征值，无默认值");
		specChar.addValue(value);
		result=specChar.clearDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t3.ProductSpecCharacteristic取消默认特征值,存在特征值，有默认值，指定值不是特征的值");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t4.ProductSpecCharacteristic取消默认特征值,存在特征值，有默认值，指定值是特征的值,不是默认值");
		 value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals(false,result);
		
		logger.info("\t5.ProductSpecCharacteristic取消默认特征值,存在特征值，有默认值，指定值是特征的值,是默认值");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.9");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals(true,result);
		logger.info("清除成功");
	}
	@Test
	public void testAddRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristic添加相关联的特征：");
		
		logger.info("\t1.ProductSpecCharacteristic添加相关联的特征,指定特征为null");
		boolean result=specChar.addRelatedCharacteristic(null, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(false, result);
		
		logger.info("\t2.ProductSpecCharacteristic添加相关联的特征,指定特征与当前特征相同");
		result=specChar.addRelatedCharacteristic(specChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(false, result);
		
		logger.info("\t3.ProductSpecCharacteristic添加相关联的特征,指定特征与当前特征不相同");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(true, result);
		logger.info("添加成功");
		logger.info("\t4.ProductSpecCharacteristic添加相关联的特征,指定特征已经建立聚合关系");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(false, result);
		logger.info("\t5.ProductSpecCharacteristic添加相关联的特征,指定特征已经建立聚合关系,是否可以建立其他关系");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.DEPENDENCY.getValue(), 1, validFor);
		assertEquals(false, result);
		
	}
	@Test
	public void testRetrieveRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristic查询相关联的特征：");
		
		logger.info("\t1.ProductSpecCharacteristic查询相关联的特征,指定特征为null");
		List<ProductSpecCharacteristic> specChars=specChar.retrieveRelatedCharacteristic(null);
		assertNull(specChars);
		
		logger.info("\t2.ProductSpecCharacteristic查询相关联的特征,当前特征没有指定关系的特征");
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertNull(specChars);
		
		logger.info("\t3.ProductSpecCharacteristic查询相关联的特征,当前特征存在聚合关系特征");
		specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertNotNull(specChars);
		logger.info("\t4.ProductSpecCharacteristic查询相关联的特征,当前特征存在聚合关系特征,不存在依赖关系");
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.DEPENDENCY.getValue());
		assertNull(specChars);
	}
	

}
