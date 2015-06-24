package com.asiainfo.baas.marathon.specification;

import com.asiainfo.baas.marathon.baseType.*;
import com.asiainfo.baas.marathon.dateType.*;

/**
 * A particular form or variety of a ProductSpecification that is different from others or from the original. The form represents differences in properties that characterize a ProductSpecification, that are not enough to warrant creating a new ProductSpecification.
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
     * A number that represents the occurrence of the version in the sequence of versions.
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

    /**
     * 
     * @param revisionType
     * @param description
     * @param revisionNumber
     * @param revisionDate
     * @param validFor
     */
    public ProductSpecificationVersion(String revisionType, String description, String revisionNumber, Date revisionDate, TimePeriod validFor) {
        // TODO - implement ProductSpecificationVersion.ProductSpecificationVersion
        throw new UnsupportedOperationException();
    }

}