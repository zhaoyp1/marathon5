package com.asiainfo.baas.marathon5.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.Money;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecificationCost;

public class TestProductSpecification {

	@Test
	public void addCost(){
		Money cost=new Money();
		cost.amount=11;
		cost.units="mine";
		TimePeriod timePeriod=new TimePeriod();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		try {
			timePeriod.startDateTime=format.parse("2015-02-03 12:00:00");
			timePeriod.endDateTime=format.parse("2015-07-21 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		AtomicProductSpecification prodSpec=new AtomicProductSpecification("1342","343","","");
		prodSpec.addCost(cost, timePeriod);
	}
	@Test
	public void queryCost(){
		Money cost=new Money();
		cost.amount=11;
		cost.units="mine";
		TimePeriod timePeriod=new TimePeriod();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		try {
			timePeriod.startDateTime=format.parse("2015-02-03 12:00:00");
			timePeriod.endDateTime=format.parse("2015-07-21 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		AtomicProductSpecification prodSpec=new AtomicProductSpecification("1342","343","","");
		prodSpec.addCost(cost, timePeriod);
		try {
			ProductSpecificationCost[] prodSpecCostList= prodSpec.queryCost(format.parse("2015-07-20 23:59:59"));
			for (ProductSpecificationCost productSpecificationCost : prodSpecCostList) {
				System.out.println(productSpecificationCost.getCostToBusiness().amount+","+productSpecificationCost.getCostToBusiness().units+","+format.format(productSpecificationCost.getValidFor().startDateTime)+" ,"+format.format(productSpecificationCost.getValidFor().endDateTime));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	} 
}
