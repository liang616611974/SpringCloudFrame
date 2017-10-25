package com.liangfeng.study.common.framework.base;

import com.liangfeng.study.common.framework.page.Page;
import com.liangfeng.study.common.framework.page.PageRequest;

import java.io.Serializable;
import java.util.List;


/**
* @Title: BaseDao.java
* @Description: 实体dao接口 包含一些实体的共有的数据库操作
* @author Liangfeng
* @date 2016-10-13
* @version 1.0
 */
public interface BaseDao <E extends Serializable,Q extends PageRequest,PK extends Serializable>{
	
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
	E get(Q query);
	
	/**
	 * 查询所有对象
	 * @return
	 */
	List<E> query();
	
	/**
	 * 根据查询对象条件查询
	 * @param query
	 * @return
	 */
	List<E> query(Q query);
	
	/**
	 * 根据查询对象条件分页查询
	 * @param query
	 * @return
	 */
	Page<E> queryPage(Q query);
	
	/**
	 * 根据查询对象条件统计
	 * @param query
	 * @return
	 */
	int count(Q query);
	
}
