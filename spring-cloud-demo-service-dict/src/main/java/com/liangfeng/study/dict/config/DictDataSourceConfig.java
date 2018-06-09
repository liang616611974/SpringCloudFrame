package com.liangfeng.study.dict.config;


import com.liangfeng.study.core.config.DataSourceConfig;
import lombok.Data;
import org.springframework.context.annotation.Configuration;


/**
 * @author Liangfeng
 * @version 1.0
 * @Title: GoodsDataSourceConfig
 * @Description:
 * @date  2018/4/17 23:38
 */

@Configuration
@Data
public class DictDataSourceConfig extends DataSourceConfig {


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

