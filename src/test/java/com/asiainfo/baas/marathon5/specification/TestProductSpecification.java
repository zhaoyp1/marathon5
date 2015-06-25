package com.asiainfo.baas.marathon5.specification;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.asiainfo.baas.common.ProductConst;
import com.asiainfo.baas.marathon.baseType.Money;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.specification.AtomicProductSpecification;
import com.asiainfo.baas.marathon.specification.CompositeProductSpecification;
import com.asiainfo.baas.marathon.specification.ProdSpecCharValueUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharUse;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristic;
import com.asiainfo.baas.marathon.specification.ProductSpecCharacteristicValue;
import com.asiainfo.baas.marathon.specification.ProductSpecification;
import com.asiainfo.baas.marathon.specification.ProductSpecificationCost;
import com.asiainfo.baas.marathon.specification.ProductSpecificationVersion;
import com.asiainfo.baas.marathon5.common.CommonUtils;

public class TestProductSpecification {

    @Test
    public void addCost() {
        Money cost = new Money();
        cost.amount = 11;
        cost.units = "mine";
        TimePeriod timePeriod = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
        AtomicProductSpecification prodSpec = new AtomicProductSpecification("1342", "343", "", "");
        prodSpec.addCost(cost, timePeriod);
    }

    @Test
    public void queryCost() {
        Money cost = new Money();
        cost.amount = 11;
        cost.units = "mine";
        TimePeriod timePeriod = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        AtomicProductSpecification prodSpec = new AtomicProductSpecification("1342", "343", "", "");
        prodSpec.addCost(cost, timePeriod);
        try {
            ProductSpecificationCost[] prodSpecCostList = prodSpec.queryCost(format.parse("2015-07-20 23:59:59"));
            for (ProductSpecificationCost productSpecificationCost : prodSpecCostList) {
                System.out.println(productSpecificationCost.getCostToBusiness().amount + ","
                        + productSpecificationCost.getCostToBusiness().units + ","
                        + format.format(productSpecificationCost.getValidFor().startDateTime) + " ,"
                        + format.format(productSpecificationCost.getValidFor().endDateTime));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetVersion() {
        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification atomicProductSpecification = new AtomicProductSpecification(productNumber, name,
                brand, lifecycleStatus);

        String version = "1.0.0";
        String description = "Available in silver, gold, and space gray, iPhone 6 features an A8 chip, Touch ID, faster LTE wireless, a new 8MP iSight camera with Focus Pixels, and iOS 8";
        Date revisionDate = new Date();
        TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00", "2015-07-21 23:59:59");
        try {
            atomicProductSpecification.setVersion(version, description, revisionDate, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetCurrentVersion() {
        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification atomicProductSpecification = new AtomicProductSpecification(productNumber, name,
                brand, lifecycleStatus);

        String version = "1.0.0";
        String description = "Available in silver, gold, and space gray, iPhone 6 features an A8 chip, Touch ID, faster LTE wireless, a new 8MP iSight camera with Focus Pixels, and iOS 8";
        Date revisionDate = new Date();
        TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        try {
            atomicProductSpecification.setVersion(version, description, revisionDate, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProductSpecificationVersion[] currentVersion = atomicProductSpecification.getCurrentVersion();

        StringBuilder outText = new StringBuilder();
        for (ProductSpecificationVersion productSpecificationVersion : currentVersion) {
            outText.append(productSpecificationVersion.getProdSpecRevisionType());
            outText.append(" ");
            outText.append(productSpecificationVersion.getProdSpecRevisionNumber());
            outText.append(" ");
            outText.append(productSpecificationVersion.getDescription());
            outText.append(" ");
            outText.append(format.format(productSpecificationVersion.getProdSpecRevisionDate()));
            outText.append(" ");
            outText.append(format.format(productSpecificationVersion.getValidFor().startDateTime));
            outText.append(" ");
            outText.append(format.format(productSpecificationVersion.getValidFor().endDateTime));
            outText.append("\n");
        }

        System.out.println(outText.toString());
    }

    @Test
    public void testGetCurrentVersionString() {
        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification atomicProductSpecification = new AtomicProductSpecification(productNumber, name,
                brand, lifecycleStatus);

        String version = "1.0.0";
        String description = "Available in silver, gold, and space gray, iPhone 6 features an A8 chip, Touch ID, faster LTE wireless, a new 8MP iSight camera with Focus Pixels, and iOS 8";
        Date revisionDate = new Date();
        TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");
        try {
            atomicProductSpecification.setVersion(version, description, revisionDate, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String currentVersion = atomicProductSpecification.getCurrentVersionString();

        System.out.println(currentVersion);
    }

    @Test
    public void testAddRelatedProdSpec() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);

        String productNumber1 = "AC001";
        String name1 = "AppleCare For iPhone";
        String brand1 = "AppleCare";
        String lifecycleStatus1 = "1";

        AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
                brand1, lifecycleStatus1);

        // type:1=ÒÀÀµ 2=»¥³â
        String type = ProductConst.RELATIONSHIP_TYPE_DEPENDENCY;
        TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");
        try {
            iPhone5SSpecification.addRelatedProdSpec(appleCareSpecification, type, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryRelatedProdSpec() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);

        String productNumber1 = "AC001";
        String name1 = "AppleCare For iPhone";
        String brand1 = "AppleCare";
        String lifecycleStatus1 = "1";

        AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
                brand1, lifecycleStatus1);

        // type:1=ÒÀÀµ 2=»¥³â
        String type = ProductConst.RELATIONSHIP_TYPE_EXCLUSIVITY;
        TimePeriod validFor = new TimePeriod("2015-02-03 12:00:00", "2015-09-21 23:59:59");
        try {
            iPhone5SSpecification.addRelatedProdSpec(appleCareSpecification, type, validFor);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProductSpecification[] productSpecification = iPhone5SSpecification.queryRelatedProdSpec(type);
        CommonUtils.printProperty(productSpecification, null, null);

    }

    @Test
    public void testAddSubProdSpec() {

        String productNumber = "5S";
        String name = "iPhone5S";
        String brand = "Apple iPhone";
        String lifecycleStatus = "1";

        AtomicProductSpecification iPhone5SSpecification = new AtomicProductSpecification(productNumber, name, brand,
                lifecycleStatus);

        String productNumber1 = "AC001";
        String name1 = "AppleCare For iPhone";
        String brand1 = "AppleCare";
        String lifecycleStatus1 = "1";

        AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
                brand1, lifecycleStatus1);

        String productNumber2 = "iPhone5s&AC001";
        String name2 = "iPhone5s + AppleCare For iPhone";
        String brand2 = "AppleCare";
        String lifecycleStatus2 = "1";

        CompositeProductSpecification compositeProductSpecification = new CompositeProductSpecification(productNumber2,
                name2, brand2, lifecycleStatus2);

        compositeProductSpecification.addSubProdSpec(iPhone5SSpecification);
        compositeProductSpecification.addSubProdSpec(appleCareSpecification);

        System.out.println("compositeProductSpecification £º");
        CommonUtils.printProperty(null, null, compositeProductSpecification);
        System.out.println("SubSpecification £º");
        CommonUtils.printProperty(null, compositeProductSpecification.getProdSpec(), null);
    }
    
    @Test
    public void addCharacteristic(){
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			String id = "1112";
			String name = "ÑÕÉ«" ;
			String valueType = "Text";
			String unique = "·ñ";
			int minCardinality = 1; 
			int maxCardinality = 3;
			ProductSpecCharacteristic specChar = new ProductSpecCharacteristic(id,name,valueType,validFor,unique,minCardinality,maxCardinality);
			
			String productNumber1 = "AC001";
	        String name1 = "AppleCare For iPhone";
	        String brand1 = "AppleCare";
	        String lifecycleStatus1 = "1";
			AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
	                brand1, lifecycleStatus1);
			appleCareSpecification.addCharacteristic(specChar, false, false, validFor);
			CommonUtils.printProperty(null, null, appleCareSpecification);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void attachCharacteristicValue(){
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			ProductSpecCharacteristic specChar = createChar("1112","ÑÕÉ«","Text","·ñ",1,3);
			
			String productNumber1 = "AC001";
	        String name1 = "AppleCare For iPhone";
	        String brand1 = "AppleCare";
	        String lifecycleStatus1 = "1";
			AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
	                brand1, lifecycleStatus1);
			appleCareSpecification.addCharacteristic(specChar, false, false, validFor);
			
			ProductSpecCharacteristicValue charValue = createValue("ºì","",false);
			
			appleCareSpecification.attachCharacteristicValue(specChar,charValue,false,validFor);
			CommonUtils.printProperty(null, null, appleCareSpecification);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void specifyDefaultCharacteristicValue(){
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			
			ProductSpecCharacteristic specChar = createChar("1112","ÑÕÉ«","Text","·ñ",1,3);
			String productNumber1 = "AC001";
	        String name1 = "AppleCare For iPhone";
	        String brand1 = "AppleCare";
	        String lifecycleStatus1 = "1";
			AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
	                brand1, lifecycleStatus1);
			appleCareSpecification.addCharacteristic(specChar, false, false, validFor);
			
			ProductSpecCharacteristicValue charValue = createValue("ºì","",false);
			appleCareSpecification.attachCharacteristicValue(specChar,charValue,false,validFor);
			appleCareSpecification.specifyDefaultCharacteristicValue(specChar,charValue);
			CommonUtils.printProperty(null, null, appleCareSpecification);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    @Test
    public void retrieveCharacteristic(){
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			
			ProductSpecCharacteristic specChar = createChar("1112","ÑÕÉ«","Text","·ñ",1,3);
			String productNumber1 = "AC001";
	        String name1 = "AppleCare For iPhone";
	        String brand1 = "AppleCare";
	        String lifecycleStatus1 = "1";
			AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
	                brand1, lifecycleStatus1);
			appleCareSpecification.addCharacteristic(specChar, false, false, validFor);
			
			ProductSpecCharacteristicValue charValue = createValue("ºì","",false);
			appleCareSpecification.attachCharacteristicValue(specChar,charValue,false,validFor);
			appleCareSpecification.specifyDefaultCharacteristicValue(specChar,charValue);
			ProductSpecCharUse[] charUses = appleCareSpecification.retrieveCharacteristic(new Date());
			CommonUtils.printProperty(charUses, null, null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void retrieveCharacteristicValue(){
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			
			ProductSpecCharacteristic specChar = createChar("1112","ÑÕÉ«","Text","·ñ",1,3);
			String productNumber1 = "AC001";
	        String name1 = "AppleCare For iPhone";
	        String brand1 = "AppleCare";
	        String lifecycleStatus1 = "1";
			AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
	                brand1, lifecycleStatus1);
			appleCareSpecification.addCharacteristic(specChar, false, false, validFor);
			
			ProductSpecCharacteristicValue charValue = createValue("ºì","",false);
			appleCareSpecification.attachCharacteristicValue(specChar,charValue,false,validFor);
			appleCareSpecification.specifyDefaultCharacteristicValue(specChar,charValue);
			ProdSpecCharValueUse[] charValueUses = appleCareSpecification.retrieveCharacteristicValue(specChar,new Date());
			CommonUtils.printProperty(charValueUses, null, null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void getRootCharacteristic(){
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			
			ProductSpecCharacteristic specChar = createChar("1112","ÑÕÉ«","Text","·ñ",1,3);
			ProductSpecCharacteristic specChar2 = createBundledChar();
			String productNumber1 = "AC001";
	        String name1 = "AppleCare For iPhone";
	        String brand1 = "AppleCare";
	        String lifecycleStatus1 = "1";
			AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
	                brand1, lifecycleStatus1);
			appleCareSpecification.addCharacteristic(specChar, false, false, validFor);
			appleCareSpecification.addCharacteristic(specChar2, false, false, validFor);
			
			ProductSpecCharacteristicValue charValue = createValue("ºì","",false);
			appleCareSpecification.attachCharacteristicValue(specChar,charValue,false,validFor);
			appleCareSpecification.specifyDefaultCharacteristicValue(specChar,charValue);
			ProductSpecCharUse[] charUses = appleCareSpecification.getRootCharacteristic();
			CommonUtils.printProperty(charUses, null, null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void getLeafCharacteristic(){
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			ProductSpecCharacteristic specChar = createChar("1112","ÑÕÉ«","Text","·ñ",1,3);
			ProductSpecCharacteristic specChar2 = createBundledChar();
			ProductSpecCharacteristic specChar3 = createChar("11231","³¤","Number","Ã×",1,1);
			
			String productNumber1 = "AC001";
	        String name1 = "AppleCare For iPhone";
	        String brand1 = "AppleCare";
	        String lifecycleStatus1 = "1";
			AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
	                brand1, lifecycleStatus1);
			appleCareSpecification.addCharacteristic(specChar2, false, false, validFor);
			
			ProductSpecCharacteristicValue charValue = createValue("ºì","",false);
			appleCareSpecification.attachCharacteristicValue(specChar,charValue,false,validFor);
			appleCareSpecification.specifyDefaultCharacteristicValue(specChar,charValue);
			ProductSpecCharUse[] charUses = appleCareSpecification.getLeafCharacteristic(specChar,new Date());
			CommonUtils.printProperty(charUses, null, null);
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void setCardinality(){
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
    	try {
			validFor.startDateTime = format.parse("2015-06-24 00:00:00");
			validFor.endDateTime = format.parse("2015-08-24 00:00:00");
			
			ProductSpecCharacteristic specChar = createChar("1112","ÑÕÉ«","Text","·ñ",1,3);
			String productNumber1 = "AC001";
	        String name1 = "AppleCare For iPhone";
	        String brand1 = "AppleCare";
	        String lifecycleStatus1 = "1";
			AtomicProductSpecification appleCareSpecification = new AtomicProductSpecification(productNumber1, name1,
	                brand1, lifecycleStatus1);
			appleCareSpecification.addCharacteristic(specChar, false, false, validFor);
			
			ProductSpecCharacteristicValue charValue = createValue("ºì","",false);
			appleCareSpecification.attachCharacteristicValue(specChar,charValue,false,validFor);
			appleCareSpecification.specifyDefaultCharacteristicValue(specChar,charValue);
			appleCareSpecification.setCardinality(specChar,2,5);
			CommonUtils.printProperty(null, null, appleCareSpecification.getProdSpecCharUse().get(0));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    }
    private ProductSpecCharacteristicValue createValue(String value,String unitOfMeasure,boolean isDefault) throws ParseException{
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		validFor.startDateTime = format.parse("2015-06-24 00:00:00");
		validFor.endDateTime = format.parse("2015-08-24 00:00:00");
    	String valueType = "Text";
		ProductSpecCharacteristicValue charValue = new ProductSpecCharacteristicValue(valueType,isDefault,unitOfMeasure,validFor,value);
		return charValue;
    }
    
    private ProductSpecCharacteristic createChar(String id,String name,String valueType,String unique,int minCardinality,int maxCardinality) throws ParseException{
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		validFor.startDateTime = format.parse("2015-06-24 00:00:00");
		validFor.endDateTime = format.parse("2015-08-24 00:00:00");
		ProductSpecCharacteristic specChar = new ProductSpecCharacteristic(id,name,valueType,validFor,unique,minCardinality,maxCardinality);
		return specChar;
    }
    
    private ProductSpecCharacteristic createBundledChar() throws ParseException{
    	TimePeriod validFor = new TimePeriod();
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		validFor.startDateTime = format.parse("2015-06-24 00:00:00");
		validFor.endDateTime = format.parse("2015-08-24 00:00:00");
		ProductSpecCharacteristic specChar = createChar("1123","³ß´ç","Text","",1,1);
		ProductSpecCharacteristic subSpecChar1 = createChar("11231","³¤","Number","Ã×",1,1);
		ProductSpecCharacteristic subSpecChar2 = createChar("11232","¿í","Number","Ã×",1,1);
		ProductSpecCharacteristic subSpecChar3 = createChar("11233","¸ß","Number","Ã×",1,1);
		specChar.addRelatedCharacteristic(subSpecChar1, ProductConst.RELATIONSHIP_TYPE_AGGREGATION, 1, validFor);
		specChar.addRelatedCharacteristic(subSpecChar2, ProductConst.RELATIONSHIP_TYPE_AGGREGATION, 2, validFor);
		specChar.addRelatedCharacteristic(subSpecChar3, ProductConst.RELATIONSHIP_TYPE_AGGREGATION, 3, validFor);
		return specChar;
    }
}
