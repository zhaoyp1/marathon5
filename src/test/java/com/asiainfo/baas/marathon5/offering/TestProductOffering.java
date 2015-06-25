package com.asiainfo.baas.marathon5.offering;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.BundledProductOffering;
import com.asiainfo.baas.marathon.offering.ProductOffering;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class TestProductOffering {
	TimePeriod validFor = new TimePeriod();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	@Test
	public void addRelatedOffering(){
		try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			String id = "2233"; 
			String name = "MacBook Pro";
			String status = "create";
			String description = "新品上市";
			
			String productNumber = "11234";
			String specName = "13 英寸配备 Retina显示屏";
			String brand = "苹果";
			String lifecycleStatus = "active";
			ProductSpecification prodSpec = new AtomicProductSpecification(productNumber,specName,brand,lifecycleStatus); 
			ProductOffering offering = new SimpleProductOffering(id,name,validFor,status,prodSpec,description);
			String id2 = "2234"; 
			String name2 = "苹果鼠标";
			String status2 = "create";
			String description2 = "苹果单品";
			
			String productNumber2 = "11235";
			String specName2 = "Magic Mouse";
			String brand2 = "苹果";
			String lifecycleStatus2 = "active";
			ProductSpecification prodSpec2 = new AtomicProductSpecification(productNumber2,specName2,brand2,lifecycleStatus2); 
			ProductOffering offering2 = new SimpleProductOffering(id2,name2,validFor,status2,prodSpec2,description2);
			offering.addRelatedOffering(offering2, "组合", validFor);
			CommonUtils.printProperty(null, null,offering);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void queryRelatedOffering(){
		try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			String id = "2233"; 
			String name = "MacBook Pro";
			String status = "create";
			String description = "新品上市";
			
			String productNumber = "11234";
			String specName = "13 英寸配备 Retina显示屏";
			String brand = "苹果";
			String lifecycleStatus = "active";
			ProductSpecification prodSpec = new AtomicProductSpecification(productNumber,specName,brand,lifecycleStatus); 
			ProductOffering offering = new SimpleProductOffering(id,name,validFor,status,prodSpec,description);
			String id2 = "2234"; 
			String name2 = "苹果鼠标";
			String status2 = "create";
			String description2 = "苹果单品";
			
			String productNumber2 = "11235";
			String specName2 = "Magic Mouse";
			String brand2 = "苹果";
			String lifecycleStatus2 = "active";
			ProductSpecification prodSpec2 = new AtomicProductSpecification(productNumber2,specName2,brand2,lifecycleStatus2); 
			ProductOffering offering2 = new SimpleProductOffering(id2,name2,validFor,status2,prodSpec2,description2);
			offering.addRelatedOffering(offering2, "组合", validFor);
			ProductOffering [] offerings = offering.queryRelatedOffering("组合", new Date());
			CommonUtils.printProperty(offerings, null,null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addSubOffering(){
		try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			String id = "2233"; 
			String name = "MacBook Pro";
			String status = "create";
			String description = "新品上市";
			
			String productNumber = "11234";
			String specName = "13 英寸配备 Retina显示屏";
			String brand = "苹果";
			String lifecycleStatus = "active";
			ProductSpecification prodSpec = new AtomicProductSpecification(productNumber,specName,brand,lifecycleStatus); 
			ProductOffering offering = new SimpleProductOffering(id,name,validFor,status,prodSpec,description);
			String id2 = "2234"; 
			String name2 = "苹果鼠标";
			String status2 = "create";
			String description2 = "苹果单品";
			
			String productNumber2 = "11235";
			String specName2 = "Magic Mouse";
			String brand2 = "苹果";
			String lifecycleStatus2 = "active";
			ProductSpecification prodSpec2 = new AtomicProductSpecification(productNumber2,specName2,brand2,lifecycleStatus2); 
			ProductOffering offering2 = new SimpleProductOffering(id2,name2,validFor,status2,prodSpec2,description2);
			BundledProductOffering bundledOffering = new BundledProductOffering(id,name,validFor,status,description);
			bundledOffering.addSubOffering(offering);
			CommonUtils.printProperty(null, null,bundledOffering);
			bundledOffering.addSubOffering(offering2,3,5);
			CommonUtils.printProperty(null, null,bundledOffering);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getSubOffering(){
		try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			String id = "2233"; 
			String name = "MacBook Pro";
			String status = "create";
			String description = "新品上市";
			
			String productNumber = "11234";
			String specName = "13 英寸配备 Retina显示屏";
			String brand = "苹果";
			String lifecycleStatus = "active";
			ProductSpecification prodSpec = new AtomicProductSpecification(productNumber,specName,brand,lifecycleStatus); 
			ProductOffering offering = new SimpleProductOffering(id,name,validFor,status,prodSpec,description);
			String id2 = "2234"; 
			String name2 = "苹果鼠标";
			String status2 = "create";
			String description2 = "苹果单品";
			
			String productNumber2 = "11235";
			String specName2 = "Magic Mouse";
			String brand2 = "苹果";
			String lifecycleStatus2 = "active";
			ProductSpecification prodSpec2 = new AtomicProductSpecification(productNumber2,specName2,brand2,lifecycleStatus2); 
			ProductOffering offering2 = new SimpleProductOffering(id2,name2,validFor,status2,prodSpec2,description2);
			BundledProductOffering bundledOffering = new BundledProductOffering(id,name,validFor,status,description);
			bundledOffering.addSubOffering(offering);
			CommonUtils.printProperty(null, null,bundledOffering);
			bundledOffering.addSubOffering(offering2,3,5);
			CommonUtils.printProperty(null, null,bundledOffering);
			ProductOffering[] offerings = bundledOffering.getSubOffering();
			CommonUtils.printProperty(offerings, null,null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
