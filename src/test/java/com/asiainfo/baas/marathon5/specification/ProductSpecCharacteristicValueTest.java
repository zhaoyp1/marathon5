package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.asiainfo.baas.common.CharacristicValueType;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class ProductSpecCharacteristicValueTest {
	

	private ProductSpecCharacteristicValue memoryCharValue;
	private ProductSpecCharacteristicValue charValue;
	private static TimePeriod validFor;

	@BeforeClass
	public static void setUpBeforeClass(){
		  validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
	}
	@Before
	public void initValue(){
		memoryCharValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		charValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.9");

	}
	@Test
	public void testProductSpecCharacteristicValue(){
		try{
			memoryCharValue=new ProductSpecCharacteristicValue(null,false,"GHz",validFor,"2.7");
			fail("expected IllegalArgumentException for validType");
	    } catch (IllegalArgumentException ex) {
	    	
	    }
		
		
		try{
			memoryCharValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",null,"2.9");
			fail("expected IllegalArgumentException for validFor"); 
		} catch (IllegalArgumentException ex) {
	    	
	    }
		memoryCharValue=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.9");
		assertNotNull(memoryCharValue);
		
	}
	@Test
	public void testAddRelatedCharacteristic(){
		
		boolean result=memoryCharValue.addRelatedCharValue(null, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		assertEquals("add a related charValue,charValue is null",false, result);
		
		 result=memoryCharValue.addRelatedCharValue(memoryCharValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		assertEquals("add a related charValue,the specify value is same with currentCharValue",false, result);
		
		result=memoryCharValue.addRelatedCharValue(charValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		assertEquals("add a related charValue",true, result);
		
		result=memoryCharValue.addRelatedCharValue(charValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);		
		assertEquals("add a related charValue,have created a exclusive relationship",false, result);
		
		result=memoryCharValue.addRelatedCharValue(charValue, RelationshipType.DEPENDENCY.getValue(), validFor);	
		assertEquals("add a related charValue,have created a exclusive relationship",false, result);
	}
	@Test
	public void testReteriveRelatedCharValue(){
		
		List<ProductSpecCharacteristicValue> charValues=memoryCharValue.reteriveRelatedCharValue(null,null);
		assertNull("Reterive the related CharValue",charValues);
		
		 charValues=memoryCharValue.reteriveRelatedCharValue(RelationshipType.EXCLUSIVITY.getValue(),new Date());
		assertEquals("Reterive the related CharValue,The current charValue and the specified charValue have no  created an exclusivity relationship",0,charValues.size());
		
		memoryCharValue.addRelatedCharValue(charValue, RelationshipType.EXCLUSIVITY.getValue(), validFor);
		charValues=memoryCharValue.reteriveRelatedCharValue(RelationshipType.EXCLUSIVITY.getValue(),new Date());
		assertEquals("Reterive the related CharValue,The current charValue and the specified charValue have  created an exclusivity relationship",1,charValues.size());
		charValues=memoryCharValue.reteriveRelatedCharValue(RelationshipType.DEPENDENCY.getValue(),new Date());
		assertEquals("Reterive the related CharValue,The current charValue and the specified charValue have created an exclusivity relationship",0,charValues.size());
	}
}
