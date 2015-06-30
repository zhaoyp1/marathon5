package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.asiainfo.baas.common.ProductSpecificationStatus;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProdSpecCharValueUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon5.apple.TestProductSpecificationData;

public class ProductSpecificationTest {
	private Logger logger = Logger.getLogger(ProductSpecificationTest.class);
	private  ProductSpecification prodSpec = null;
	private  TimePeriod validFor = null;
	@Before
	public  void initProdSpec(){
		validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		prodSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple",ProductSpecificationStatus.PLANNED.getValue());
	}
	@Test
    public void testAddCharacteristic() {
        ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        logger.info("添加一个特征");
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        assertEquals(1,prodSpec.getProdSpecCharUse().size());
        logger.info("添加特征成功！");
        
        logger.info("添加一个已存在的特征");
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        assertEquals(1,prodSpec.getProdSpecCharUse().size());
        logger.info("添加特征失败！");
        
        logger.info("添加一个特征为空");
        prodSpec.addCharacteristic(null, false, false, validFor, "CPU");
        assertEquals(1,prodSpec.getProdSpecCharUse().size());
        logger.info("添加特征失败！");
    }

    @Test
    public void testAttachCharacteristicValue() {
    	 ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	 prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
    	 
    	 ProductSpecCharacteristicValue charValue = this.createValue(TestProductSpecificationData.specCharValue[9]);
         logger.info("添加一个特征值");
         prodSpec.attachCharacteristicValue(characteristic, charValue, false, validFor);
         assertEquals(1,prodSpec.getProdSpecCharUse().size());
         logger.info("添加特征值成功！");
         
         logger.info("添加一个已存在的特征值");
         prodSpec.attachCharacteristicValue(characteristic, charValue, false, validFor);
         assertEquals(1,prodSpec.getProdSpecCharUse().size());
         logger.info("添加特征值失败！");
         
         logger.info("添加一个特征值为空");
         prodSpec.attachCharacteristicValue(characteristic, null, false, validFor);
         assertEquals(1,prodSpec.getProdSpecCharUse().size());
         logger.info("添加特征值失败！");
    }

    @Test
    public void testSpecifyDefaultCharacteristicValue() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	logger.info("添加特征");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	logger.info("添加特征值");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
   	 	
   	 	logger.info("设置默认值");
		boolean retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue2);
		assertTrue("默认值设置成功",retFlag);
		logger.info("默认值设置成功！");
		
		logger.info("设置默认值，传入的值对象为null");
		charValue2 = null;
		retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,null);
		assertFalse("默认值设置失败",retFlag);
		logger.info("默认值设置失败！");
		
		logger.info("设置默认值，传入的值对象为不是该特征所有的");
		ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
		retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,charValue3);
		assertFalse("默认值设置失败",retFlag);
		logger.info("默认值设置失败！");
    }

    @Test
    public void testClearDefaultCharacteristicValue(){
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	logger.info("添加特征");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	logger.info("添加特征值");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
   	 	
		logger.info("清除默认值，传入的值对象为null");
		boolean retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,null);
		assertFalse("默认值清除失败",retFlag);
		logger.info("默认值清除失败！");
		
		logger.info("清除默认值，传入的值对象为不是该特征所有的");
		ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
		retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,charValue3);
		assertFalse("默认值清除失败",retFlag);
		logger.info("默认值清除失败！");
		
		logger.info("清除默认值");
		retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic, charValue1);
		assertTrue("默认值清除成功",retFlag);
		logger.info("默认值清除成功！");
		
    }
    @Test
    public void testRetrieveCharacteristic() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	logger.info("添加特征");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 	prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");
   	 	
   	 	logger.info("查询当前时间点的特征");
   	 	List<ProductSpecCharUse> charUses = prodSpec.retrieveCharacteristic(new Date());
   	 	assertNotNull(charUses);
   	 	assertEquals(2, charUses.size());
   	 	logger.info("查询特征成功！");
    }

    @Test
    public void testRetrieveCharacteristicValue() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	logger.info("添加特征");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	logger.info("添加特征值");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
   	 	
   	 	logger.info("查询某一特征在当前时间点的值");
	   	List<ProdSpecCharValueUse> charValueUses = prodSpec.retrieveCharacteristicValue(characteristic, new Date());
	   	assertNotNull(charValueUses);
	 	assertEquals(2, charValueUses.size());
	 	logger.info("查询特征成功！");
    }

    @Test
    public void testRetrieveRootCharacteristic() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	ProductSpecCharacteristic  characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
    	ProductSpecCharacteristic  characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
    	ProductSpecCharacteristic  characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
    	ProductSpecCharacteristic  characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
    	logger.info("添加Char聚合关系");
    	characteristic2.addRelatedCharacteristic(characteristic3, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic4, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic5, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic6, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	logger.info("添加特征");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 	prodSpec.addCharacteristic(characteristic2, false, false, validFor, "尺寸和重量");
	   	prodSpec.addCharacteristic(characteristic3, false, false, validFor, "长");
	   	prodSpec.addCharacteristic(characteristic4, false, false, validFor, "宽");
	   	prodSpec.addCharacteristic(characteristic5, false, false, validFor, "高");
	   	prodSpec.addCharacteristic(characteristic6, false, false, validFor, "重量");
   	 
   	 	logger.info("查询规格的一级特征");
   	 	List<ProductSpecCharUse> rootCharUses = prodSpec.retrieveRootCharacteristic();
	   	assertNotNull(rootCharUses);
	 	assertEquals(2, rootCharUses.size());
	 	logger.info("查询特征成功！");
    }

    @Test
    public void getLeafCharacteristic() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	ProductSpecCharacteristic  characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
    	ProductSpecCharacteristic  characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
    	ProductSpecCharacteristic  characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
    	ProductSpecCharacteristic  characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
    	logger.info("添加Char聚合关系");
    	characteristic2.addRelatedCharacteristic(characteristic3, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic4, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic5, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	characteristic2.addRelatedCharacteristic(characteristic6, RelationshipType.AGGREGATION.getValue(), 1, validFor);
    	logger.info("添加特征");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 	prodSpec.addCharacteristic(characteristic2, false, false, validFor, "尺寸和重量");
	   	prodSpec.addCharacteristic(characteristic3, false, false, validFor, "长");
	   	prodSpec.addCharacteristic(characteristic4, false, false, validFor, "宽");
	   	prodSpec.addCharacteristic(characteristic5, false, false, validFor, "高");
	   	prodSpec.addCharacteristic(characteristic6, false, false, validFor, "重量");
   	 
   	 	logger.info("查询规格的某一特征的子特征");
   	 	List<ProductSpecCharUse> leafCharUses = prodSpec.retrieveLeafCharacteristic(characteristic2, new Date());
	   	assertNotNull(leafCharUses);
	 	assertEquals(4, leafCharUses.size());
	 	logger.info("查询子特征成功！");
	 	
	 	logger.info("查询规格的某一特征的子特征");
   	 	List<ProductSpecCharUse> leafCharUses2 = prodSpec.retrieveLeafCharacteristic(null, new Date());
	   	assertNull(leafCharUses2);
	 	logger.info("查询子特征失败！");
    }

    @Test
    public void testSpecifyCardinality() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	logger.info("添加特征");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	logger.info("添加特征值");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
		
		logger.info("设置某一特征的Cardinality");
		boolean retFlag = prodSpec.specifyCardinality(characteristic,1,5);
		assertTrue(retFlag);
		logger.info("Cardinality设置成功！");
		
		logger.info("设置Cardinality，特征为空");
		retFlag = prodSpec.specifyCardinality(null,1,5);
		assertFalse(retFlag);
		logger.info("Cardinality设置失败！");
		
		logger.info("设置Cardinality，特征不是被用的");
		retFlag = prodSpec.specifyCardinality(characteristic2,1,5);
		assertFalse(retFlag);
		logger.info("Cardinality设置失败！");
    }

    private ProductSpecCharacteristicValue createValue(Object[] obj){
        ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue((String)obj[1], (boolean)obj[2],
        		(String)obj[3], (TimePeriod)obj[4], (String)obj[5]);
        return charValue;
    }

    private ProductSpecCharacteristic createChar(Object[] obj){
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic((String)obj[0], (String)obj[1],
        		(String)obj[2], (TimePeriod)obj[3], (String)obj[4],(Integer)obj[5],(Integer)obj[5]);
        return specChar;
    }
}
