package com.liangfeng.study.dict.service.impl;


import com.liangfeng.study.dict.model.auto.pojo.Dict;
import com.liangfeng.study.dict.model.auto.qo.DictQuery;
import com.liangfeng.study.dict.mapper.DictMapper;
import com.liangfeng.study.dict.service.DictService;
import com.liangfeng.study.core.web.dto.request.GetRequestbody;
import com.liangfeng.study.core.web.dto.request.RemoveRequestbody;
import com.liangfeng.study.core.web.dto.response.AddResponsebody;
import com.liangfeng.study.dict.web.request.DictQueryRequestbody;
import com.liangfeng.study.dict.web.request.DictAddOrMdfRequestbody;
import com.liangfeng.study.dict.web.response.DictGetResponsebody;
import com.liangfeng.study.dict.web.response.DictQueryResponsebody;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictServiceImpl
 * @Description:
 * @date 2018-06-09
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
@Service
public class DictServiceImpl implements DictService {

    @Autowired
    DictMapper dictMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public AddResponsebody add(DictAddOrMdfRequestbody requestbody) {
        // 1.定义参数
        Dict dict = new Dict();
        // 2.复制属性值
        BeanUtils.copyProperties(requestbody,dict);
        // 3.插入数据
        dictMapper.insert(dict);
        // 4.返回主键
        return new AddResponsebody(dict.getId());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void modify(DictAddOrMdfRequestbody requestbody) {
        // 1.定义参数
        Dict dict = new Dict();
        // 2.复制属性值
        BeanUtils.copyProperties(requestbody,dict);
        // 3.修改数据
        dictMapper.update(dict);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void remove(RemoveRequestbody requestbody) {
        // 1.遍历删除
        for (Long id : requestbody.getIds()) {
            dictMapper.delete(id);
        }
    }

    @Override
    public DictGetResponsebody get(GetRequestbody requestbody) {
        // 1.定义参数
        DictGetResponsebody responseBody = new DictGetResponsebody();
        // 2.查询对象
        Dict dict = dictMapper.get(requestbody.getId());
        // 3.复制属性
        BeanUtils.copyProperties(dict,responseBody);
        // 4.返回数据
        return responseBody;
    }

    @Override
    public DictQueryResponsebody query(DictQueryRequestbody requestbody) {
        // 1.定义参数
        DictQueryResponsebody responseBody = new DictQueryResponsebody();
        DictQuery dictQuery = new DictQuery();
        BeanUtils.copyProperties(requestbody,dictQuery);

        // 2.查询
        requestbody.setSortColumns("id desc");
        List<Dict> dicts = dictMapper.query(dictQuery);
        List<DictGetResponsebody> getResponseBodies = responseBody.getRows();
        for (Dict dict : dicts) {
            DictGetResponsebody getResponseBody = new DictGetResponsebody();
            BeanUtils.copyProperties(dict, getResponseBody);
            getResponseBodies.add(getResponseBody);
        }
        return responseBody;
    }

    @Override
    public DictQueryResponsebody queryPage(DictQueryRequestbody requestbody) {
        // 1.定义参数
        DictQueryResponsebody responseBody = new DictQueryResponsebody();
        DictQuery dictQuery = new DictQuery();
        BeanUtils.copyProperties(requestbody,dictQuery);
        int total = 0;

        // 2.查询总数
        total = dictMapper.count(dictQuery);
        responseBody.setTotal(total);

        // 3.分页查询集合
        // 3.1 如何没有数据则直接返回。
        if(responseBody.getTotal()==0){
            return responseBody;
        }

        // 3.2 有数据
        requestbody.setSortColumns("id desc");
        List<Dict> dicts = dictMapper.queryPage(dictQuery);
        List<DictGetResponsebody> getResponseBodies = responseBody.getRows();
        for (Dict dict : dicts) {
            DictGetResponsebody getResponseBody = new DictGetResponsebody();
            BeanUtils.copyProperties(dict, getResponseBody);
            getResponseBodies.add(getResponseBody);
        }
        return responseBody;
    }
}

