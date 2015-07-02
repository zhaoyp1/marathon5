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
                        outText.append("：");
                        outText.append(BeanUtils.getProperty(bean, fieldArray[i].getName()));
                        outText.append("    ");
                    }

                    for (int i = 0; i < superFieldArray.length; i++) {
                        outText.append(superFieldArray[i].getName());
                        outText.append("：");
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

        // 初始化config
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

    public static String getPropertyToJson(Object[] beanArray, List printBeanList, Object beanObject) {

        // 初始化config
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor("yyyy-MM-dd hh:mm:ss"));
        String[] excudes = { "price", "historyVersion", "currentVersion", "currentVersionString", "leafCharacteristic",
                "defaultCharacteristicValueUse", "rootCharacteristic", "subOffering", "cardinality" };
        config.setExcludes(excudes);
        config.setIgnoreDefaultExcludes(false);
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);

        List<Object> beanList = printBeanList;
        String outText = "";
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
                    outText = json.toString();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return format(outText);
    }

    /**
     * 得到格式化json数据 退格用\t 换行用\r
     */
    public static String format(String jsonStr) {
        int level = 0;
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int i = 0; i < jsonStr.length(); i++) {
            char c = jsonStr.charAt(i);
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
            }
            switch (c) {
            case '{':
            case '[':
                jsonForMatStr.append(c + "\n");
                level++;
                break;
            case ',':
                jsonForMatStr.append(c + "\n");
                break;
            case '}':
            case ']':
                jsonForMatStr.append("\n");
                level--;
                jsonForMatStr.append(getLevelStr(level));
                jsonForMatStr.append(c);
                break;
            default:
                jsonForMatStr.append(c);
                break;
            }
        }

        return jsonForMatStr.toString();

    }

    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
}
