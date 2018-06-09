package com.liangfeng.study.core.framework.base;


import com.liangfeng.study.core.helper.DateHelper;
import com.liangfeng.study.core.helper.ObjectHelper;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: BaseTest
 * @Description:
 * @date 2017/5/27 0027 上午 10:50
 */
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    // 不需要校验的字段名称数组
    private static final String[] noneValidFileds = {"serialVersionUID"};

    private static final String VALID_FILED_DESC = "验证字段名称:{}";

    private static final String VALID_FILED_VAL_DESC = "验证字段名称:{},val1={},val2={}";

    /**
     * 验证对象属性值
     *
     * @param errMsg   显示的错误消息
     * @param validObj 验证的对象
     */
    public void validObjProps(String errMsg, Object validObj) {
        // 过滤一些无需校验的字段
        validObjProps(errMsg, validObj, null, noneValidFileds, true);
    }

    /**
     * 验证对象属性值，includes存在，则excludes不生效，如果两者都为空，则校验所有属性。
     *
     * @param errMsg   显示的错误消息
     * @param validObj 验证的对象
     * @param includes 需要验证的属性
     * @param excludes 不需要验证的属性，
     */
    public void validObjProps(String errMsg, Object validObj, String[] includes, String[] excludes, boolean isContainParent) {
        Assert.assertNotNull(errMsg, validObj);
        if (includes != null) {
            for (String filedName : includes) {
                logger.debug(VALID_FILED_DESC, filedName);
                Assert.assertNotNull(errMsg, ObjectHelper.getFieldValueByName(filedName, validObj));
            }
            return;
        }
        if (excludes != null) {
            String[] fileNames = ObjectHelper.getFiledName(validObj, isContainParent);
            Set<String> excludeSet = new HashSet<>();
            // 加上默认不需要校验的字段
            CollectionUtils.addAll(excludeSet, noneValidFileds);
            CollectionUtils.addAll(excludeSet, excludes);
            for (String filedName : fileNames) {
                if (excludeSet.contains(filedName)) {
                    continue;
                }
                logger.debug(VALID_FILED_DESC, filedName);
                Assert.assertNotNull(errMsg, ObjectHelper.getFieldValueByName(filedName, validObj));
            }
            return;
        }
      /*  // 验证所有字段值
        Object[] objs = ObjectHelper.getFiledValues(validObj);
        for (Object obj : objs) {
            Assert.assertNotNull(errMsg, obj);
        }*/
    }

    /**
     * 比较两个对象的属性值是否相等
     * @param errMsg
     * @param obj1
     * @param obj2
     */
    public void compareObjProps(String errMsg, Object obj1, Object obj2,String[] noneValidFileds) {
        // 过滤一些无需比较的字段
        compareObjProps(errMsg, obj1, obj2, null, noneValidFileds, true);
    }

    /**
     * 比较两个对象的属性值是否相等
     *
     * @param errMsg
     * @param obj1
     * @param obj2
     */
    public void compareObjProps(String errMsg, Object obj1, Object obj2) {
        // 过滤一些无需比较的字段
        compareObjProps(errMsg, obj1, obj2, null, noneValidFileds, true);
    }

    /**
     * 比较两个对象的属性值是否相等
     *
     * @param errMsg   错误消息
     * @param obj1     对象1
     * @param obj2     对象2
     * @param includes 需要比较的属性
     * @param excludes 不需要比较的属性
     */
    public void compareObjProps(String errMsg, Object obj1, Object obj2, String[] includes, String[] excludes, boolean isContainParent) {
        Assert.assertNotNull(errMsg, obj1);
        Assert.assertNotNull(errMsg, obj2);
        if (includes != null) {
            for (String filedName : includes) {
                compareObjPropsVal(obj1, obj2,filedName, errMsg);
            }
            return;
        }
        if (excludes != null) {
            String[] fileNames = ObjectHelper.getFiledName(obj1, isContainParent);
            Set<String> excludeSet = new HashSet<>();
            // 加上默认不需要校验的字段
            CollectionUtils.addAll(excludeSet, noneValidFileds);
            CollectionUtils.addAll(excludeSet, excludes);
            for (String filedName : fileNames) {
                if (excludeSet.contains(filedName)) {
                    continue;
                }
                compareObjPropsVal(obj1, obj2,filedName, errMsg);
            }
            return;
        }
       /* // 验证所有字段值
        Object[] objs1 = ObjectHelper.getFiledValues(obj1);
        Object[] objs2 = ObjectHelper.getFiledValues(obj2);
        for (int i = 0; i < objs1.length; i++) {
            if(objs1[i] instanceof Date){
                Assert.assertEquals(errMsg, DateHelper.formatDateTime((Date) objs1[i]),DateHelper.formatDateTime((Date) objs2[i]));
            }else{
                Assert.assertEquals(errMsg, objs1[i], objs2[i]);
            }
        }*/
    }

    /**
     * 比较两个对象的字段值
     * @param obj1
     * @param obj2
     * @param filedName
     * @param errMsg
     */
    private void compareObjPropsVal(Object obj1, Object obj2, String filedName, String errMsg) {
        Object val1 = ObjectHelper.getFieldValueByName(filedName, obj1);
        Object val2 = ObjectHelper.getFieldValueByName(filedName, obj2);
        if (val1 instanceof Date) {
            val1 = DateHelper.formatDateTime((Date) val1);
            val2 = DateHelper.formatDateTime((Date) val2);
        }
        logger.debug(VALID_FILED_VAL_DESC, filedName,val1,val2);
        Assert.assertEquals(errMsg, val1, val2);
    }


}
