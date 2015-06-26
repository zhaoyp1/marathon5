package com.asiainfo.baas.marathon5.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.commons.beanutils.BeanUtils;

public class CommonUtils {

    public static void printProperty(Object[] beanArray, List printBeanList, Object beanObject) {

        List<Object> beanList = printBeanList;
        StringBuilder outText = new StringBuilder();
        if (beanArray != null) {
            beanList = Arrays.asList(beanArray);
        }
        if (beanObject != null) {
            if (beanList == null)
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

    public static void printPropertyToJson(Object[] beanArray, List printBeanList, Object beanObject) {

        // ³õÊ¼»¯config
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd hh:mm:ss"));
        String[] excudes = { "price", "historyVersion", "currentVersion", "currentVersionString", "leafCharacteristic",
                "defaultCharacteristicValueUse", "rootCharacteristic", "subOffering" };
        config.setExcludes(excudes);
        config.setIgnoreDefaultExcludes(false);
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

        List<Object> beanList = printBeanList;
        StringBuilder outText = new StringBuilder();
        if (beanArray != null) {
            beanList = Arrays.asList(beanArray);
        }
        if (beanObject != null) {
            if (beanList == null)
                beanList = new ArrayList<Object>();
            beanList.add(beanObject);
        }
        try {
            if (beanList != null && beanList.size() > 0) {
                for (Object bean : beanList) {
                    JSONObject json = JSONObject.fromObject(bean, config);
                    System.out.println(json.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(outText.toString());
    }
}
