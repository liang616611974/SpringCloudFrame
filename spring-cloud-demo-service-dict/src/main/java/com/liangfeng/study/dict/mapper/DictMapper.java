package com.liangfeng.study.dict.mapper;


import com.liangfeng.study.api.dto.request.DictApiQueryRequestbody;
import com.liangfeng.study.core.framework.base.BaseMapper;
import com.liangfeng.study.dict.model.auto.pojo.Dict;
import com.liangfeng.study.dict.model.auto.qo.DictQuery;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DictMapper
 * @Description:
 * @date 2018-06-09
 */
@Mapper
public interface DictMapper extends BaseMapper<Dict,DictQuery,Long> {

    List<Dict> queryBySysCodeAndGroupCodes(DictApiQueryRequestbody requestbody);
}
