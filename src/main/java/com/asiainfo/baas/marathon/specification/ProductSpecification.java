package com.asiainfo.baas.marathon.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asiainfo.baas.common.DateUtils;
import com.asiainfo.baas.marathon.baseType.Money;
import com.asiainfo.baas.marathon.baseType.TimePeriod;
import com.asiainfo.baas.marathon.offering.SimpleProductOffering;

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

    private List<ProductSpecificationCost> productSpecificationCost;
    private List<SimpleProductOffering> productOffering;
    private List<ProductSpecificationVersion> productSpecificationVersion;
    private List<CompositeProductSpecification> compositeProdSpec;
    private List<ProductSpecificationRelationship> prodSpecRelationship;
    private List<ProductSpecCharUse> prodSpecCharUse;
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

    public List<ProductSpecCharUse> getProdSpecCharUse() {
        return this.prodSpecCharUse;
    }

    public void setProdSpecCharUse(List<ProductSpecCharUse> prodSpecCharUse) {
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
    private void setVersion(String verType, String version, String description, Date revisionDate, TimePeriod validFor) {
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
    public void setVersion(String version, String description, Date revisionDate, TimePeriod validFor) throws Exception {

        String versionNumbers[] = version.split("\\.");
        String versionTypes[] = { "1", "2", "3" };

        if (versionNumbers == null || versionNumbers.length != 3) {
            throw new Exception("Incorrect Version Format! Please check the version type.");
        }
        for (int i = 0; i < versionNumbers.length; i++) {
            this.setVersion(versionTypes[i], versionNumbers[i], description, revisionDate, validFor);
        }
    }

    public ProductSpecificationVersion[] getCurrentVersion() {

        List<ProductSpecificationVersion> currentVersions = new ArrayList<ProductSpecificationVersion>();
        Date now = new Date();
        int len = this.productSpecificationVersion.size();
        for (int i = 0; i < len; i++) {
            ProductSpecificationVersion version = this.productSpecificationVersion.get(i);
            if (DateUtils.isInPeriod(now, version.getValidFor())) {
                currentVersions.add(version);
            }
        }

        return currentVersions.toArray(new ProductSpecificationVersion[0]);
    }

    public String getCurrentVersionString() {

        String versionString = "";
        ProductSpecificationVersion[] currentVersions = getCurrentVersion();

        if (currentVersions != null && currentVersions.length > 0) {
            for (ProductSpecificationVersion currentVersion : currentVersions) {
                versionString = versionString + "." + currentVersion.getProdSpecRevisionNumber();
            }
        }
        if (StringUtils.isNotEmpty(versionString)) {
            versionString = versionString.substring(1, versionString.length());
        }
        return versionString;
    }

    public ProductSpecificationVersion[] getHistoryVersion() {
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
    public ProductSpecificationCost[] queryCost(Date time) {
        List<ProductSpecificationCost> validProdSpecCost = new ArrayList<ProductSpecificationCost>();
        for (int i = 0; i < productSpecificationCost.size(); i++) {
            ProductSpecificationCost cost = productSpecificationCost.get(i);
            if (DateUtils.isInPeriod(time, cost.getValidFor())) {
                validProdSpecCost.add(productSpecificationCost.get(i));
            }

        }
        if (validProdSpecCost != null && validProdSpecCost.size() > 0) {
            return (ProductSpecificationCost[]) validProdSpecCost
                    .toArray(new ProductSpecificationCost[validProdSpecCost.size()]);
        } else {
            return null;
        }

    }

    /**
     * 
     * @param prodSpec
     * @param type
     * @param validFor
     */
    public void addRelatedProdSpec(ProductSpecification prodSpec, String type, TimePeriod validFor) {
        if (this.prodSpecRelationship == null) {
            this.prodSpecRelationship = new ArrayList<ProductSpecificationRelationship>();
        }
        ProductSpecificationRelationship productSpecificationRelationship = new ProductSpecificationRelationship(this,
                prodSpec, type, validFor);
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
    public ProductSpecification[] queryRelatedProdSpec(String type) {
        List<ProductSpecification> productSpecifications = new ArrayList<ProductSpecification>();
        int len = this.prodSpecRelationship.size();
        for (int i = 0; i < len; i++) {
            if (type.equals(this.prodSpecRelationship.get(i).getType())) {
                productSpecifications.add(this.prodSpecRelationship.get(i).getTargetProdSpec());
            }
        }
        return productSpecifications.toArray(new ProductSpecification[0]);
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
    public void addCharacteristic(ProductSpecCharacteristic characteristic, boolean canBeOveridden, boolean packageFlg,
            TimePeriod validFor) {
        // TODO - implement ProductSpecification.addCharacteristic
        throw new UnsupportedOperationException();
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
    public void addCharacteristic(ProductSpecCharacteristic characteristic, boolean canBeOveridden, boolean packageFlg,
            TimePeriod validFor, String name, String unique, int minCardinality, int maxCardinality,
            boolean extensible, String description) {
        // TODO - implement ProductSpecification.addCharacteristic
        throw new UnsupportedOperationException();
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
            boolean packageFlg, TimePeriod validFor, String name, String unique, int minCardinality,
            int maxCardinality, boolean extensible, String description) {
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
    public void attachCharacteristicValue(ProductSpecCharacteristic characteristic,
            ProductSpecCharacteristicValue charValue, boolean isDefault, TimePeriod validFor) {
        // TODO - implement ProductSpecification.attachCharacteristicValue
        throw new UnsupportedOperationException();
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
    public void specifyDefaultCharacteristicValue(ProductSpecCharacteristic characteristic,
            ProductSpecCharacteristicValue defaultValue) {
        // TODO - implement
        // ProductSpecification.specifyDefaultCharacteristicValue
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param time
     */
    public ProductSpecCharUse[] retrieveCharacteristic(Date time) {
        // TODO - implement ProductSpecification.retrieveCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic
     * @param time
     */
    public ProdSpecCharValueUse[] retrieveCharacteristicValue(ProductSpecCharacteristic characteristic, Date time) {
        // TODO - implement ProductSpecification.retrieveCharacteristicValue
        throw new UnsupportedOperationException();
    }

    public ProductSpecCharUse[] getRootCharacteristic() {
        // TODO - implement ProductSpecification.getRootCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic
     * @param time
     */
    public ProductSpecCharUse[] getLeafCharacteristic(ProductSpecCharacteristic characteristic, Date time) {
        // TODO - implement ProductSpecification.getLeafCharacteristic
        throw new UnsupportedOperationException();
    }

    /**
     * 
     * @param characteristic
     * @param minCardinality
     * @param maxCardinality
     */
    public void setCardinality(ProductSpecCharacteristic characteristic, int minCardinality, int maxCardinality) {
        // TODO - implement ProductSpecification.setCardinality
        throw new UnsupportedOperationException();
    }

}