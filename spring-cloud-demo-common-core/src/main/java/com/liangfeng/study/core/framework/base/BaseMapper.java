package com.liangfeng.study.core.framework.base;

import com.liangfeng.study.core.framework.page.Page;
import com.liangfeng.study.core.framework.page.PageRequest;

import java.io.Serializable;
import java.util.List;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: BaseMapper 实体Mapper接口 包含一些实体的共有的数据库操作
 * @Description:
 * @date  2018/4/22 0022 上午 1:55
 */
public interface BaseMapper <E extends Serializable,Q extends PageRequest,PK extends Serializable>{

    /**
     * 插入实体
     * @param entity 实体
     * @return
     */
    int insert(E entity);

    /**
     * 更新实体
     * @param entity 实体
     * @return
     */
    int update(E entity);

    /**
     * 根据主键删除实体
     * @param id 主键
     * @return
     */
    int delete(PK id);

    /**
     * 根据主键获取实体
     * @param id 主键
     * @return
     */
    E get(PK id);


    /**
     * 根据查询对象条件查询
     * @param query
     * @return
     */
    List<E> query(Q query);

   /* *//**
     * 根据查询对象条件分页查询
     * @param query
     * @return
     *//*
    List<E> queryPage(Q query);*/

    /**
     * 根据查询对象条件统计
     * @param query
     * @return
     */
    int count(Q query);

}
