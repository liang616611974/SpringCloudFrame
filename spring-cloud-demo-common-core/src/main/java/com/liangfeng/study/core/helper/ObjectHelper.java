package com.liangfeng.study.core.helper;


import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: ObjectHelper 对象帮助类
 * @Description:
 * @date 2017/5/19 0019 上午 11:07
 */
public class ObjectHelper {

    /**
     * 根据属性名获取属性值
     * @param fieldName
     * @param obj
     * @return
     */
    public static Object getFieldValueByName(String fieldName, Object obj) {
        try {
            String getter = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method method = obj.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(obj, new Object[]{});
            return value;
        } catch (NoSuchMethodException e1) {
            return null;
        }catch (Exception e2){
            throw new RuntimeException("根据属性名获取属性值发生异常",e2);
        }
    }

    /**
     * 获取属性名数组
     * @param obj 目标对象
     * @param isContainParent 是否包括父对象的字段
     * @return
     */
    public static String[] getFiledName(Object obj,boolean isContainParent) {
        List<Field> fields = Arrays.asList(getFields(obj, isContainParent));
        List<String> fieldNames = new ArrayList<>();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        return fieldNames.toArray(new String[]{});
    }

    /**
     *  获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * @param obj 目标对象
     * @param isContainParent 是否包括父对象的字段
     * @return
     */
    public static List<Map<String, Object>> getFiledsInfo(Object obj,boolean isContainParent) {
        List<Map<String, Object>> list = new ArrayList<>();
        List<Field> fields = Arrays.asList(getFields(obj, isContainParent));
        Map<String, Object> infoMap = null;
        for (Field field : fields) {
            infoMap = new HashMap<>();
            infoMap.put("type", field.getType().toString());
            infoMap.put("name", field.getName());
            infoMap.put("value", getFieldValueByName(field.getName(), obj));
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     * @param obj 目标对象
     * @param isContainParent 是否包括父对象的字段
     * @return
     */
    public static Object[] getFiledValues(Object obj,boolean isContainParent) {
        String[] fieldNames = getFiledName(obj,isContainParent);
        Object[] value = new Object[fieldNames.length];
        for (int i = 0; i < fieldNames.length; i++) {
            value[i] = getFieldValueByName(fieldNames[i], obj);
        }
        return value;
    }

    /**
     * 获取对象所有字段
     * @param obj 目标对象
     * @param isContainParent 是否包括父对象的字段
     * @return
     */
    public static Field[] getFields(Object obj,boolean isContainParent){
        List<Field> fields = new ArrayList<>();
        Class tempClass = obj.getClass();
        if(isContainParent){
            while (tempClass != null){
                fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
                tempClass = tempClass.getSuperclass();
            }
        }else {
            fields.addAll(Arrays.asList(tempClass.getDeclaredFields()));
        }
        return fields.toArray(new Field[]{});
    }


    /**
     * 初始化对象的属性值
     * @param obj
     * @param numFieldVal
     */
    public static void initFieldsVal( Object obj,int numFieldVal ) {
        initFieldsVal(obj,true,"",String.valueOf(numFieldVal),numFieldVal);
    }

    /**
     * 初始化对象的属性值
     * @param obj 目标对象
     * @param isContainParent
     * @param strFieldValPrefix
     * @param strFieldValSuffix
     * @param numFieldVal
     */
    public static void initFieldsVal( Object obj,boolean isContainParent,String strFieldValPrefix,String strFieldValSuffix,int numFieldVal ) {
        Field[] fields = getFields(obj,isContainParent);
        for(Field field: fields){
            String name = field.getName();
            String setter = "set"+name.toUpperCase().substring(0, 1)+name.substring(1);
            Method method;
            try{
                method = obj.getClass().getMethod(setter,new Class[]{field.getType()});
                if(field.getType().getSimpleName().equals("String")){
                    method.invoke(obj, strFieldValPrefix + name + strFieldValSuffix);
                }else if(field.getType().getSimpleName().equals("Integer")){
                    method.invoke(obj, numFieldVal);
                }else if(field.getType().getSimpleName().equals("Long")){
                    method.invoke(obj, new Long(numFieldVal));
                }else if(field.getType().getSimpleName().equals("BigDecimal")){
                    method.invoke(obj, new BigDecimal(numFieldVal + ".00"));
                }else if(field.getType().getSimpleName().equals("Date")){
                    method.invoke(obj, DateHelper.getDate(DateHelper.getAppendDate(new Date(),0,0,numFieldVal - 1)));
                }else {
                    // 不处理
                }
            } catch (NoSuchMethodException e1) {
                // 如果不存在该字段，则跳过
                continue;
            }catch (Exception e2){
                throw new RuntimeException("设置对象" + name + "属性值发生异常",e2);
            }

        }
    }
}
