package com.asiainfo.baas.marathon.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.asiainfo.baas.common.RelationshipType;
import com.asiainfo.baas.marathon.baseType.Money;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;
import com.asiainfo.baas.marathon5.common.CommonUtils;

/**
 * A detailed description of a tangible or intangible object made available
 * externally in the form of a ProductOffering to Customers or other Parties
 * playing a PartyRole. A ProductSpecification may consist of other
 * ProductSpecifications supplied together as a collection. Members of the
 * collection may be offered in their own right. ProductSpecifications may also
 * exist within groupings, such as ProductCategories, ProductLines, and
 * ProductTypes.
 */
public abstract class ProductSpecification {

    private static Logger logger = Logger.getLogger(ProductSpecification.class);

    private List<ProductSpecificationCost> productSpecificationCost;
    private List<SimpleProductOffering> productOffering;
    private List<ProductSpecificationVersion> productSpecificationVersion;
    private List<CompositeProductSpecification> compositeProdSpec;
    private List<ProductSpecificationRelationship> prodSpecRelationship;
    private Set<ProductSpecCharUse> prodSpecCharUse113;
    private List<ProductSpecificationType> prodSpecType;
    /**
     * The name of the product specification.
     */
    private String name;
    /**
     * The manufacturer or trademark of the specification.
     */
    private String brand;
    /**
     * A narrative that explains in detail what the product spec is.
     */
    private String description;
    /**
     * An identification number assigned to uniquely identify the specification.
     */
    private String productNumber;
    /**
     * The period for which the product specification is valid.
     */
    private TimePeriod validFor;
    /**
     * The condition of the product specification, such as active, inactive,
     * planned.
     */
    private String lifecycleStatus;

    public List<ProductSpecificationCost> getProductSpecificationCost() {
        return this.productSpecificationCost;
    }

    public void setProductSpecificationCost(List<ProductSpecificationCost> productSpecificationCost) {
        this.productSpecificationCost = productSpecificationCost;
    }

    public List<SimpleProductOffering> getProductOffering() {
        return this.productOffering;
    }

    public void setProductOffering(List<SimpleProductOffering> productOffering) {
        this.productOffering = productOffering;
    }

    public List<ProductSpecificationVersion> getProductSpecificationVersion() {
        return this.productSpecificationVersion;
    }

    public void setProductSpecificationVersion(List<ProductSpecificationVersion> productSpecificationVersion) {
        this.productSpecificationVersion = productSpecificationVersion;
    }

    public List<CompositeProductSpecification> getCompositeProdSpec() {
        return this.compositeProdSpec;
    }

    public void setCompositeProdSpec(List<CompositeProductSpecification> compositeProdSpec) {
        this.compositeProdSpec = compositeProdSpec;
    }

    public List<ProductSpecificationRelationship> getProdSpecRelationship() {
        return this.prodSpecRelationship;
    }

    public void setProdSpecRelationship(List<ProductSpecificationRelationship> prodSpecRelationship) {
        this.prodSpecRelationship = prodSpecRelationship;
    }

    public Set<ProductSpecCharUse> getProdSpecCharUse() {
        return prodSpecCharUse;
    }

    public void setProdSpecCharUse(Set<ProductSpecCharUse> prodSpecCharUse) {
        this.prodSpecCharUse = prodSpecCharUse;
    }

    public List<ProductSpecificationType> getProdSpecType() {
        return this.prodSpecType;
    }

    public void setProdSpecType(List<ProductSpecificationType> prodSpecType) {
        this.prodSpecType = prodSpecType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductNumber() {
        return this.productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    public String getLifecycleStatus() {
        return this.lifecycleStatus;
    }

    public void setLifecycleStatus(String lifecycleStatus) {
        this.lifecycleStatus = lifecycleStatus;
    }

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     * @param lifecycleStatus
     */
    public ProductSpecification(String productNumber, String name, String brand, String lifecycleStatus) {
        this.productNumber = productNumber;
        this.name = name;
        this.brand = brand;
        this.lifecycleStatus = lifecycleStatus;
    }

    /**
     * 
     * @param productNumber
     * @param name
     * @param brand
     * @param lifecycleStatus
     * @param description
     * @param validFor
     */
    public ProductSpecification(String productNumber, String name, String brand, String lifecycleStatus,
            String description, TimePeriod validFor) {
        this.productNumber = productNumber;
        this.name = name;
        this.brand = brand;
        this.lifecycleStatus = lifecycleStatus;
        this.description = description;
        this.validFor = validFor;
    }

    /**
     * 
     * @param verType
     * @param version
     * @param description
     * @param revisionDate
     * @param validFor
     */
    private void specifyVersion(String verType, String version, String description, Date revisionDate,
            TimePeriod validFor) {
        ProductSpecificationVersion prodSpecversion = new ProductSpecificationVersion(verType, description, version,
                revisionDate, validFor);
        if (productSpecificationVersion == null) {
            productSpecificationVersion = new ArrayList<ProductSpecificationVersion>();
        }
        this.productSpecificationVersion.add(prodSpecversion);
    }

    /**
     * 
     * @param version
     * @param description
     * @param revisionDate
     * @param validFor
     * @throws Exception
     */
    public void specifyVersion(String version, String description, Date revisionDate, TimePeriod validFor)
            throws Exception {

        String versionNumbers[] = version.split("\\.");
        String versionTypes[] = {};

        if (versionNumbers == null || versionNumbers.length != 3) {
            throw new Exception("Incorrect Version Format! Please check the version type.");
        }
        for (int i = 0; i < versionNumbers.length; i++) {
            this.specifyVersion(versionTypes[i], versionNumbers[i], description, revisionDate, validFor);
        }
    }

    public List<ProductSpecificationVersion> retrieveCurrentVersion() {

        List<ProductSpecificationVersion> currentVersions = new ArrayList<ProductSpecificationVersion>();
        Date now = new Date();
        int len = this.productSpecificationVersion.size();
        for (int i = 0; i < len; i++) {
            ProductSpecificationVersion version = this.productSpecificationVersion.get(i);
            if (version.getValidFor().isInPeriod(now)) {
                currentVersions.add(version);
            }
        }

        return currentVersions;
    }

    public String retrieveCurrentVersionString() {

        String versionString = "";
        List<ProductSpecificationVersion> currentVersions = retrieveCurrentVersion();

        if (currentVersions != null && currentVersions.size() > 0) {
            for (ProductSpecificationVersion currentVersion : currentVersions) {
                versionString = versionString + "." + currentVersion.getProdSpecRevisionNumber();
            }
        }
        if (StringUtils.isNotEmpty(versionString)) {
            versionString = versionString.substring(1, versionString.length());
        }
        return versionString;
    }

    public List<ProductSpecificationVersion> retrieveHistoryVersion() {
        // TODO - implement ProductSpecification.getHistoryVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param version
     * @param description
     * @param revisionDate
     */
    public String upgradeMajorVersion(String majorVersion, String description, Date revisionDate) {
        // TODO - implement ProductSpecification.upgradeMajorVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param version
     * @param description
     * @param revisionDate
     */
    public String upgradeMinorVersion(String minorVersion, String description, Date revisionDate) {
        // TODO - implement ProductSpecification.upgradeMinorVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param version
     * @param description
     * @param revisionDate
     */
    public String upgradePatchVersion(String patchVersion, String description, Date revisionDate) {
        // TODO - implement ProductSpecification.upgradePatchVersion
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param cost
     * @param validFor
     */
    public void addCost(Money cost, TimePeriod validFor) {
        ProductSpecificationCost productSpecCost = new ProductSpecificationCost(cost, validFor);
        if (productSpecificationCost == null)
            productSpecificationCost = new ArrayList<ProductSpecificationCost>();
        productSpecificationCost.add(productSpecCost);
    }

    /**
     * 
     * @param oldCost
     * @param validFor
     */
    public void updateCostPeriod(ProductSpecificationCost oldCost, TimePeriod validFor) {
        oldCost.setValidFor(validFor);
    }

    /**
     * 
     * @param time
     */
    public List<ProductSpecificationCost> queryCost(Date time) {
        List<ProductSpecificationCost> validProdSpecCost = new ArrayList<ProductSpecificationCost>();
        // for (int i = 0; i < productSpecificationCost.size(); i++) {
        // ProductSpecificationCost cost = productSpecificationCost.get(i);
        // if (cost.getValidFor().isInPeriod(time)) {
        // validProdSpecCost.add(productSpecificationCost.get(i));
        // }
        //
        // }
        if (validProdSpecCost != null && validProdSpecCost.size() > 0) {
            return validProdSpecCost;
        } else {
            return null;
        }

    }

    /**
     * 
     * @param prodSpec
     * @param type
     * @param validFor
     * @throws Exception
     */
    public void addRelatedProdSpec(ProductSpecification prodSpec, String type, TimePeriod validFor)
            throws IllegalArgumentException {
        if (null == this.prodSpecRelationship) {
            this.prodSpecRelationship = new ArrayList<ProductSpecificationRelationship>();
        }
        if (null == prodSpec) {
            throw new IllegalArgumentException("Parameter [prodSpec] cannot be null.");
        }
        if (null == type) {
            throw new IllegalArgumentException("Parameter [type] cannot be null. ProductNumber="
                    + prodSpec.getProductNumber() + "type=" + type);
        }
        if (this.equals(prodSpec)) {
            logger.error("Cannot add relationship with it self!");
            throw new IllegalArgumentException("Cannot add relationship with it self!");
        }
        ProductSpecificationRelationship productSpecificationRelationship = new ProductSpecificationRelationship(this,
                prodSpec, type, validFor);
        if (this.prodSpecRelationship.contains(productSpecificationRelationship)) {
            logger.error("the relationship already exist, Cannot repeatedly create relationship by the same type. ProductNumber="
                    + prodSpec.getProductNumber() + "type=" + type);
            throw new IllegalArgumentException(
                    "the relationship already exist, Cannot repeatedly create relationship by the same type.");
        }
        this.prodSpecRelationship.add(productSpecificationRelationship);
    }

    /**
     * 
     * @param prodSpec
     */
    public void removeRelatedProdSpec(ProductSpecification prodSpec) {
        if (this.prodSpecRelationship != null) {
            this.prodSpecRelationship.remove(prodSpec);
        }
    }

    /**
     * 
     * @param type
     */
    public List<ProductSpecification> retrieveRelatedProdSpec(String type) {
        List<ProductSpecification> productSpecifications = new ArrayList<ProductSpecification>();

        if (StringUtils.isEmpty(type)) {
            throw new IllegalArgumentException("Parameter [prodSpec] cannot be null.");
        }
        if (null != this.prodSpecRelationship) {
            Iterator<ProductSpecificationRelationship> iterator = this.prodSpecRelationship.iterator();
            while (iterator.hasNext()) {
                ProductSpecificationRelationship productSpecRelationship = iterator.next();
                if (type.equals(productSpecRelationship.getType())) {
                    productSpecifications.add(productSpecRelationship.getTargetProdSpec());
                }
            }
        }
        return productSpecifications;
    }

    /**
     * 
     * @param characteristic A characteristic quality or distinctive feature of
     *            a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the
     *            CharacteristicSpecValues associated with the
     *            CharacteristicSpec cannot be changed when instantiating a
     *            ServiceCharacteristicValue. For example, a bandwidth of 64 MB
     *            cannot be changed.
     * @param packageFlg An indicator that specifies if the associated
     *            CharacteristicSpecification is a composite. true£ºis a
     *            composite one
     * @param validFor The period of time for which the use of the
     *            CharacteristicSpecification is applicable.
     */
    public void addCharacteristic(ProductSpecCharacteristic characteristic, boolean canBeOveridden, boolean isPackage,
            TimePeriod validFor, String name) {
        this.paramIsEmpty(characteristic);
        ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic, canBeOveridden, isPackage, validFor, name);
        if (null == this.prodSpecCharUse) {
            this.prodSpecCharUse = new HashSet<ProductSpecCharUse>();
        }
        this.prodSpecCharUse.add(charUse);
    }

    /**
     * 
     * @param characteristic A characteristic quality or distinctive feature of
     *            a ProductSpecification. The object must exist in the system
     * @param canBeOveridden An indicator that specifies that the
     *            CharacteristicSpecValues associated with the
     *            CharacteristicSpec cannot be changed when instantiating a
     *            ServiceCharacteristicValue. For example, a bandwidth of 64 MB
     *            cannot be changed.
     * @param packageFlg An indicator that specifies if the associated
     *            CharacteristicSpecification is a composite.
     * @param validFor The period of time for which the use of the
     *            CharacteristicSpecification is applicable.
     * @param name A word, term, or phrase by which the
     *            CharacteristicSpecification is known and distinguished from
     *            other CharacteristicSpecifications.
     * @param unique An indicator that specifies if a value is unique for the
     *            specification. Possible values are:
     *            "unique while value is in effect" and
     *            "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a
     *            CharacteristicValue can take on. For example, zero to five
     *            phone numbers in a group calling plan, where zero is the value
     *            for the minCardinality.
     * @param maxCardinality The maximum number of instances a
     *            CharacteristicValue can take on. For example, zero to five
     *            phone numbers in a group calling plan, where five is the value
     *            for the maxCardinality.
     * @param extensible An indicator that specifies that the values for the
     *            characteristic can be extended by adding new values when
     *            instantiating a characteristic for a Service.
     * @param description A narrative that explains the
     *            CharacteristicSpecification.
     */
    public void addCharacteristic(ProductSpecCharacteristic characteristic, boolean canBeOveridden, boolean isPackage,
            TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality,
            boolean extensible, String description) {
        this.paramIsEmpty(characteristic);
        ProductSpecCharUse charUse = new ProductSpecCharUse(characteristic, canBeOveridden, isPackage, validFor, name,
                unique, minCardinality, maxCardinality, extensible, description);
        if (null == this.prodSpecCharUse) {
            this.prodSpecCharUse = new HashSet<ProductSpecCharUse>();
        }
        this.prodSpecCharUse.add(charUse);
    }

    /**
     * 
     * @param characteristic A characteristic quality or distinctive feature of
     *            a ProductSpecification. The {@code ProductSpecification} must
     *            have the Characteristic before.
     */
    public void removeCharacteristic(ProductSpecCharacteristic characteristic) {
        // TODO - implement ProductSpecification.removeCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic A characteristic quality or distinctive feature of
     *            a ProductSpecification. The {@code ProductSpecification} must
     *            have the Characteristic.
     * @param canBeOveridden An indicator that specifies that the
     *            CharacteristicSpecValues associated with the
     *            CharacteristicSpec cannot be changed when instantiating a
     *            ServiceCharacteristicValue. For example, a bandwidth of 64 MB
     *            cannot be changed.
     * @param packageFlg An indicator that specifies if the associated
     *            CharacteristicSpecification is a composite.
     * @param validFor The period of time for which the use of the
     *            CharacteristicSpecification is applicable.
     * @param name A word, term, or phrase by which the
     *            CharacteristicSpecification is known and distinguished from
     *            other CharacteristicSpecifications.
     * @param unique An indicator that specifies if a value is unique for the
     *            specification. Possible values are:
     *            "unique while value is in effect" and
     *            "unique whether value is in effect or not"
     * @param minCardinality The minimum number of instances a
     *            CharacteristicValue can take on. For example, zero to five
     *            phone numbers in a group calling plan, where zero is the value
     *            for the minCardinality.
     * @param maxCardinality The maximum number of instances a
     *            CharacteristicValue can take on. For example, zero to five
     *            phone numbers in a group calling plan, where five is the value
     *            for the maxCardinality.
     * @param extensible An indicator that specifies that the values for the
     *            characteristic can be extended by adding new values when
     *            instantiating a characteristic for a Service.
     * @param description A narrative that explains the
     *            CharacteristicSpecification.
     */
    public void modifyCharacteristicInfo(ProductSpecCharacteristic characteristic, boolean canBeOveridden,
            boolean isPackage, TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality,
            boolean extensible, String description) {
        // TODO - implement ProductSpecification.modifyCharacteristicInfo
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic A characteristic quality or distinctive feature of
     *            a ProductSpecification. The object must exist in the system
     * @param charValue A number or text that be assigned to a
     *            ProductSpecCharacteristic. The value must be in the
     *            characterisc's values.
     * @param isDefault Indicates if the value is the default value for a
     *            characteristic. true£ºis default value
     * @param validFor The period of time for which the use of the
     *            CharacteristicValue is applicable.
     */
    public boolean attachCharacteristicValue(ProductSpecCharacteristic characteristic,
            ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
    	this.paramIsEmpty(characteristic);
        this.paramIsEmpty(charValue);
        boolean flag = false;
        if (this.prodSpecCharUse != null) {
            ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(characteristic);
            this.charIsUsed(charUse);
            if(characteristic.getProductSpecCharacteristicValue().contains(charValue)){
            	flag = charUse.addValue(charValue, isDefault, validFor);
            }else{
            	logger.warn("Parameter characteristicValue is not belong to this characteristic ");
            }
        } 
        return flag;
    }

    /**
     * 
     * @param characteristic
     * @param charValue
     */
    public void detachCharacteristicValue(ProductSpecCharacteristic characteristic,
            ProductSpecCharacteristicValue charValue) {
        // TODO - implement ProductSpecification.detachCharacteristicValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic
     * @param defaultValue
     */
    public boolean specifyDefaultCharacteristicValue(ProductSpecCharacteristic characteristic,
            ProductSpecCharacteristicValue defaultValue) {
    	this.paramIsEmpty(characteristic);
        this.paramIsEmpty(defaultValue);
        boolean flag = false;
        if (this.prodSpecCharUse != null) {
            ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(characteristic);
            this.charIsUsed(charUse);
            if(characteristic.getProductSpecCharacteristicValue().contains(defaultValue)){
            	flag = charUse.specifyDefaultCharacteristicValueUse(defaultValue);
            }else{
            	logger.warn("Parameter characteristicValue is not belong to this characteristic ");
            }
        } 
        return flag;
    }

    public boolean clearDefaultCharacteristicValue(ProductSpecCharacteristic characteristic,
            ProductSpecCharacteristicValue defaultValue) {
       this.paramIsEmpty(characteristic);
       this.paramIsEmpty(defaultValue);
       boolean flag = false;
        if (this.prodSpecCharUse != null) {
            ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(characteristic);
            this.charIsUsed(charUse);
            flag = charUse.clearDefaultValueUse(defaultValue);
        }
        return flag;
    }

    public List<ProdSpecCharValueUse> retrieveDefaultCharacteristicValue(ProductSpecCharacteristic characteristic) {
        this.paramIsEmpty(characteristic);
        List<ProdSpecCharValueUse> defaultValues = new ArrayList<ProdSpecCharValueUse>();
        if (null != this.prodSpecCharUse) {
            ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(characteristic);
            this.charIsUsed(charUse);
            defaultValues = charUse.retrieveDefaultCharacteristicValueUse();
        }
        return defaultValues;
    }

    /**
     * 
     * @param time
     */
    public List<ProductSpecCharUse> retrieveCharacteristic(Date time) {
        List<ProductSpecCharUse> charUseList = new ArrayList<ProductSpecCharUse>();
        if (null != this.prodSpecCharUse) {
            for (ProductSpecCharUse charUse : this.prodSpecCharUse) {
                if (charUse.getValidFor().isInPeriod(time))
                    charUseList.add(charUse);
            }
        }
        return charUseList;
    }

    /**
     * 
     * @param characteristic
     * @param time
     */
    public List<ProdSpecCharValueUse> retrieveCharacteristicValue(ProductSpecCharacteristic characteristic, Date time) {
        List<ProdSpecCharValueUse> charValueUseList = new ArrayList<ProdSpecCharValueUse>();
        this.paramIsEmpty(characteristic);
        ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(characteristic);
        this.charIsUsed(charUse);
        Set<ProdSpecCharValueUse> valueUseAllList = new HashSet<ProdSpecCharValueUse>();
        valueUseAllList = charUse.getProdSpecCharValueUse();
        if (null != valueUseAllList) {
            for (ProdSpecCharValueUse valueUse : valueUseAllList) {
                if (valueUse.getValidFor().isInPeriod(time))
                    charValueUseList.add(valueUse);
            }
        }
        return charValueUseList;
    }

    private ProductSpecCharUse retrieveProdSpecCharUse(ProductSpecCharacteristic characteristic) {
        if (null != this.prodSpecCharUse) {
            for (ProductSpecCharUse charUse : this.prodSpecCharUse) {
                if (characteristic.equals(charUse.getProdSpecChar()))
                    return charUse;
            }
        }
        return null;
    }

    public List<ProductSpecCharUse> retrieveRootCharacteristic() {
        List<ProductSpecCharUse> charUseList = new ArrayList<ProductSpecCharUse>();
        if (null != this.prodSpecCharUse) {
            charUseList.addAll(this.prodSpecCharUse);
            for (ProductSpecCharUse charUse : this.prodSpecCharUse) {
                List<ProductSpecCharacteristic> prodSpecChar = charUse.getProdSpecChar().retrieveRelatedCharacteristic(
                        RelationshipType.AGGREGATION.getValue());
                if (null != prodSpecChar) {
                    for (ProductSpecCharacteristic specChar : prodSpecChar) {
                        ProductSpecCharUse subCharUse = this.retrieveProdSpecCharUse(specChar);
                        if (null != subCharUse)
                            charUseList.remove(subCharUse);
                    }
                }
            }
        }
        return charUseList;
    }

    /**
     * 
     * @param characteristic
     * @param time
     */

    public List<ProductSpecCharUse> retrieveLeafCharacteristic(ProductSpecCharacteristic characteristic, Date time) {
        List<ProductSpecCharUse> charUses = new ArrayList<ProductSpecCharUse>();
        this.paramIsEmpty(characteristic);
        List<ProductSpecCharacteristic> prodSpecChar = characteristic.retrieveRelatedCharacteristic(
                RelationshipType.AGGREGATION.getValue(), time);
        if (null != prodSpecChar) {
            for (ProductSpecCharacteristic specChar : prodSpecChar) {
                ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(specChar);
                if (null != charUse)
                    charUses.add(charUse);
            }
        }
        return charUses;
    }

    /**
     * 
     * @param characteristic
     * @param minCardinality
     * @param maxCardinality
     */
    public boolean specifyCardinality(ProductSpecCharacteristic characteristic, int minCardinality, int maxCardinality) {
        this.paramIsEmpty(characteristic);
        ProductSpecCharUse charUse = this.retrieveProdSpecCharUse(characteristic);
        if(null != charUse){
        	charUse.setCardinality(minCardinality, maxCardinality);
        	return true;
        }else{
        	logger.warn("Parameter characteristic is not used");
        	return false;
        }
    }

    private void paramIsEmpty(Object obj){
    	if(null == obj ){
    		logger.error("The parameter is null");
    		throw new IllegalArgumentException();
    	}
    }
    
    private void charIsUsed(ProductSpecCharUse charUse){
    	if(null == charUse){
        	logger.error("Parameter characteristic is not used ");
        	throw new IllegalArgumentException();
        }
    }
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return CommonUtils.getPropertyToJson(null, null, this);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((productNumber == null) ? 0 : productNumber.hashCode());
        result = prime * result + ((productSpecificationVersion == null) ? 0 : productSpecificationVersion.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductSpecification other = (ProductSpecification) obj;
        if (productNumber == null) {
            if (other.productNumber != null)
                return false;
        } else if (!productNumber.equals(other.productNumber))
            return false;
        if (productSpecificationVersion == null) {
            if (other.productSpecificationVersion != null)
                return false;
        } else if (!productSpecificationVersion.equals(other.productSpecificationVersion))
            return false;
        return true;
    }

}
