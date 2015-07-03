package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private  ProductSpecification expectProdSpec = null;
	private  TimePeriod validFor = null;
	@Before
	public  void initProdSpec(){
		validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
		prodSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple",ProductSpecificationStatus.PLANNED.getValue());
		expectProdSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple",ProductSpecificationStatus.PLANNED.getValue());
	}
	@Test
    public void testAddCharacteristic() {
        ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
        Set<ProductSpecCharUse> expectCharUse = new HashSet<ProductSpecCharUse>();
        ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic,false, false, validFor, "CPU");
        ProductSpecCharUse charUse2 = new ProductSpecCharUse(characteristic,false, false, validFor, "处理器(CPU)");
        expectCharUse.add(charUse);
       //添加一个特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        assertEquals("添加一个特征",expectCharUse,prodSpec.getProdSpecCharUse());
        
        //添加一个已存在的特征
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");
        assertEquals("添加一个已存在的特征",expectCharUse,prodSpec.getProdSpecCharUse());
        
        //添加一个已存在的特征，使用名字不同
        expectCharUse.add(charUse2);
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "处理器(CPU)");
        assertEquals("添加一个已存在的特征，使用名字不同",expectCharUse,prodSpec.getProdSpecCharUse());
        
        //添加一个特征为空
        prodSpec.addCharacteristic(null, false, false, validFor, "CPU");
        assertEquals("添加一个特征为空",expectCharUse,prodSpec.getProdSpecCharUse());
    }

    @Test
    public void testAttachCharacteristicValue() {
    	 ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	 ProductSpecCharacteristic  characteristic1 = this.createChar(TestProductSpecificationData.specChar[4]);
    	 ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
    	 ProductSpecCharacteristic  characteristic3 = this.createChar(TestProductSpecificationData.specChar[0]);
    	 //添加特征  //显示屏
    	 prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
    	 prodSpec.addCharacteristic(characteristic2, false, false, validFor, "尺寸和重量");
    	 
    	 ProductSpecCharacteristicValue charValue = this.createValue(TestProductSpecificationData.specCharValue[9]);
    	 ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[9]);
    	 ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[10]);
    	 ProductSpecCharacteristicValue charValue4 = this.createValue(TestProductSpecificationData.specCharValue[10]);
    	 
    	 characteristic.addValue(charValue);
    	 characteristic.addValue(charValue3);
    	 
    	 Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
         ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue,false,validFor);
         expectCharValueUse.add(charValueUse);
    	 
         //添加一个特征值
         prodSpec.attachCharacteristicValue(characteristic, charValue, false, validFor);
         assertEquals("添加一个特征值",expectCharValueUse, prodSpec.getProdSpecCharUse().iterator().next());
         
         //添加一个已存在的特征值
         prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
         assertEquals("添加一个已存在的特征值",expectCharValueUse,prodSpec.getProdSpecCharUse().iterator().next());
         
         //添加一个特征值,特征值不属于该特征
         prodSpec.attachCharacteristicValue(characteristic, charValue4, false, validFor);
         assertEquals("添加一个特征值,特征值不属于该特征",expectCharValueUse,prodSpec.getProdSpecCharUse().iterator().next());
         
         //添加一个特征值,特征不属于该规格
         prodSpec.attachCharacteristicValue(characteristic3, charValue3, false, validFor);
         assertEquals("添加一个特征值,特征不属于该规格",expectCharValueUse,prodSpec.getProdSpecCharUse().iterator().next());
         
         //添加一个特征值为空
         prodSpec.attachCharacteristicValue(characteristic, null, false, validFor);
         assertEquals("添加一个特征值为空",expectCharValueUse,prodSpec.getProdSpecCharUse().iterator().next());
    }

    @Test
    public void testSpecifyDefaultCharacteristicValue() {
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	//添加特征
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	//添加特征值
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
	   	 Set<ProductSpecCharUse> expectCharUse = new HashSet<ProductSpecCharUse>();
	     ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic,false, false, validFor, "CPU");
	     ProductSpecCharUse charUse2 = new ProductSpecCharUse(characteristic,false, false, validFor, "处理器(CPU)");
	     
	   	 Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
	     ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue2,true,validFor);
	     expectCharValueUse.add(charValueUse);
	     charUse.setProdSpecCharValueUse(expectCharValueUse);
	     
	     expectCharUse.add(charUse);
     
   	 	//设置某一特征的默认值
		boolean retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic, charValue2);
		assertTrue("设置某一特征的默认值",retFlag);
		
		//设置某一特征的默认值，传入的值对象为null
		charValue2 = null;
		retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,null);
		assertFalse("设置某一特征的默认值，传入的值对象为null",retFlag);
		
		//设置某一特征的默认值，传入的值对象为不是该特征所有的
		ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
		retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,charValue3);
		assertFalse("设置某一特征的默认值，传入的值对象为不是该特征所有的",retFlag);
    }

    @Test
    public void testRetrieveDefaultCharacteristicValue(){
    	ProductSpecCharacteristic  characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
    	ProductSpecCharacteristic  characteristic3 = this.createChar(TestProductSpecificationData.specChar[5]);
    	ProductSpecCharacteristic  characteristic4 = this.createChar(TestProductSpecificationData.specChar[5]);
    	ProductSpecCharacteristic  characteristic5 = this.createChar(TestProductSpecificationData.specChar[6]);
    	ProductSpecCharacteristic  characteristic6 = this.createChar(TestProductSpecificationData.specChar[6]);
    	logger.info("添加特征");
   	 	prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
   	 	prodSpec.addCharacteristic(characteristic3, false, false, validFor, "颜色");
   	 	prodSpec.addCharacteristic(characteristic5, false, false, validFor, "分辨率");
   	 
   	 	ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
   	 	ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
   	 	ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[7]);
   	 	logger.info("添加特征值");
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue1, true, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic, charValue2, false, validFor);
   	 	prodSpec.attachCharacteristicValue(characteristic5, charValue3, false, validFor);
   	 	
   	 	logger.info("查询某一特征的默认值");
   	 	List<ProdSpecCharValueUse> defaultCharValues = prodSpec.retrieveDefaultCharacteristicValue(characteristic2);
		assertNotNull("查询默认值",defaultCharValues);
		assertEquals("查询默认值",1,defaultCharValues.size());
		logger.info("默认值查询成功！");
		
		logger.info("查询某一特征的默认值，传入的特征没有值");
		defaultCharValues = prodSpec.retrieveDefaultCharacteristicValue(characteristic4);
		assertNotNull("查询某一特征的默认值，传入的特征没有值",defaultCharValues);
		assertEquals("查询某一特征的默认值，传入的特征没有值",0,defaultCharValues.size());
		logger.info("默认值查询失败！");
		
		logger.info("查询某一特征的默认值，传入的特征没有默认值");
		defaultCharValues = prodSpec.retrieveDefaultCharacteristicValue(characteristic6);
		assertNotNull("查询某一特征的默认值，传入的特征没有默认值",defaultCharValues);
		assertEquals("查询某一特征的默认值，传入的特征没有默认值",0,defaultCharValues.size());
		logger.info("默认值查询失败！");
		
		
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
   	 	
		logger.info("清除某一特征的默认值，传入的值对象为null");
		boolean retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,null);
		assertFalse("清除某一特征的默认值，传入的值对象为null",retFlag);
		logger.info("默认值清除失败！");
		
		logger.info("清除某一特征的默认值，传入的值对象为不是该特征所有的");
		ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
		retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,charValue3);
		assertFalse("清除某一特征的默认值，传入的值对象为不是该特征所有的",retFlag);
		logger.info("默认值清除失败！");
		
		logger.info("清除某一特征的默认值");
		retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic, charValue1);
		assertTrue("清除某一特征的默认值",retFlag);
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
   	 	assertNotNull("查询当前时间点的特征",charUses);
   	 	assertEquals("查询当前时间点的特征",2, charUses.size());
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
	   	assertNotNull("查询某一特征在当前时间点的值",charValueUses);
	 	assertEquals("查询某一特征在当前时间点的值",2, charValueUses.size());
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
	   	assertNotNull("查询规格的一级特征",rootCharUses);
	 	assertEquals("查询规格的一级特征",2, rootCharUses.size());
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
	   	assertNotNull("查询规格的某一特征的子特征",leafCharUses);
	 	assertEquals("查询规格的某一特征的子特征",4, leafCharUses.size());
	 	logger.info("查询子特征成功！");
	 	
	 	logger.info("查询规格的某一特征的子特征");
   	 	List<ProductSpecCharUse> leafCharUses2 = prodSpec.retrieveLeafCharacteristic(null, new Date());
	   	assertNull("查询规格的某一特征的子特征",leafCharUses2);
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
		assertTrue("设置某一特征的Cardinality",retFlag);
		logger.info("Cardinality设置成功！");
		
		logger.info("设置Cardinality，特征为空");
		retFlag = prodSpec.specifyCardinality(null,1,5);
		assertFalse("设置Cardinality，特征为空",retFlag);
		logger.info("Cardinality设置失败！");
		
		logger.info("设置Cardinality，特征不是被用的");
		retFlag = prodSpec.specifyCardinality(characteristic2,1,5);
		assertFalse("设置Cardinality，特征不是被用的",retFlag);
		logger.info("Cardinality设置失败！");
    }

    private void setExcetpProdSpec(ProductSpecCharacteristic characteristic, boolean canBeOveridden, boolean isPackage,
            TimePeriod validFor,String name,ProductSpecCharacteristicValue prodSpecCharVal, boolean isDefault){
    	 Set<ProductSpecCharUse> prodSpecCharUse = new HashSet<ProductSpecCharUse>();
         ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic,canBeOveridden,isPackage,validFor,name);
         //设置Value
         if(null != prodSpecCharVal){
        	 Set<ProdSpecCharValueUse> prodSpecCharValueUse = new HashSet<ProdSpecCharValueUse>();
	         ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(prodSpecCharVal,isDefault,validFor);
	         prodSpecCharValueUse.add(charValueUse);
	         charUse.setProdSpecCharValueUse(prodSpecCharValueUse);
         }
         prodSpecCharUse.add(charUse);
         expectProdSpec.setProdSpecCharUse(prodSpecCharUse);
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
