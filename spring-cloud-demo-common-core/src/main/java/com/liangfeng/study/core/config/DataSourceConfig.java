package com.liangfeng.study.core.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.liangfeng.study.core.constant.AppConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DataSourceConfig 数据连接池配置
 * @Description:
 * @date  2018/4/16 0016 下午 9:51
 */
/*@EnableTransactionManagement*/
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {

    private String url;

    private String pwd;


    /**
     * 创建 primaryDataSource
     * @return
     */
  /*  @Bean(name = "aaa")
   // @Qualifier(DatabaseConstant.WEB_DATASOURCE)
    //@Primary
    //@ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource(){
        return null;
    }*/



   /*

    *//**
     * 创建 DruidDataSource 数据连接池
     * @param dbName 数据库名称
     * @param dataSourceName 数据源名称
     * @return
     *//*
    private DruidDataSource getDruidDataSource(String dbName, String dataSourceName){
        logger.info("===============创建 " + dataSourceName + " 数据源 开始===========");
        // 1.校验配置参数完整性
        if(StringUtils.isBlank(propertyResolver.getProperty( dbName + ".url"))||
                StringUtils.isBlank(propertyResolver.getProperty(dbName + ".username"))||
                StringUtils.isBlank(propertyResolver.getProperty(dbName + ".password"))||
                StringUtils.isBlank(propertyResolver.getProperty(dbName + ".driver"))){
            logger.error(dataSourceName + " 数据源配置信息不完整");
            throw new ApplicationContextException(dataSourceName + " 数据源配置信息不完整");
        }
        // 2.创建 DruidDataSource 连接池
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(propertyResolver.getProperty( dbName + ".url"));
        dataSource.setUsername(propertyResolver.getProperty(dbName + ".username"));
        dataSource.setPassword(propertyResolver.getProperty(dbName + ".password"));
        dataSource.setDriverClassName(propertyResolver.getProperty(dbName + ".driver"));

        //池配置
        dataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty(dbName + ".initialSize")));
        dataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty(dbName + ".minIdle")));
        dataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty(dbName + ".maxActive")));
        dataSource.setMaxWait(Long.parseLong(propertyResolver.getProperty(dbName + ".maxWait")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(propertyResolver.getProperty(dbName + ".timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(propertyResolver.getProperty(dbName + ".minEvictableIdleTimeMillis")));
        dataSource.setValidationQuery(propertyResolver.getProperty(dbName + ".validationQuery"));
        dataSource.setTestWhileIdle(Boolean.valueOf(propertyResolver.getProperty(dbName + ".testWhileIdle")));
        dataSource.setTestOnBorrow(Boolean.valueOf(propertyResolver.getProperty(dbName + ".testOnBorrow")));
        dataSource.setTestOnReturn(Boolean.valueOf(propertyResolver.getProperty(dbName + ".testOnReturn")));

        logger.info("===============创建 " +  dataSourceName + " 数据源 结束===========");
        return dataSource;
    }
*/

}
