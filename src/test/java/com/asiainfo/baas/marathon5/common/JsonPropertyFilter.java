package com.asiainfo.baas.marathon5.common;

import net.sf.json.util.PropertyFilter;

import com.asiainfo.baas.marathon.offering.SimpleProductOffering;

public class JsonPropertyFilter implements PropertyFilter {

    @Override
    public boolean apply(Object o, String n, Object v) {
        if (o instanceof SimpleProductOffering && n.equals("price")) {
            return false;
        }
        return true;
    }

}
