package com.asiainfo.baas.marathon.specification;

import java.util.Date;

import com.asiainfo.baas.marathon.baseType.TimePeriod;

/**
 * A particular form or variety of a ProductSpecification that is different from
 * others or from the original. The form represents differences in properties
 * that characterize a ProductSpecification, that are not enough to warrant
 * creating a new ProductSpecification.
 */
public class ProductSpecificationVersion {

    private ProductSpecification productSpecification;
    /**
     * The significance of the revision.
     */
    private String prodSpecRevisionType;
    /**
     * A narrative that explains the reason for the version's creation.
     */
    private String description;
    /**
     * A number that represents the occurrence of the version in the sequence of
     * versions.
     */
    private String prodSpecRevisionNumber;
    /**
     * The date the version was created.
     */
    private Date prodSpecRevisionDate;
    /**
     * The period during which the version is applicable.
     */
    private TimePeriod validFor;

    public ProductSpecification getProductSpecification() {
        return this.productSpecification;
    }

    public void setProductSpecification(ProductSpecification productSpecification) {
        this.productSpecification = productSpecification;
    }

    public String getProdSpecRevisionType() {
        return this.prodSpecRevisionType;
    }

    public void setProdSpecRevisionType(String prodSpecRevisionType) {
        this.prodSpecRevisionType = prodSpecRevisionType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProdSpecRevisionNumber() {
        return this.prodSpecRevisionNumber;
    }

    public void setProdSpecRevisionNumber(String prodSpecRevisionNumber) {
        this.prodSpecRevisionNumber = prodSpecRevisionNumber;
    }

    public Date getProdSpecRevisionDate() {
        return this.prodSpecRevisionDate;
    }

    public void setProdSpecRevisionDate(Date prodSpecRevisionDate) {
        this.prodSpecRevisionDate = prodSpecRevisionDate;
    }

    public TimePeriod getValidFor() {
        return this.validFor;
    }

    public void setValidFor(TimePeriod validFor) {
        this.validFor = validFor;
    }

    /**
     * 
     * @param revisionType
     * @param description
     * @param revisionNumber
     * @param revisionDate
     * @param validFor
     */
    public ProductSpecificationVersion(String revisionType, String description, String revisionNumber,
            Date revisionDate, TimePeriod validFor) {
        this.prodSpecRevisionType = revisionType;
        this.description = description;
        this.prodSpecRevisionNumber = revisionNumber;
        this.prodSpecRevisionDate = revisionDate;
        this.validFor = validFor;
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
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((prodSpecRevisionDate == null) ? 0 : prodSpecRevisionDate.hashCode());
        result = prime * result + ((prodSpecRevisionNumber == null) ? 0 : prodSpecRevisionNumber.hashCode());
        result = prime * result + ((prodSpecRevisionType == null) ? 0 : prodSpecRevisionType.hashCode());
        result = prime * result + ((productSpecification == null) ? 0 : productSpecification.hashCode());
        result = prime * result + ((validFor == null) ? 0 : validFor.hashCode());
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
        ProductSpecificationVersion other = (ProductSpecificationVersion) obj;
        if (prodSpecRevisionDate == null) {
            if (other.prodSpecRevisionDate != null)
                return false;
        } else if (!prodSpecRevisionDate.equals(other.prodSpecRevisionDate))
            return false;
        if (prodSpecRevisionNumber == null) {
            if (other.prodSpecRevisionNumber != null)
                return false;
        } else if (!prodSpecRevisionNumber.equals(other.prodSpecRevisionNumber))
            return false;
        if (prodSpecRevisionType == null) {
            if (other.prodSpecRevisionType != null)
                return false;
        } else if (!prodSpecRevisionType.equals(other.prodSpecRevisionType))
            return false;
        return true;
    }

}