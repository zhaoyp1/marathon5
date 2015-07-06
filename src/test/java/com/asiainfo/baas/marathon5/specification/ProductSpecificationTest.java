package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.AssertTrue;

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
import com.asiainfo.baas.marathon.specification.ProductSpecificationRelationship;
import com.asiainfo.baas.marathon5.apple.TestProductSpecificationData;

public class ProductSpecificationTest {
    private Logger logger = Logger.getLogger(ProductSpecificationTest.class);
    private ProductSpecification prodSpec = null;
    private ProductSpecification expectProdSpec = null;
    private TimePeriod validFor = null;
    private ProductSpecification srcProdSpec = null;

    @Before
    public void initProdSpec() {
        srcProdSpec = new AtomicProductSpecification("S001", "iPhone6", "Apple iPhone",
                ProductSpecificationStatus.PLANNED.getValue());
        validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
        prodSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple",
                ProductSpecificationStatus.PLANNED.getValue());
        expectProdSpec = new AtomicProductSpecification("mac-13", "13-inch MacBook Pro", "apple",
                ProductSpecificationStatus.PLANNED.getValue());
    }

    @Test
    public void testAddCharacteristic() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
        Set<ProductSpecCharUse> expectCharUse = new HashSet<ProductSpecCharUse>();
        ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic, false, false, validFor, "CPU");
        ProductSpecCharUse charUse2 = new ProductSpecCharUse(characteristic, false, false, validFor, "处理器(CPU)");
        expectCharUse.add(charUse);
        // 添加一个特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        assertEquals("添加一个特征", expectCharUse, prodSpec.getProdSpecCharUse());

        // 添加一个已存在的特征
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");
        assertEquals("添加一个已存在的特征", expectCharUse, prodSpec.getProdSpecCharUse());

        // 添加一个已存在的特征，使用名字不同
        expectCharUse.add(charUse2);
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "处理器(CPU)");
        assertEquals("添加一个已存在的特征，使用名字不同", 2, prodSpec.getProdSpecCharUse().size());
        assertEquals("添加一个已存在的特征，使用名字不同", expectCharUse, prodSpec.getProdSpecCharUse());

        // 添加一个特征为空
        try{
	        prodSpec.addCharacteristic(null, false, false, validFor, "CPU");
	        fail("添加一个特征为空");
        }catch(Exception e){}
        
        // 添加一个特征为空，使用名为空null
        try{
	        prodSpec.addCharacteristic(characteristic2, false, false, validFor, null);
	        fail("添加一个特征为空，使用名为空null");
        }catch(Exception e){}
        
        // 添加一个特征，使用名为空字符
        try{
	        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "");
	        fail("添加一个特征，使用名为空字符");
        }catch(Exception e){}
    }

    @Test
    public void testAttachCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[0]);

        ProductSpecCharacteristicValue charValue = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        ProductSpecCharacteristicValue charValue4 = this.createValue(TestProductSpecificationData.specCharValue[10]);

        characteristic.addValue(charValue);
        characteristic.addValue(charValue3);
        
        characteristic2.addValue(charValue);
        characteristic2.addValue(charValue3);
        
        // 添加特征 //显示屏
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue, false, validFor);

        // 添加一个特征值
        prodSpec.attachCharacteristicValue(characteristic,"CPU", charValue, false, validFor);
        assertEquals("添加一个特征值",1,prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().size());
        assertTrue("添加一个特征值",prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().contains(charValueUse));

        // 添加一个已存在的特征值
        prodSpec.attachCharacteristicValue(characteristic,"CPU", charValue2, false, validFor);
        assertEquals("添加一个已存在的特征值",1,prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().size());
        assertTrue("添加一个已存在的特征值",prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().contains(charValueUse));

        // 添加一个特征值,特征值不属于该特征
        prodSpec.attachCharacteristicValue(characteristic,"CPU", charValue4, false, validFor);
        assertEquals("添加一个特征值,特征值不属于该特征",1,prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().size());
        assertTrue("添加一个特征值,特征值不属于该特征",prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse().contains(charValueUse));

        // 添加一个特征值,特征不属于该规格
        try{
	        prodSpec.attachCharacteristicValue(characteristic3,"CPU", charValue3, false, validFor);
	        fail("添加一个特征值,特征不属于该规格");
        }catch(Exception e){}

        // 添加一个特征值为空
        try{
        	prodSpec.attachCharacteristicValue(characteristic,"CPU", null, false, validFor);
        	fail("添加一个特征值为空");
        }catch(Exception e){}
        
        // 添加一个特征值,特征为空
        try{
        	prodSpec.attachCharacteristicValue(null,"CPU", charValue2, false, validFor);
        	fail("添加一个特征值,特征为空");
        }catch(Exception e){}
        
        // 添加一个特征值,特征名为空
        try{
        	prodSpec.attachCharacteristicValue(characteristic,"", charValue2, false, validFor);
        	fail("添加一个特征值,特征名为空");
        }catch(Exception e){}
    }

    @Test
    public void testSpecifyDefaultCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        characteristic.addValue(charValue1);
        characteristic.addValue(charValue2);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        // 添加特征值
        prodSpec.attachCharacteristicValue(characteristic,"CPU", charValue1, true, validFor);
        prodSpec.attachCharacteristicValue(characteristic,"CPU", charValue2, false, validFor);

        Set<ProdSpecCharValueUse> expectCharValueUse = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(charValue2, true, validFor);
        ProdSpecCharValueUse charValueUse2 = new ProdSpecCharValueUse(charValue1, false, validFor);
        expectCharValueUse.add(charValueUse);
        expectCharValueUse.add(charValueUse2);
        
        Set<ProdSpecCharValueUse> expectCharValueUse2 = new HashSet<ProdSpecCharValueUse>();
        ProdSpecCharValueUse charValueUse3 = new ProdSpecCharValueUse(charValue1, true, validFor);
        ProdSpecCharValueUse charValueUse4 = new ProdSpecCharValueUse(charValue2, false, validFor);
        expectCharValueUse2.add(charValueUse3);
        expectCharValueUse2.add(charValueUse4);


        // 设置某一特征的默认值
        boolean retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,"CPU", charValue2);
        assertTrue("设置某一特征的默认值", retFlag);
        assertEquals("设置某一特征的默认值",expectCharValueUse,prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse());

        // 设置某一特征的默认值，传入的值对象为null
        try{
	        retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,"CPU", null);
	        fail("设置某一特征的默认值，传入的值对象为null");
        }catch(Exception e){}

        // 设置某一特征的默认值，传入的值对象为不是该特征所有的
        ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
        retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,"CPU", charValue3);
        assertFalse("设置某一特征的默认值，传入的值对象为不是该特征所有的", retFlag);
        assertEquals("设置某一特征的默认值，传入的值对象为不是该特征所有的",expectCharValueUse2,prodSpec.getProdSpecCharUse().iterator().next().getProdSpecCharValueUse());
        
        // 设置某一特征的默认值，传入的特征对象为null
        try{
	        retFlag = prodSpec.specifyDefaultCharacteristicValue(null,"CPU", charValue2);
	        fail("设置某一特征的默认值，传入的特征对象为null");
        }catch(Exception e){}
        
     // 设置某一特征的默认值，传入的特征名为空
        try{
	        retFlag = prodSpec.specifyDefaultCharacteristicValue(characteristic,"", charValue2);
	        fail("设置某一特征的默认值，传入的特征名为空");
        }catch(Exception e){}
    }

    @Test
    public void testRetrieveDefaultCharacteristicValue() {
        // 处理器
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[4]);
        // 重量
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[9]);
        ProductSpecCharacteristic characteristic4 = this.createChar(TestProductSpecificationData.specChar[9]);
        // 扩展分辨率
        ProductSpecCharacteristic characteristic5 = this.createChar(TestProductSpecificationData.specChar[3]);
        ProductSpecCharacteristic characteristic6 = this.createChar(TestProductSpecificationData.specChar[3]);

        // CPU: 2.7G
        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        // 2.9GHz
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        // 分辨率 1920 x 1200
        ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[7]);
        characteristic.addValue(charValue1);
        characteristic.addValue(charValue2);

        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        prodSpec.addCharacteristic(characteristic3, false, false, validFor, "重量");
        prodSpec.addCharacteristic(characteristic5, false, false, validFor, "分辨率");

        // 添加特征值
        prodSpec.attachCharacteristicValue(characteristic,"CPU", charValue1, true, validFor);
        prodSpec.attachCharacteristicValue(characteristic,"CPU", charValue2, false, validFor);
        prodSpec.attachCharacteristicValue(characteristic5,"CPU", charValue3, false, validFor);

        // 查询某一特征的默认值
        List<ProdSpecCharValueUse> defaultCharValues = prodSpec.retrieveDefaultCharacteristicValue(characteristic2,"CPU");
        assertNotNull("查询默认值", defaultCharValues);
        assertEquals("查询默认值", 1, defaultCharValues.size());

        // 查询某一特征的默认值，传入的特征没有值
        defaultCharValues = prodSpec.retrieveDefaultCharacteristicValue(characteristic4,"CPU");
        assertNotNull("查询某一特征的默认值，传入的特征没有值", defaultCharValues);
        assertEquals("查询某一特征的默认值，传入的特征没有值", 0, defaultCharValues.size());

        // 查询某一特征的默认值，传入的特征没有默认值
        defaultCharValues = prodSpec.retrieveDefaultCharacteristicValue(characteristic6,"CPU");
        assertNotNull("查询某一特征的默认值，传入的特征没有默认值", defaultCharValues);
        assertEquals("查询某一特征的默认值，传入的特征没有默认值", 0, defaultCharValues.size());
        logger.info("默认值查询失败！");

    }

    @Test
    public void testClearDefaultCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        // 添加特征值
        prodSpec.attachCharacteristicValue(characteristic,"CPU", charValue1, true, validFor);
        prodSpec.attachCharacteristicValue(characteristic,"CPU", charValue2, false, validFor);

        // 清除某一特征的默认值，传入的值对象为null
        boolean retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,"CPU", null);
        assertFalse("清除某一特征的默认值，传入的值对象为null", retFlag);

        // 清除某一特征的默认值，传入的值对象为不是该特征所有的
        ProductSpecCharacteristicValue charValue3 = this.createValue(TestProductSpecificationData.specCharValue[11]);
        retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,"CPU", charValue3);
        assertFalse("清除某一特征的默认值，传入的值对象为不是该特征所有的", retFlag);

        // 清除某一特征的默认值
        retFlag = prodSpec.clearDefaultCharacteristicValue(characteristic,"CPU", charValue1);
        assertTrue("清除某一特征的默认值", retFlag);

    }

    @Test
    public void testRetrieveCharacteristic() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "CPU");

        // 查询当前时间点的特征
        List<ProductSpecCharUse> charUses = prodSpec.retrieveCharacteristic(new Date());
        assertNotNull("查询当前时间点的特征", charUses);
        assertEquals("查询当前时间点的特征", 2, charUses.size());
    }

    @Test
    public void testRetrieveCharacteristicValue() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        // 添加特征值
        prodSpec.attachCharacteristicValue(characteristic,"", charValue1, true, validFor);
        prodSpec.attachCharacteristicValue(characteristic,"", charValue2, false, validFor);

        // 查询某一特征在当前时间点的值
        List<ProdSpecCharValueUse> charValueUses = prodSpec.retrieveCharacteristicValue(characteristic,"CPU", new Date());
        assertNotNull("查询某一特征在当前时间点的值", charValueUses);
        assertEquals("查询某一特征在当前时间点的值", 2, charValueUses.size());
    }

    @Test
    public void testRetrieveRootCharacteristic() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
        ProductSpecCharacteristic characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
        ProductSpecCharacteristic characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
        ProductSpecCharacteristic characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
        // 添加Char聚合关系
        characteristic2.addRelatedCharacteristic(characteristic3, RelationshipType.AGGREGATION.getValue(), 1, validFor);
        characteristic2.addRelatedCharacteristic(characteristic4, RelationshipType.AGGREGATION.getValue(), 1, validFor);
        characteristic2.addRelatedCharacteristic(characteristic5, RelationshipType.AGGREGATION.getValue(), 1, validFor);
        characteristic2.addRelatedCharacteristic(characteristic6, RelationshipType.AGGREGATION.getValue(), 1, validFor);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "尺寸和重量");
        prodSpec.addCharacteristic(characteristic3, false, false, validFor, "长");
        prodSpec.addCharacteristic(characteristic4, false, false, validFor, "宽");
        prodSpec.addCharacteristic(characteristic5, false, false, validFor, "高");
        prodSpec.addCharacteristic(characteristic6, false, false, validFor, "重量");

        // 查询规格的一级特征
        List<ProductSpecCharUse> rootCharUses = prodSpec.retrieveRootCharacteristic();
        assertNotNull("查询规格的一级特征", rootCharUses);
        assertEquals("查询规格的一级特征", 2, rootCharUses.size());
    }

    @Test
    public void getLeafCharacteristic() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        ProductSpecCharacteristic characteristic3 = this.createChar(TestProductSpecificationData.specChar[6]);
        ProductSpecCharacteristic characteristic4 = this.createChar(TestProductSpecificationData.specChar[7]);
        ProductSpecCharacteristic characteristic5 = this.createChar(TestProductSpecificationData.specChar[8]);
        ProductSpecCharacteristic characteristic6 = this.createChar(TestProductSpecificationData.specChar[9]);
        // 添加Char聚合关系
        characteristic2.addRelatedCharacteristic(characteristic3, RelationshipType.AGGREGATION.getValue(), 1, validFor);
        characteristic2.addRelatedCharacteristic(characteristic4, RelationshipType.AGGREGATION.getValue(), 1, validFor);
        characteristic2.addRelatedCharacteristic(characteristic5, RelationshipType.AGGREGATION.getValue(), 1, validFor);
        characteristic2.addRelatedCharacteristic(characteristic6, RelationshipType.AGGREGATION.getValue(), 1, validFor);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");
        prodSpec.addCharacteristic(characteristic2, false, false, validFor, "尺寸和重量");
        prodSpec.addCharacteristic(characteristic3, false, false, validFor, "长");
        prodSpec.addCharacteristic(characteristic4, false, false, validFor, "宽");
        prodSpec.addCharacteristic(characteristic5, false, false, validFor, "高");
        prodSpec.addCharacteristic(characteristic6, false, false, validFor, "重量");

        // 查询规格的某一特征的子特征
        List<ProductSpecCharUse> leafCharUses = prodSpec.retrieveLeafCharacteristic(characteristic2,"", new Date());
        assertNotNull("查询规格的某一特征的子特征", leafCharUses);
        assertEquals("查询规格的某一特征的子特征", 4, leafCharUses.size());

        // 查询规格的某一特征的子特征
        List<ProductSpecCharUse> leafCharUses2 = prodSpec.retrieveLeafCharacteristic(null,"", new Date());
        assertNull("查询规格的某一特征的子特征", leafCharUses2);
    }

    @Test
    public void testSpecifyCardinality() {
        ProductSpecCharacteristic characteristic = this.createChar(TestProductSpecificationData.specChar[4]);
        ProductSpecCharacteristic characteristic2 = this.createChar(TestProductSpecificationData.specChar[5]);
        // 添加特征
        prodSpec.addCharacteristic(characteristic, false, false, validFor, "CPU");

        ProductSpecCharacteristicValue charValue1 = this.createValue(TestProductSpecificationData.specCharValue[9]);
        ProductSpecCharacteristicValue charValue2 = this.createValue(TestProductSpecificationData.specCharValue[10]);
        // 添加特征值
        prodSpec.attachCharacteristicValue(characteristic,"", charValue1, true, validFor);
        prodSpec.attachCharacteristicValue(characteristic,"", charValue2, false, validFor);

        // 设置某一特征的Cardinality
        boolean retFlag = prodSpec.specifyCardinality(characteristic,"", 1, 5);
        assertTrue("设置某一特征的Cardinality", retFlag);

        // 设置Cardinality，特征为空
        retFlag = prodSpec.specifyCardinality(null,"", 1, 5);
        assertFalse("设置Cardinality，特征为空", retFlag);

        // 设置Cardinality，特征不是被用的
        retFlag = prodSpec.specifyCardinality(characteristic2,"", 1, 5);
        assertFalse("设置Cardinality，特征不是被用的", retFlag);
    }

    @Test
    public void testAddRelatedProdSpec() {

        // *********** Case1（正常分支） **************
        ProductSpecification targetProdSpec = new AtomicProductSpecification("T001", "AppleCare For iPhone",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        String type = RelationshipType.DEPENDENCY.getValue();
        TimePeriod validFor = new TimePeriod();
        List<ProductSpecificationRelationship> expectedRelatedSpecList = new ArrayList<ProductSpecificationRelationship>();
        ProductSpecificationRelationship expectedRelatedSpec = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec);
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec, type, validFor);
        assertEquals("添加关联规格", 1, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case2（添加同样数据，同一关联类型） **************
        // 再次添加一条同样数据
        ProductSpecification targetProdSpec2 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        try {
            this.srcProdSpec.addRelatedProdSpec(targetProdSpec2, type, validFor);
            fail("expected IllegalArgumentException for srcProdSpec");
        } catch (Exception e) {

        }
        assertEquals("添加同样数据，同一关联类型", 1, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("添加同样数据，同一关联类型", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // ***********testAddRelatedProdSpec Case3（添加不同数据，同一关联类型）
        // 再次添加一条不同数据,相同类型
        ProductSpecification targetProdSpec3 = new AtomicProductSpecification("T002", "AppleCare For iPhone2",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec3, type, validFor);
        // 构造期待数据
        ProductSpecificationRelationship expectedRelatedSpec3 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec3, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec3);
        assertEquals("再次添加一条不同数据,相同类型", 2, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("再次添加一条不同数据,相同类型", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case4（添加同样数据，不同关联类型） **************
        // 再次添加一条不同数据,相同类型
        String type4 = RelationshipType.AGGREGATION.getValue();
        ProductSpecification targetProdSpec4 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec4, type4, validFor);
        // 构造期待数据
        ProductSpecificationRelationship expectedRelatedSpec4 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec4, type4, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec4);
        assertEquals("添加同样数据，不同关联类型", 3, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals("添加同样数据，不同关联类型", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case5（添加与srcSpec相同的Spec） **************
        // 再次添加一条不同数据,相同类型
        try {
            this.srcProdSpec.addRelatedProdSpec(this.srcProdSpec, type4, validFor);
            fail("expected IllegalArgumentException for srcProdSpec");
        } catch (IllegalArgumentException e) {
        }
        assertEquals("添加与srcSpec相同的Spec", 3, this.srcProdSpec.getProdSpecRelationship().size());
        // 构造期待数据
        assertEquals("添加与srcSpec相同的Spec", expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
    }

    @Test
    public void testRetrieveRelatedProdSpec() {

        // *********** Case1（2个不同类型，取其中一种） *************
        String dependencyType = RelationshipType.DEPENDENCY.getValue();
        String aggregationType = RelationshipType.AGGREGATION.getValue();
        ProductSpecification targetProdSpecDependency1 = new AtomicProductSpecification("T001", "AppleCare For iPhone",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        ProductSpecification targetProdSpecAggregation1 = new AtomicProductSpecification("T002",
                "AppleCare For iPhone", "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        TimePeriod validFor = new TimePeriod();

        List<ProductSpecification> expectedRelatedSpecList = new ArrayList<ProductSpecification>();
        ProductSpecification expectedTargetProdSpec = new AtomicProductSpecification("T002", "AppleCare For iPhone",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        expectedRelatedSpecList.add(expectedTargetProdSpec);

        this.srcProdSpec.addRelatedProdSpec(targetProdSpecDependency1, dependencyType, validFor);
        this.srcProdSpec.addRelatedProdSpec(targetProdSpecAggregation1, aggregationType, validFor);
        List<ProductSpecification> productSpecificationList = this.srcProdSpec.retrieveRelatedProdSpec(aggregationType);
        assertEquals("存在多种类型关系，取其中一种关联关系", 1, productSpecificationList.size());
        assertEquals("存在多种类型关系，取其中一种关联关系", expectedRelatedSpecList, productSpecificationList);

        // *********** Case2（查询不存在该类型的数据） **************
        List<ProductSpecification> productSpecificationList2 = this.srcProdSpec
                .retrieveRelatedProdSpec(RelationshipType.EXCLUSIVITY.getValue());
        assertEquals("查询不存在该类型的数据", 0, productSpecificationList2.size());

        // *********** Case3（传入类型为null） **************
        try {
            List<ProductSpecification> productSpecificationList3 = this.srcProdSpec.retrieveRelatedProdSpec(null);
            fail("Case 3 ： type为null时未通过。");
        } catch (IllegalArgumentException e) {
        }

        // *********** Case4（没有关系数据，查询某类型的spec） **************
        this.srcProdSpec.getProdSpecRelationship().clear();
        List<ProductSpecification> productSpecificationList4 = this.srcProdSpec
                .retrieveRelatedProdSpec(aggregationType);
        assertEquals("没有关系数据，查询某类型的spec", 0, productSpecificationList4.size());
    }

    private void setExcetpProdSpec(ProductSpecCharacteristic characteristic, boolean canBeOveridden, boolean isPackage,
            TimePeriod validFor, String name, ProductSpecCharacteristicValue prodSpecCharVal, boolean isDefault) {
        Set<ProductSpecCharUse> prodSpecCharUse = new HashSet<ProductSpecCharUse>();
        ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic, canBeOveridden, isPackage, validFor, name);
        // 设置Value
        if (null != prodSpecCharVal) {
            Set<ProdSpecCharValueUse> prodSpecCharValueUse = new HashSet<ProdSpecCharValueUse>();
            ProdSpecCharValueUse charValueUse = new ProdSpecCharValueUse(prodSpecCharVal, isDefault, validFor);
            prodSpecCharValueUse.add(charValueUse);
            charUse.setProdSpecCharValueUse(prodSpecCharValueUse);
        }
        prodSpecCharUse.add(charUse);
        expectProdSpec.setProdSpecCharUse(prodSpecCharUse);
    }

    private ProductSpecCharacteristicValue createValue(Object[] obj) {
        ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue((String) obj[1],
                (boolean) obj[2], (String) obj[3], (TimePeriod) obj[4], (String) obj[5]);
        return charValue;
    }

    private ProductSpecCharacteristic createChar(Object[] obj) {
        ProductSpecCharacteristic specChar = new ProductSpecCharacteristic((String) obj[0], (String) obj[1],
                (String) obj[2], (TimePeriod) obj[3], (String) obj[4], (Integer) obj[5], (Integer) obj[5]);
        return specChar;
    }
}
