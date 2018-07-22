package com.liangfeng.study.dict.test.service;


import com.liangfeng.study.core.constant.AppConstant;
import com.liangfeng.study.core.framework.base.BaseTest;
import com.liangfeng.study.core.helper.ObjectHelper;
import com.liangfeng.study.core.web.dto.request.GetRequestbody;
import com.liangfeng.study.core.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.core.web.dto.response.AddResponsebody;
import com.liangfeng.study.dict.model.auto.pojo.Dict;
import com.liangfeng.study.dict.mapper.DictMapper;
import com.liangfeng.study.dict.service.DictService;
import com.liangfeng.study.dict.web.request.DictAddOrMdfRequestbody;
import com.liangfeng.study.dict.web.request.DictQueryRequestbody;
import com.liangfeng.study.dict.web.response.DictGetResponsebody;
import com.liangfeng.study.dict.web.response.DictQueryResponsebody;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictServiceTest
 * @Description:
 * @date 2018-06-09
 */
@Transactional
@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
public class DictServiceTest extends BaseTest {

    // 不需要校验的字段名称数组
    private static final String[] noneValidFileds = {AppConstant.SESSION_ATTR_NAME_USERID, AppConstant.SESSION_ATTR_NAME_USERROLES};

    private static final String SYS_CODE = "SCD";

    @Autowired
    DictMapper dictMapper;

    @Autowired
    DictService service;

    @Before
    public void before() {
        // 1.清除已有的数据，避免影响测试
        List<Dict> dicts = dictMapper.query(null);
        for (Dict dict : dicts) {
            dictMapper.delete(dict.getId());
        }
    }

    @Test
    public void testAdd() {
        // 1.定义参数
        DictAddOrMdfRequestbody requestbody;
        AddResponsebody responsebody;
        Dict dict;
        String errMsg = "测试添加字典失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        requestbody = new DictAddOrMdfRequestbody();
        ObjectHelper.initFieldsVal(requestbody, 1);
        requestbody.setSysCode(SYS_CODE);
        // 执行
        responsebody = service.add(requestbody);
        // 验证结果
        dict = dictMapper.get(requestbody.getId());
        compareObjProps(errMsg, requestbody, dict, noneValidFileds);
        Assert.assertEquals(errMsg, requestbody.getId(), responsebody.getId());
    }

    @Test
    public void testModify() {
        // 1.定义参数
        DictAddOrMdfRequestbody requestbody;
        Dict dict;
        String errMsg = "测试修改字典失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        dict = new Dict();
        ObjectHelper.initFieldsVal(dict, 1);
        dict.setSysCode("0000");
        dictMapper.insert(dict);
        requestbody = new DictAddOrMdfRequestbody();
        ObjectHelper.initFieldsVal(requestbody, 2);
        requestbody.setId(dict.getId());
        requestbody.setSysCode(SYS_CODE);
        // 运行
        service.modify(requestbody);
        // 验证结果
        dict = dictMapper.get(requestbody.getId());
        compareObjProps(errMsg, requestbody, dict, noneValidFileds);
    }

    @Test
    public void testRemove() {
        // 1.定义参数
        RemoveRequestbody requestbody;
        Dict dict;
        String errMsg = "测试删除字典失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        requestbody = new RemoveRequestbody();
        for (int i = 1; i <= 3; i++) {
            dict = new Dict();
            ObjectHelper.initFieldsVal(dict, i);
            dict.setSysCode(SYS_CODE);
            dictMapper.insert(dict);
            requestbody.add(dict.getId());
        }
        // 运行
        service.remove(requestbody);
        List<Dict> dicts = dictMapper.query(null);
        Assert.assertEquals(errMsg, 0, dicts.size());
    }

    @Test
    public void testGet() {
        // 1.定义参数
        GetRequestbody requestbody;
        DictGetResponsebody responsebody;
        Dict dict;
        String errMsg = "测试获取字典失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        dict = new Dict();
        ObjectHelper.initFieldsVal(dict, 1);
        dict.setSysCode(SYS_CODE);
        dictMapper.insert(dict);
        requestbody = new GetRequestbody();
        requestbody.setId(dict.getId());
        // 运行
        responsebody = service.get(requestbody);
        // 验证结果
        compareObjProps(errMsg, dict, responsebody, noneValidFileds);
    }

    @Test
    public void testQuery() {
        // 1.定义参数
        DictQueryRequestbody requestbody;
        DictQueryResponsebody responsebody;
        Dict dict;
        String errMsg = "测试查询字典失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        List<Dict> dicts = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            dict = new Dict();
            ObjectHelper.initFieldsVal(dict, i);
            dict.setSysCode(SYS_CODE);
            dictMapper.insert(dict);
            dicts.add(dict);
        }
        requestbody = new DictQueryRequestbody();
        // 运行
        responsebody = service.query(requestbody);
        // 验证结果
        List<DictGetResponsebody> getResponsebodies = responsebody.getRows();
        for (int i = 0; i < 3; i++) {
            compareObjProps(errMsg, dicts.get(i), getResponsebodies.get(i));
        }
    }

    @Test
    public void testQueryPage() {
        // 1.定义参数
        DictQueryRequestbody requestbody;
        DictQueryResponsebody responsebody;
        Dict dict;
        String errMsg = "测试查询字典失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        List<Dict> dicts = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            dict = new Dict();
            ObjectHelper.initFieldsVal(dict, i);
            dict.setSysCode(SYS_CODE);
            dictMapper.insert(dict);
            dicts.add(dict);
        }
        requestbody = new DictQueryRequestbody();
        requestbody.setPageNum(1);
        requestbody.setPageSize(1);
        requestbody.setSortColumns("id desc");
        // 运行
        responsebody = service.queryPage(requestbody);
        // 验证结果
        Assert.assertEquals(errMsg, 3, responsebody.getTotal());
        Assert.assertEquals(errMsg, 1, responsebody.getRows().size());
        compareObjProps(errMsg, dicts.get(2), responsebody.getRows().get(0));
    }


    @Test
    public void testQueryForWebCache() {
        // 1.定义参数
        DictQueryRequestbody requestbody;
        Map<String, List<DictGetResponsebody>> dicMap;
        String sysCode = SYS_CODE;
        Dict dict;
        String errMsg = "测试返回给前端页面做数据缓存失败";

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        for (int i = 1; i <= 3; i++) {
            dict = new Dict();
            ObjectHelper.initFieldsVal(dict, i);
            dict.setSysCode(SYS_CODE);
            dictMapper.insert(dict);
        }
        requestbody = new DictQueryRequestbody();
        requestbody.setSysCode(sysCode);
        // 运行
        dicMap = service.queryForWebCache(requestbody);
        // 验证结果
        Assert.assertEquals(errMsg, dicMap.size(), 3);
    }


}
