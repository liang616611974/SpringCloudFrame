package com.liangfeng.study.goods.test.service;


import com.liangfeng.study.core.component.id.IdGenerator;
import com.liangfeng.study.core.framework.base.BaseTest;
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

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    GoodsService service;

    @Test
    public void testAdd(){
        // 1.定义参数
        Date now = new Date();
        GoodsAddOrMdfRequestbody requestbody;

        // 2.测试情景
        // 2.1 测试情景一
        requestbody = new GoodsAddOrMdfRequestbody();
        requestbody.setId(idGenerator.generateId());
        requestbody.setGoodsName("商品名称1");
        requestbody.setType(new String("1"));
        requestbody.setPrice(new BigDecimal("1"));
        requestbody.setProducerName(new String("1"));
        requestbody.setProduceDate(now);
        requestbody.setCreateTime(now);
        requestbody.setModifyTime(now);
        requestbody.setCreateUser(new Long("1"));
        requestbody.setModifyUser(new Long("1"));
        service.add(requestbody);
        Goods goods = goodsMapper.get(requestbody.getId());
        validObjProps("测试添加商品失败",goods);
    }

}
