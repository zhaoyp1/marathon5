package com.asiainfo.baas.marathon5.specification;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.asiainfo.baas.common.ProductSpecificationStatus;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecification;

@RunWith(Parameterized.class)
public class ProductSpecificationTest {

			private ProductSpecification prodSpec = null;
			private ProductSpecification targetProdSpec = null;
			private boolean retFlag = false;
			private String retMsg = "";
			@Before
			public  void initSpecCharValue(){
				prodSpec = new AtomicProductSpecification("AC001", "AppleCare For iPhone", "AppleCare",ProductSpecificationStatus.PLANNED.getValue());
			}
			@Parameters
			public static Iterable<Object[]> data() { 
		       return Arrays.asList(new Object[][] { 
		    		    {false,"两个不相同规格对象比较", "mac-13", "13-inch MacBook Pro", "apple", ProductSpecificationStatus.PLANNED.getValue()},
		    		    {true,"两个相同规格对象比较", "AC001", "AppleCare For iPhone", "AppleCare",ProductSpecificationStatus.PLANNED.getValue() }
		               });  
			}  
			
			public ProductSpecificationTest(boolean retFlag,String retMsg,String productNumber,String name,String brand,String lifecycleStatus){
				this.targetProdSpec = new AtomicProductSpecification(productNumber, name,brand, lifecycleStatus);
				this.retFlag = retFlag;
				this.retMsg = retMsg;
			}


			@Test
			public void testEquals() {
				boolean realRetFlag = false;
				realRetFlag = this.prodSpec.equals(this.targetProdSpec);
				assertEquals(this.retFlag,realRetFlag);
				System.out.println(this.retMsg+" 比较结果："+realRetFlag);
			}

		}




