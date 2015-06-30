package com.asiainfo.baas.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ReflectionToStringBuilderBaas extends ReflectionToStringBuilder {

    public ReflectionToStringBuilderBaas(Object object, ToStringStyle style) {
        super(object, style);
    }

    @Override
    protected boolean accept(Field field) {
        List<String> fillterList = new ArrayList<String>();
        fillterList.add("productSpecificationCost");
        fillterList.add("productOffering");
        fillterList.add("productSpecificationVersion");
        fillterList.add("prodSpecType");
        return super.accept(field) && !fillterList.contains(field.getName());
    }

}
