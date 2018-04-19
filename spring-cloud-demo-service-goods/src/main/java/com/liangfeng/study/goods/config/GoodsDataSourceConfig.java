package com.liangfeng.study.goods.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.liangfeng.study.core.config.DataSourceConfig;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;




/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsDataSourceConfig
 * @Description:
 * @date  2018/4/17 23:38
 */
@Configuration
//@ConfigurationProperties(prefix = "spring.datasource")
@Data
public class GoodsDataSourceConfig extends DataSourceConfig {


 /*   *//**
     * 创建 primaryDataSource
     * @return
     *//*
    @Bean
   // @Qualifier("primaryDataSource")
    //@Primary
    public DataSource primaryDataSource(){
        return getDataSource();
    }

    *//**
     * 创建primaryTxManager
     * @param dataSource
     * @return
     *//*
    @Bean(name ="primaryTxManager")
    @Qualifier("primaryTxManager")
    @Primary
    public PlatformTransactionManager primaryTxManager(@Qualifier("primaryDataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

 /*   @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return primaryTxManager;
    }*/


}
