package com.asiainfo.baas.marathon5.specification;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.asiainfo.baas.common.CharacristicValueType;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ConfigurableProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharRelationship;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class ProductSpecCharacteristicTest {
	
	private ProductSpecCharacteristic specChar;
	private ProductSpecCharacteristic configSpecChar;
	private ProductSpecCharacteristic exceptChar;
	private ProductSpecCharacteristic exceptConfigSpecChar;
	private static TimePeriod validFor;
	private static SimpleDateFormat format;
	@BeforeClass
	public static void setUpBeforeClass(){
		  format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  validFor = new TimePeriod("2015-02-03 12:00:00","2015-07-21 23:59:59");
		  
	}
	
	@Before
	public void initValue(){
		specChar = new ProductSpecCharacteristic("1", "摄像头", CharacristicValueType.TEXT.getValue(),validFor, "unique",1,1);
		exceptChar= specChar;
		configSpecChar=new ConfigurableProductSpecCharacteristic("2", "Memory", CharacristicValueType.NUMBER.getValue(),validFor, "unique",1,1);
		ProductSpecCharacteristicValue value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		configSpecChar.addValue(value);
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",new TimePeriod("2015-05-03 12:00:00","2015-07-21 23:59:59"),"2.9");
		configSpecChar.addValue(value);
		exceptConfigSpecChar=configSpecChar;
	}
	
	@Test
	public void testAddValue(){
		boolean result=false;
		ProductSpecCharacteristicValue value  =null;
		try{
			result=specChar.addValue(value);
			fail("add a empty value， expected IllegalArgumentException for value");
		}catch(IllegalArgumentException ex){
			assertEquals("add a empty value",exceptChar.toString(),specChar.toString());
		}
		try{
			value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.7");
			result=specChar.addValue(value);
			fail("The value type of Character and CharacterValue value is different,expected IllegalArgumentException for valueType");
		}catch(IllegalArgumentException ex){
			assertEquals("The value type of Character and CharacterValue value is different",exceptChar.toString(),specChar.toString());
		}
		exceptChar.getProductSpecCharacteristicValue().add(value);
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"720pFace TimeHD高清摄像头");
		result=specChar.addValue(value);
		assertEquals("add a normal value",exceptChar.toString(),specChar.toString());
		assertEquals("add a normal value",true,result);
		assertEquals("add a normal value",true,specChar.getProductSpecCharacteristicValue().contains(value));
		assertEquals("add a normal value",1,specChar.getProductSpecCharacteristicValue().size());
		result=specChar.addValue(value);
		assertEquals("Add a duplicate value",true,result);
		assertEquals("Add a duplicate value",1,specChar.getProductSpecCharacteristicValue().size());
		assertEquals("Add a duplicate value",exceptChar.toString(),specChar.toString());
 		
		ProductSpecCharacteristicValue newValue=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"720pFace TimeHD高清摄像头");
		result=specChar.addValue(newValue);
		assertEquals("Add a duplicate value ,Values are the same as before. ",true,result);
		assertEquals("Add a duplicate value ,Values are the same as before. ",1,specChar.getProductSpecCharacteristicValue().size());
		assertEquals("Add a duplicate value",exceptChar.toString(),specChar.toString());
	}
	
	@Test
	public void testRetrieveValue() throws ParseException{
		Date time=null;
		List<ProductSpecCharacteristicValue> prodSpecCharValues=null;
		try {
			prodSpecCharValues=specChar.retrieveValue(time);
			fail("query the value of Charistist:time is null, expected IllegalArgumentException for time");

		} catch (IllegalArgumentException ex) {
			assertEquals("query the value of Charistist:time is null",exceptChar.toString(),specChar.toString());
		}
		
		time=new Date();
		prodSpecCharValues=specChar.retrieveValue(time);
		assertEquals("query the value of CharististValue:No value of the Characteristic",0, prodSpecCharValues.size());
		
		prodSpecCharValues=configSpecChar.retrieveValue(time);
		assertEquals("query the value of Charistist:(Time points in two time periods.)",2,prodSpecCharValues.size());
		
		prodSpecCharValues=configSpecChar.retrieveValue(format.parse("2015-02-03 12:00:00"));
		assertEquals("query the value of Charistist:(Time points in on time periods.)",1,prodSpecCharValues.size());
		
		prodSpecCharValues=configSpecChar.retrieveValue(format.parse("2015-01-03 12:00:00"));
		assertEquals("query the value of Charistist:(Time points not in on time periods.)",0,prodSpecCharValues.size());
	}
	@Test
	public void testSpecifyDefaultValue(){
		boolean result=false;
		ProductSpecCharacteristicValue value  =null;
		try{
			result=specChar.specifyDefaultValue(value);
			fail("Specify the DefaultValue of characterist:value is null expected IllegalArgumentException for value");
			
		}catch(IllegalArgumentException ex){
			assertEquals("Specify the DefaultValue of characterist:value is null",exceptChar.toString(),specChar.toString());
		}
		
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),true,"",validFor,"2.7");
		result=specChar.specifyDefaultValue(value);
		assertEquals("Specify the DefaultValue of characterist：No value of the Characteristic",false,result);
		assertEquals("Specify the DefaultValue of characterist：No value of the Characteristic",0,specChar.retrieveDefaultValue().size());
		assertEquals("Specify the DefaultValue of characterist：No value of the Characteristic",true,specChar.retrieveDefaultValue().contains(value));

		assertEquals("Specify the DefaultValue of characterist：No value of the Characteristic",exceptChar.toString(),specChar.toString());
		
		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.9");
	 	specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		setDefaultValue(value);
		assertEquals("Specify the DefaultValue of characterist:Char does not exist default values",true,result);
		assertEquals("Specify the DefaultValue of characterist:Char does not exist default values",1,specChar.retrieveDefaultValue().size());
		assertEquals("Specify the DefaultValue of characterist:Char does not exist default values",true,specChar.retrieveDefaultValue().contains(value));

		value=new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		exceptChar.getProductSpecCharacteristicValue().add(value);
		this.setDefaultValue(value);
		assertEquals("Specify the DefaultValue of characterist：the current is not the defaultValue( has one default values)",true,result);
		assertEquals("Specify the DefaultValue of characterist：the current is not the defaultValue( has one default values)",2,specChar.retrieveDefaultValue().size());
		assertEquals("Specify the DefaultValue of characterist：the current is not the defaultValue( has one default values)",true,specChar.retrieveDefaultValue().contains(value));
		assertEquals("Specify the DefaultValue of characterist：the current is not the defaultValue( has one default values)",exceptChar.toString(),specChar.toString());

		
		specChar.addValue(value);
		result=specChar.specifyDefaultValue(value);
		assertEquals("Specify the DefaultValue of characterist：the current value is  the default Value",true,result);
		assertEquals("Specify the DefaultValue of characterist：the current value is  the default Value",2,specChar.retrieveDefaultValue().size());
		assertEquals("Specify the DefaultValue of characterist：the current value is  the default Value",exceptChar.toString(),specChar.toString());

	}
	
	@Test
	public void testRetrieveDefaultValue(){
		
		List<ProductSpecCharacteristicValue> defaultCharValue=specChar.retrieveDefaultValue();
		assertEquals("retriveve the default value ：Char does not exist  values",0,defaultCharValue.size());
		assertEquals("retriveve the default value ：Char does not exist  values",exceptChar.toString(),specChar.toString());

		ProductSpecCharacteristicValue value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		specChar.addValue(value);
		exceptChar.getProductSpecCharacteristicValue().add(value);
		defaultCharValue=specChar.retrieveDefaultValue();
		assertEquals("retriveve the default value ：Char does not exist  default values",0,defaultCharValue.size());
		assertEquals("retriveve the default value ：Char does not exist  default values",exceptChar.toString(),specChar.toString());
		defaultCharValue=configSpecChar.retrieveDefaultValue();
		assertEquals("retriveve the default value ：Default values exist for  Char ",1,defaultCharValue.size());
		assertEquals("retriveve the default value ：Default values exist for  Char ",exceptConfigSpecChar.toString(),configSpecChar.toString());

	}
	
	@Test
	public void testClearDefaultValue(){
		
		ProductSpecCharacteristicValue value =null;
		boolean result = false;
		try{
			  result=specChar.clearDefaultValue(value);
			  fail("clear the default Value of Char ,but the value is null,expected IllegalArgumentException for value");
		}catch(IllegalArgumentException ex){
			assertEquals("clear the default Value of Char:value is null",exceptChar.toString(),specChar.toString());
		}
		
		
		 value =new ProductSpecCharacteristicValue(CharacristicValueType.TEXT.getValue(),false,"",validFor,"2.7");
		 result=specChar.clearDefaultValue(value);
		assertEquals("clear the Default value of char:No value of the Characteristic",false,result);
		assertEquals("clear the Default value of char:No value of the Characteristic",exceptChar.toString(),specChar.toString());
		
		specChar.addValue(value);
		result=specChar.clearDefaultValue(value);
		assertEquals("clear the Default value of char:No Default value of the Characteristic",true,result);
		assertEquals("clear the Default value of char:No Default value of the Characteristic",exceptChar.toString(),specChar.toString());

		
		result=configSpecChar.clearDefaultValue(value);
		assertEquals("clear default value:  Values do not belong to this char",false,result);
		assertEquals("clear default value:  Values do not belong to this char",exceptChar.toString(),specChar.toString());
		
		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),false,"GHz",validFor,"2.7");
		result=configSpecChar.clearDefaultValue(value);
		assertEquals("clear default value: Value is not the default value",true,result);
		assertEquals("clear default value: Value is not the default value",false,specChar.retrieveDefaultValue().contains(value));

		assertEquals("clear default value: Value is not the default value",exceptChar.toString(),specChar.toString());

		value=new ProductSpecCharacteristicValue(CharacristicValueType.NUMBER.getValue(),true,"GHz",validFor,"2.9");
		result=configSpecChar.clearDefaultValue(value);
		for (ProductSpecCharacteristicValue exceptVal : exceptConfigSpecChar.retrieveDefaultValue()) {
			if(exceptVal .equals(value)){
				exceptVal.setIsDefault(false);
			}
		}
		assertEquals("clear default value: Value is  the default value",true,result);
		assertEquals("clear default value: Value is  the default value",false,configSpecChar.retrieveDefaultValue().contains(value));
		assertEquals("clear default value: Value is  the default value",exceptConfigSpecChar.toString(),configSpecChar.toString());
	}
	@Test
	public void testAddRelatedCharacteristic(){
		boolean result=false;
		try{
			result=	specChar.addRelatedCharacteristic(null, RelationshipType.AGGREGATION.getValue(), 1, validFor);
			fail("add related SpecChar:the targetChar is null,expected IllegalArgumentException for targetChar ");
		}catch(IllegalArgumentException ex){
			assertEquals("add related SpecChar",exceptChar.toString(),specChar.toString());
		}
		
		result=specChar.addRelatedCharacteristic(specChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals("add Related SpecChar:The srcChar is the same as the targetChar.",false, result);
		assertEquals("add Related SpecChar:The srcChar is the same as the targetChar.",exceptChar.toString(), specChar.toString());

		
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		ProductSpecCharRelationship relationShip=new ProductSpecCharRelationship(specChar,configSpecChar,RelationshipType.AGGREGATION.getValue(), validFor,1);
		if(exceptChar.getProdSpecCharRelationship()!=null){
			exceptChar.getProdSpecCharRelationship().add(relationShip);
		}else{
			List<ProductSpecCharRelationship> relationChars= new ArrayList<ProductSpecCharRelationship>();
			relationChars.add(relationShip);
			specChar.setProdSpecCharRelationship(relationChars);
		}
		
		assertEquals("add Related SpecChar",true, result);
		assertEquals("add Related SpecChar",true, specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue()).contains(configSpecChar));
		assertEquals("add Related SpecChar",exceptChar.toString(), specChar.toString());

		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		assertEquals(false, result);
		assertEquals("add Related SpecChar：The current char and the specified char have created an aggregate relationship(Same time)",exceptChar.toString(), specChar.toString());
		
		TimePeriod  other_validFor = new TimePeriod("2015-07-22 12:00:00","2015-07-25 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals(true, result);
		assertEquals("add Related SpecChar：The current char and the specified char have created an aggregate relationship(Different time periods)",exceptChar.toString(), specChar.toString());

		
		
		other_validFor = new TimePeriod("2015-07-20 12:00:00","2015-07-25 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals("add Related SpecChar：he current char and the specified char have created an aggregate relationship",false, result);
		assertEquals("add Related SpecChar：he current char and the specified char have created an aggregate relationship",exceptChar.toString(), specChar.toString());
		assertEquals("add Related SpecChar：he current char and the specified char have created an aggregate relationship",exceptChar.toString(), specChar.toString());

		other_validFor = new TimePeriod("2015-01-20 12:00:00","2015-07-30 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals("add Related SpecChar：he current char and the specified char have created an aggregate relationship",false, result);
		assertEquals("add Related SpecChar：he current char and the specified char have created an aggregate relationship",exceptChar.toString(), specChar.toString());
		assertEquals("add Related SpecChar：he current char and the specified char have created an aggregate relationship",exceptChar.toString(), specChar.toString());

		
		other_validFor = new TimePeriod("2015-01-20 12:00:00","2015-03-30 23:59:59");
		result=specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, other_validFor);
		assertEquals("add Related SpecChar：he current char and the specified char have created an aggregate relationship",false, result);
		assertEquals("add Related SpecChar：he current char and the specified char have created an aggregate relationship",exceptChar.toString(), specChar.toString());
		assertEquals("add Related SpecChar：he current char and the specified char have created an aggregate relationship",exceptChar.toString(), specChar.toString());
		
	}
	@Test
	public void testRetrieveRelatedCharacteristic(){
		List<ProductSpecCharacteristic> specChars=null;
		try{
			 specChars=specChar.retrieveRelatedCharacteristic(null);
			 fail("retrieve Related char : type is null,except IllegalArgumentException for type");
		}catch(IllegalArgumentException ex){
			assertEquals("retrieve Related char：type is null",exceptChar.toString(),specChar.toString());
		}
		
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertEquals("retrieve Related char:The char does not specify the type of association char",exceptChar.toString(),specChar.toString());

		assertEquals("retrieve Related char:The char does not specify the type of association char",0,specChars.size());
		
		specChar.addRelatedCharacteristic(configSpecChar, RelationshipType.AGGREGATION.getValue(), 1, validFor);
		specChars=specChar.retrieveRelatedCharacteristic(RelationshipType.AGGREGATION.getValue());
		assertEquals("retrieve Related char:The char have the specify   type of association char",1,specChars.size());
		assertEquals("retrieve Related char:The char have the specify   type of association char",exceptChar.toString(),specChar.toString());
	}
	
	public void setDefaultValue(ProductSpecCharacteristicValue value){
		if( null != exceptChar.getProductSpecCharacteristicValue()){
			for (ProductSpecCharacteristicValue exceptVal : exceptChar.getProductSpecCharacteristicValue()) {
				if(exceptVal.equals(value)){
					value.setIsDefault(true);
					break;
				}
			}
		}
		 
	}
	

}
