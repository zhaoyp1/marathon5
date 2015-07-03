package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import com.asiainfo.baas.common.ProductSpecificationStatus;
import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecificationRelationship;

public class ProductSpecificationRelationshipTest {

    private Logger logger = Logger.getLogger(ProductSpecificationRelationshipTest.class);

    private ProductSpecification srcProdSpec = null;

    @Before
    public void initSrcProdSpec() {
        srcProdSpec = new AtomicProductSpecification("S001", "iPhone6", "Apple iPhone",
                ProductSpecificationStatus.PLANNED.getValue());
    }

    @Test
    public void testAddRelatedProdSpec() {

        // -------------testAddRelatedProdSpec start---------------
        // *********** Case1（正常分支） start**************
        ProductSpecification targetProdSpec = new AtomicProductSpecification("T001", "AppleCare For iPhone",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        String type = RelationshipType.DEPENDENCY.getValue();
        TimePeriod validFor = new TimePeriod();

        List<ProductSpecificationRelationship> expectedRelatedSpecList = new ArrayList<ProductSpecificationRelationship>();
        ProductSpecificationRelationship expectedRelatedSpec = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec);

        this.srcProdSpec.addRelatedProdSpec(targetProdSpec, type, validFor);
        assertEquals(1, this.srcProdSpec.getProdSpecRelationship().size());
        for (int i = 0; i < expectedRelatedSpecList.size(); i++) {
            assertEquals(expectedRelatedSpecList.get(i).getTargetProdSpec().getName(), this.srcProdSpec
                    .getProdSpecRelationship().get(i).getTargetProdSpec().getName());
        }
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
        // *********** Case1 end**************

        // *********** Case2（添加同样数据，同一关联类型） start**************
        // 再次添加一条同样数据
        ProductSpecification targetProdSpec2 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        try {
            this.srcProdSpec.addRelatedProdSpec(targetProdSpec2, type, validFor);
            fail("expected IllegalArgumentException for srcProdSpec");
        } catch (Exception e) {

        }
        assertEquals(1, this.srcProdSpec.getProdSpecRelationship().size());
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
        logger.info("添加后src内的relationship：");
        for (int i = 0; i < this.srcProdSpec.getProdSpecRelationship().size(); i++) {
            logger.info(this.srcProdSpec.getProdSpecRelationship().get(i).toString());
        }
        // ***********testAddRelatedProdSpec Case2 end**************

        // ***********testAddRelatedProdSpec Case3（添加不同数据，同一关联类型）
        // start**************
        // 再次添加一条不同数据,相同类型
        ProductSpecification targetProdSpec3 = new AtomicProductSpecification("T002", "AppleCare For iPhone2",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec3, type, validFor);
        assertEquals(2, this.srcProdSpec.getProdSpecRelationship().size());
        // 构造期待数据
        ProductSpecificationRelationship expectedRelatedSpec3 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec3, type, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec3);
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
        logger.info("添加后src内的relationship：");
        for (int i = 0; i < this.srcProdSpec.getProdSpecRelationship().size(); i++) {
            logger.info(this.srcProdSpec.getProdSpecRelationship().get(i).toString());
        }
        // *********** Case3 end**************

        // *********** Case4（添加同样数据，不同关联类型） start**************
        // 再次添加一条不同数据,相同类型
        String type4 = RelationshipType.AGGREGATION.getValue();
        ProductSpecification targetProdSpec4 = new AtomicProductSpecification("T001", "AppleCare For iPhone2",
                "AppleCare", ProductSpecificationStatus.PLANNED.getValue());
        this.srcProdSpec.addRelatedProdSpec(targetProdSpec4, type4, validFor);
        assertEquals(3, this.srcProdSpec.getProdSpecRelationship().size());
        // 构造期待数据
        ProductSpecificationRelationship expectedRelatedSpec4 = new ProductSpecificationRelationship(srcProdSpec,
                targetProdSpec4, type4, validFor);
        expectedRelatedSpecList.add(expectedRelatedSpec4);
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());

        // *********** Case5（添加srcSpec） start**************
        // 再次添加一条不同数据,相同类型
        try {
            this.srcProdSpec.addRelatedProdSpec(this.srcProdSpec, type4, validFor);
            fail("expected IllegalArgumentException for srcProdSpec");
        } catch (IllegalArgumentException e) {
        }

        assertEquals(3, this.srcProdSpec.getProdSpecRelationship().size());
        // 构造期待数据
        assertEquals(expectedRelatedSpecList, srcProdSpec.getProdSpecRelationship());
        // *********** Case4 end**************
    }

    @Test
    public void testRetrieveRelatedProdSpec() {

        // -------------testretrieveRelatedProdSpec start---------------
        // *********** Case1（2个不同类型，取其中一种） start*************
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
        assertEquals(1, productSpecificationList.size());
        assertEquals(expectedRelatedSpecList, productSpecificationList);
        // *********** Case1 end**************

        // *********** Case2（查询不存在该类型的数据） start**************
        List<ProductSpecification> productSpecificationList2 = this.srcProdSpec
                .retrieveRelatedProdSpec(RelationshipType.EXCLUSIVITY.getValue());
        assertEquals(0, productSpecificationList2.size());
        // *********** Case2 end**************

        // *********** Case3（传入类型为null） start**************
        try {
            List<ProductSpecification> productSpecificationList3 = this.srcProdSpec.retrieveRelatedProdSpec(null);
            fail("Case 3 ： type为null时未通过。");
        } catch (IllegalArgumentException e) {
        }
        // *********** Case3 end**************

        // *********** Case4（没有关系数据，查询某类型的spec） start**************
        this.srcProdSpec.getProdSpecRelationship().clear();
        List<ProductSpecification> productSpecificationList4 = this.srcProdSpec
                .retrieveRelatedProdSpec(aggregationType);
        assertEquals(0, productSpecificationList4.size());
        // *********** Case4 end**************"
        // -------------testretrieveRelatedProdSpec end---------------
    }

}
