package com.liangfeng.study.goods.test.service;


import com.liangfeng.study.core.component.id.IdGenerator;
import com.liangfeng.study.core.constant.AppConstant;
import com.liangfeng.study.core.framework.base.BaseTest;
import com.liangfeng.study.core.helper.DateHelper;
import com.liangfeng.study.core.helper.ObjectHelper;
import com.liangfeng.study.goods.mapper.GoodsMapper;
import com.liangfeng.study.goods.model.auto.pojo.Goods;
import com.liangfeng.study.goods.service.GoodsService;
import com.liangfeng.study.goods.web.request.GoodsAddOrMdfRequestbody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsServiceTest
 * @Description:
 * @date  2018/4/22 0022 上午 12:04
 */
@Transactional
@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceTest extends BaseTest{

    // 不需要校验的字段名称数组
    private static final String[] noneValidFileds = {AppConstant.SESSION_ATTR_NAME_USERID, AppConstant.SESSION_ATTR_NAME_USERROLES};

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsService service;

    @Test
    public void testAdd(){
        // 1.定义参数
        Date now = new Date();
        GoodsAddOrMdfRequestbody requestbody;
        Goods goods;

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        requestbody = new GoodsAddOrMdfRequestbody();
        ObjectHelper.initFieldsVal(requestbody,1);
        // 执行
        service.add(requestbody);
        // 验证结果
        goods = goodsMapper.get(requestbody.getId());
        compareObjProps("测试添加商品失败",requestbody,goods,noneValidFileds);
    }

    @Test
    public void testModify(){
        // 1.定义参数
        Date now = new Date();
        GoodsAddOrMdfRequestbody requestbody;
        Goods goods;

        // 2.测试情景
        // 2.1 测试情景一
        // 构造测试数据
        goods = new Goods();
        ObjectHelper.initFieldsVal(goods,1);
        goodsMapper.insert(goods);
        requestbody = new GoodsAddOrMdfRequestbody();
        ObjectHelper.initFieldsVal(requestbody,2);
        requestbody.setId(goods.getId());
        // 运行
        service.modify(requestbody);
        // 验证结果
        goods = goodsMapper.get(requestbody.getId());
        compareObjProps("测试修改商品失败",requestbody,goods,noneValidFileds);
    }

}
