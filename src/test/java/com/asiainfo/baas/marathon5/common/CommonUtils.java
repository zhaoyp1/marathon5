package com.asiainfo.baas.marathon5.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

public class CommonUtils {

    public static void printProperty(Object[] beanArray, List<Object> printBeanList, Object beanObject) {

        List<Object> beanList = printBeanList;
        StringBuilder outText = new StringBuilder();
        if (beanArray != null) {
            beanList = Arrays.asList(beanArray);
        }
        if (beanObject != null) {
        	if(beanList == null)
        		beanList = new ArrayList<Object>();
            beanList.add(beanObject);
        }
        try {
            if (beanList != null && beanList.size() > 0) {
                for (Object bean : beanList) {
                    Class sc = bean.getClass();
                    Class superSc = sc.getSuperclass();
                    Field[] fieldArray = sc.getDeclaredFields();
                    Field[] superFieldArray = superSc.getDeclaredFields();
                    for (int i = 0; i < fieldArray.length; i++) {
                        outText.append(fieldArray[i].getName());
                        outText.append("£º");
                        outText.append(BeanUtils.getProperty(bean, fieldArray[i].getName()));
                        outText.append("    ");
                    }

                    for (int i = 0; i < superFieldArray.length; i++) {
                        outText.append(superFieldArray[i].getName());
                        outText.append("£º");
                        outText.append(BeanUtils.getProperty(bean, superFieldArray[i].getName()));
                        outText.append("    ");
                    }
                    outText.append("\n");
                }
            }
        } catch (Exception e) {
        }
        System.out.println(outText.toString());
    }
}
