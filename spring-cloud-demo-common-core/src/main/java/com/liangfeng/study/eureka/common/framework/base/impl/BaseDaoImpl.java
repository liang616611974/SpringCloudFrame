package com.liangfeng.study.eureka.common.framework.base.impl;

import com.liangfeng.study.eureka.common.exception.DaoException;
import com.liangfeng.study.eureka.common.framework.page.Page;
import com.liangfeng.study.eureka.common.framework.page.PageRequest;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: BaseDaoImpl
 * @Description:
 * @date 2017/5/25 0025 下午 5:33
 */
public abstract class BaseDaoImpl {

    private static final Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

    private static final String SEPARATOR = ".";
    private static final String PAGE_FILTER_OFFSET_KEY = "offset";
    private static final String PAGE_FILTER_PAGESIZE_KEY = "pageSize";
    private static final String PAGE_FILTER_LASTROWS_KEY = "lastRows";
    private static final String PAGE_FILTER_SORTCOLUMNS_KEY = "sortColumns";

    private SqlSessionTemplate sqlSessionTemplate;

    public abstract String getIbatisMapperNamesapce();

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    /**
     * 获取单条记录
     * @param statementName sql名
     * @param param 参数
     * @param <T>
     * @return
     */
    protected <T> T get(String statementName, Object param) {
        return (T) sqlSessionTemplate.selectOne(getIbatisMapperNamesapce() + SEPARATOR + statementName, param);
    }

    /**
     * 查询多条记录
     * @param statementName sql名
     * @param param 参数
     * @param <T>
     * @return
     */
    protected <T> List<T> query(String statementName, Object param) {
        return (List<T>) sqlSessionTemplate.selectList(getIbatisMapperNamesapce() + SEPARATOR + statementName, param);
    }

    /**
     * 分页查询
     * @param statementName 分页查询sql名
     * @param countStatementName 统计查询sql名
     * @param pageRequest 分页请求实体类
     * @param <T>
     * @return
     */
    protected <T> Page<T> queryPage(String statementName, String countStatementName, PageRequest pageRequest) {
        Page<T> page = null;
        try {
            //1.先统计记录
            Number totalCount = (Number) sqlSessionTemplate.selectOne(getIbatisMapperNamesapce() + SEPARATOR + countStatementName, pageRequest);

            //2.如果统计记录为0，则不查实际数据
            if (totalCount == null || totalCount.longValue() <= 0) {
                page = new Page<T>(0, pageRequest.getPageNum(), pageRequest.getPageSize(), pageRequest.getPageWidth());
                page.setResult(new ArrayList<T>(0));
                return page;
            }

            //3.如果统计记录大于0，则开始查实际数据
            page = new Page<T>(totalCount.intValue(), pageRequest.getPageNum(), pageRequest.getPageSize(), pageRequest.getPageWidth());

            //3.1构建其它分页参数,用于不喜欢或是因为兼容性而不使用方言(Dialect)的分页用户使用. 与getSqlMapClientTemplate().queryForList(statementName, parameterObject)配合使用
            Map<String, Object> filters = new HashMap<String, Object>();
            filters.put(PAGE_FILTER_OFFSET_KEY, page.getFirstResultIndex());
            filters.put(PAGE_FILTER_PAGESIZE_KEY, page.getPageSize());
            filters.put(PAGE_FILTER_LASTROWS_KEY, page.getFirstResultIndex() + page.getPageSize());
            filters.put(PAGE_FILTER_SORTCOLUMNS_KEY, pageRequest.getSortColumns());

            //3.2将分页对象转成map对象,作为查询条件
            Map<String, Object> parameterObject = null;
            parameterObject = PropertyUtils.describe(pageRequest);
            filters.putAll(parameterObject);

            //3.3利用mybatis分页插件查询数据
            List<T> list = sqlSessionTemplate.selectList(getIbatisMapperNamesapce() + SEPARATOR + statementName, filters, new RowBounds((int) page.getFirstResultIndex(), (int) page.getPageSize()));
            page.setResult(list);
        } catch (Exception e) {
            logger.error("BaseDaoImpl分页查询 异常", e);
            throw new DaoException("BaseDaoImpl分页查询 异常", e);
        }
        return page;
    }

}