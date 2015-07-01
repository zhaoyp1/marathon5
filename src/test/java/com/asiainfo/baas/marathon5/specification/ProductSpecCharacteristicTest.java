package com.asiainfo.baas.marathon5.specification;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	private static SimpleDateFormat format;
	private Logger logger=Logger.getLogger(ProductSpecCharacteristicTest.class);
	@BeforeClass
	public static void setUpBeforeClass(){
		  format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		  
	}
	@Before
	public void initValue(){
		specChar = new ProductSpecCharacteristic("1", "摄像头", CharacristicValueType.TEXT.getValue(),validFor, "unique",1,1);
		configSpecChar=new ConfigurableProductSpecCharacteristic("2", "Memory", CharacristicValueType.NUMBER.getValue(),validFor, "unique",1,1);
		ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		configSpecChar.addValue(value);
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",new TimePeriod("2015-05-03 12:00:00","2015-07-21 23:59:59"),"2.9");
		configSpecChar.addValue(value);
	}
	@Test
	public void testAddValue(){
		boolean result=false;
		logger.info("ProductSpecCharacteristic添加特征值:");
		
		logger.info("1:添加的特征值为null");
		ProductSpecCharacteristicValue value  =null;
		result=specChar.addValue(value);
		assertEquals("添加的特征值为null",false,result);
		
		logger.info("2:添加新的特征值,特征与特征值的valueType不相同");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.7");
		result=specChar.addValue(value);
		assertEquals("添加新的特征值,特征与特征值的valueType不相同",false,result);
		
		logger.info("3:添加新的特征值,特征与特征值的valueType相同");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"720pFace TimeHD高清摄像头");
		result=specChar.addValue(value);
		assertEquals("添加新的特征值,特征与特征值的valueType相同",true,result);
		assertEquals("添加新的特征值,特征与特征值的valueType相同",true,specChar.getProductSpecCharacteristicValue().contains(value));
		
		logger.info("4:ProductSpecCharacteristic添加重复的特征值");
		result=specChar.addValue(value);
		assertEquals("添加重复的特征值",false,result);
		
		logger.info("5:添加新的特征值，值的属性与已添加的特征值相同");
		ProductSpecCharacteristicValue newValue=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"720pFace TimeHD高清摄像头");
		result=specChar.addValue(newValue);
		assertEquals("添加新的特征值，值的属性与已添加的特征值相同",false,result);

	}
	
	@Test
	public void testRetrieveValue() throws ParseException{
		logger.info("ProductSpecCharacteristic查询特征值");
		
		logger.info("1:ProductSpecCharacteristic查询特征值,时间为null");
		Date time=null;
		List<ProductSpecCharacteristicValue> prodSpecCharValues=specChar.retrieveValue(time);
		assertEquals("查询特征值,时间为null",null, prodSpecCharValues);
		
		logger.info("2:ProductSpecCharacteristic查询特征值,参数正确,特征不存在特征值");
		time=new Date();
		prodSpecCharValues=specChar.retrieveValue(time);
		assertEquals("查询特征值,参数正确，特征不存在特征值",null, prodSpecCharValues);
		
		logger.info("3:ProductSpecCharacteristic查询特征值:参数正确,特征存在特征值(时间点两个时间段内)");
		prodSpecCharValues=configSpecChar.retrieveValue(time);
		assertEquals("查询特征值:参数正确,特征存在特征值(时间点两个时间段内)",2,prodSpecCharValues.size());
		
		logger.info("4:ProductSpecCharacteristic查询特征值：参数正确,特征存在特征值(时间点在一个时间段内)");
		prodSpecCharValues=configSpecChar.retrieveValue(format.parse("2015-02-03 12:00:00"));
		assertEquals("查询特征值：参数正确,特征存在特征值(时间点在一个时间段内)",1,prodSpecCharValues.size());
		
		logger.info("5:ProductSpecCharacteristic查询特征值:参数正确,特征存在特征值(时间点不在两个时间段内)");
		prodSpecCharValues=configSpecChar.retrieveValue(format.parse("2015-01-03 12:00:00"));
		assertEquals("查询特征值:参数正确,特征存在特征值(时间点不在两个时间段内)",0,prodSpecCharValues.size());
	}
	@Test
	public void testSpecifyDefaultValue(){
		boolean result=false;
		logger.info("ProductSpecCharacteristic指定默认特征值:");
		
		logger.info("1:ProductSpecCharacteristic指定默认特征值：特征值为空");
		ProductSpecCharacteristicValue value  =null;
		result=specChar.specifyDefaultValue(value);
		assertEquals("指定默认特征值：特征值为空",false,result);
		
		logger.info("2:ProductSpecCharacteristic指定默认特征值：当前特征无特征值");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"2.7");
		result=specChar.specifyDefaultValue(value);
		assertEquals("指定默认特征值：当前特征无特征值",false,result);
		
		logger.info("3:ProductSpecCharacteristic指定默认特征值:指定特征值不属于当前特征");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.9");
		result=specChar.specifyDefaultValue(value);
		assertEquals("指定默认特征值:指定特征值不属于当前特征",false,result);
		
		logger.info("4:ProductSpecCharacteristic指定默认特征值：指定特征值属于当前特征，并且不为默认值(当前特征没有默认值)");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals("指定默认特征值：指定特征值属于当前特征，并且不为默认值(当前特征没有默认值)",true,result);
		assertEquals("指定默认特征值：指定特征值属于当前特征，并且不为默认值(当前特征没有默认值)",1,specChar.retrieveDefaultValue().size());
		
		logger.info("5:ProductSpecCharacteristic指定默认特征值：指定特征值属于当前特征，并且不为默认值(当前特征存在1个默认值)");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals("指定默认特征值：指定特征值属于当前特征，并且不为默认值(当前特征存在1个默认值)",true,result);
		assertEquals("指定默认特征值：指定特征值属于当前特征，并且不为默认值(当前特征存在1个默认值)",2,specChar.retrieveDefaultValue().size());
		
		
		logger.info("6:ProductSpecCharacteristic指定默认特征值：指定的特征值已被设置为默认值");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals("指定默认特征值：指定的特征值已被设置为默认值",true,result);
		assertEquals(2,specChar.retrieveDefaultValue().size());
	}
	
	@Test
	public void testRetrieveDefaultValue(){
		logger.info("ProductSpecCharacteristic查询默认特征值:");
		logger.info("1.ProductSpecCharacteristic查询默认特征值：特征没有特征值");
		List<ProductSpecCharacteristicValue> defaultCharValue=specChar.retrieveDefaultValue();
		assertNull("查询默认特征值：特征没有特征值",defaultCharValue);
		
		logger.info("2.ProductSpecCharacteristic查询默认特征值:存在特征值 ，没有默认值");
		ProductSpecCharacteristicValue value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		specChar.addValue(value);
		defaultCharValue=specChar.retrieveDefaultValue();
		assertEquals("查询默认特征值:存在特征值 ，没有默认值",0,defaultCharValue.size());
		
		
		logger.info("3.ProductSpecCharacteristic查询默认特征值,存在默认值");
		defaultCharValue=configSpecChar.retrieveDefaultValue();
		assertNotNull("查询默认特征值,存在默认值",defaultCharValue);
		assertEquals("查询默认特征值,存在默认值",2,defaultCharValue.size());
	}
	
	@Test
	public void testClearDefaultValue(){
		logger.info("ProductSpecCharacteristic取消默认值 ");
		
		logger.info("1.ProductSpecCharacteristic取消默认特征值：特征值为NULL");
		ProductSpecCharacteristicValue value =null;
		boolean result=specChar.clearDefaultValue(value);
		assertEquals("取消默认特征值：特征值为NULL",false,result);

		logger.info("2.ProductSpecCharacteristic取消默认特征值:参数正确，该特征没有特征值");
		 value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		 result=specChar.clearDefaultValue(value);
		assertEquals("取消默认特征值:没有特征值",false,result);
		
		logger.info("3.ProductSpecCharacteristic取消默认特征值：参数正常,存在特征值，无默认值");
		specChar.addValue(value);
		result=specChar.clearDefaultValue(value);
		assertEquals("取消默认特征值：参数正常,存在特征值，无默认值",false,result);
		
		logger.info("4.ProductSpecCharacteristic取消默认特征值:参数正常,存在特征值，有默认值，指定值不属于特征");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals("取消默认特征值:参数正常,存在特征值，有默认值，指定值不属于特征",false,result);
		
		logger.info("5.ProductSpecCharacteristic取消默认特征值：参数正常,存在特征值，有默认值，指定值属于特征,不是默认值");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals("取消默认特征值：参数正常,存在特征值，有默认值，指定值属于特征,不是默认值",false,result);
		
		logger.info("6.ProductSpecCharacteristic取消默认特征值:参数正常,存在特征值，有默认值，指定值是特征的值,是默认值");
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.9");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals("取消默认特征值:参数正常,存在特征值，有默认值，指定值是特征的值,是默认值",true,result);
		assertEquals("取消默认特征值:参数正常,存在特征值，有默认值，指定值是特征的值,是默认值",false,configSpecChar.retrieveDefaultValue().contains(value));
		logger.info("清除成功");
	}
	@Test
	public void testAddRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristic添加相关联的特征：");
		
		logger.info("1.ProductSpecCharacteristic添加相关联的特征:指定特征为null");
		boolean result=specChar.addRelatedCharacteristic(null, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals("添加相关联的特征:指定特征为null",false, result);
		
		logger.info("2.ProductSpecCharacteristic添加相关联的特征:指定特征与当前特征相同");
		result=specChar.addRelatedCharacteristic(specChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals("添加相关联的特征:指定特征与当前特征相同",false, result);
		
		logger.info("3.ProductSpecCharacteristic添加相关联的特征:指定特征与当前特征不相同");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals("添加相关联的特征:指定特征与当前特征不相同",true, result);
		assertEquals("添加相关联的特征:指定特征与当前特征不相同",true, specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue()).contains(configSpecChar));
		logger.info("添加成功");
		
		logger.info("4.ProductSpecCharacteristic添加相关联的特征：指定特征已经建立聚合关系(同一时间段)");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(false, result);
		
		logger.info("5.ProductSpecCharacteristic添加相关联的特征：指定特征已经建立聚合关系,时间段不同");
		TimePeriod  other_validFor = new TimePeriod("2015-07-22 12:00:00","2015-07-25 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals(true, result);
		
		logger.info("6.ProductSpecCharacteristic添加相关联的特征：指定特征已经建立聚合关系,时间段有交叉");
		other_validFor = new TimePeriod("2015-07-20 12:00:00","2015-07-25 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals("添加相关联的特征：指定特征已经建立聚合关系,时间段有交叉",false, result);
		
		logger.info("7.ProductSpecCharacteristic添加相关联的特征：指定特征已经建立聚合关系,时间段包含原有相同关系的时间段");
		other_validFor = new TimePeriod("2015-01-20 12:00:00","2015-07-30 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals("添加相关联的特征：指定特征已经建立聚合关系,时间段包含原有相同关系的时间段",false, result);
		
		logger.info("8.ProductSpecCharacteristic添加相关联的特征：指定特征已经建立聚合关系,时间段小于原有相同关系的时间段");
		other_validFor = new TimePeriod("2015-01-20 12:00:00","2015-03-30 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals("添加相关联的特征：指定特征已经建立聚合关系,时间段小于原有相同关系的时间段",false, result);
		
		logger.info("9.ProductSpecCharacteristic添加相关联的特征,指定特征已经建立聚合关系,是否可以建立其他关系");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.DEPENDENCY.getValue(), 1, validFor);
		assertEquals(false, result);
		
	}
	@Test
	public void testRetrieveRelatedCharacteristic(){
		logger.info("ProductSpecCharacteristic查询相关联的特征：");
		
		logger.info("1.ProductSpecCharacteristic查询相关联的特征,指定特征为null");
		List<ProductSpecCharacteristic> specChars=specChar.retrieveRelatedCharacteristic(null);
		assertNull(specChars);
		
		logger.info("2.ProductSpecCharacteristic查询相关联的特征,当前特征没有指定关系的特征");
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertNull(specChars);
		
		logger.info("3.ProductSpecCharacteristic查询相关联的特征,当前特征存在聚合关系特征");
		specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertNotNull(specChars);
		logger.info("4.ProductSpecCharacteristic查询相关联的特征,当前特征存在聚合关系特征,不存在依赖关系");
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.DEPENDENCY.getValue());
		assertNull(specChars);
	}
	

}
