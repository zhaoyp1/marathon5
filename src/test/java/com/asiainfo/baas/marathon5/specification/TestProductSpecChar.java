package com.asiainfo.baas.marathon5.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;

public class TestProductSpecChar {

	@Test
	public void addRelatedProductSpecChar(){
		TimePeriod validFor=new TimePeriod();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		try {
			validFor.startDateTime=format.parse("2015-02-03 12:00:00");
			validFor.endDateTime=format.parse("2015-07-21 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ProductSpecCharacteristicValue srcCharValue=new ProductSpecCharacteristicValue("number", true, "min", validFor, "5");
		ProductSpecCharacteristicValue targetCharValue=new ProductSpecCharacteristicValue("number", true, "min", validFor, "5");
		srcCharValue.addRelatedCharValue(targetCharValue, "dependency", validFor);
	}
	@Test
	public void queryRelatedProductSpecChar() throws Exception{
		TimePeriod validFor=new TimePeriod();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		try {
			validFor.startDateTime=format.parse("2015-02-03 12:00:00");
			validFor.endDateTime=format.parse("2015-07-21 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ProductSpecCharacteristicValue srcCharValue=new ProductSpecCharacteristicValue("number", true, "min", validFor, "5");
		ProductSpecCharacteristicValue targetCharValue=new ProductSpecCharacteristicValue("number", true, "min", validFor, "5");
		ProductSpecCharacteristicValue targetCharValue2=new ProductSpecCharacteristicValue("number", true, "min", validFor, "5","9","closed");

		srcCharValue.addRelatedCharValue(targetCharValue, "dependency", validFor);
		srcCharValue.addRelatedCharValue(targetCharValue2, "dependency", validFor);
		List<ProductSpecCharacteristicValue> charValues=srcCharValue.queryRelatedCharValue("dependency", format.parse("2015-07-20 23:59:59"));
		for (int i = 0; i < charValues.size(); i++) {
			if(charValues.get(i).getValue()==null){
				System.out.println(charValues.get(i).getValueFrom()+","+charValues.get(i).getValueTo());
			}else{
				System.out.println(charValues.get(i).getValue());
			}
		}
	}
	
}
