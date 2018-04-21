package com.liangfeng.study.core.config;


import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Liangfeng
 * @version 1.0
 * @Title: DataSourceConfig 数据连接池配置
 * @Description:
 * @date  2018/4/16 0016 下午 9:51
 */
@EnableTransactionManagement
@ConfigurationProperties(prefix = "spring.druid.datasource")
@Data
public class DataSourceConfig implements TransactionManagementConfigurer{

    private static final Logger logger = LoggerFactory.getLogger(DataSource.class);

    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
    private Long maxWait;
    private Long timeBetweenEvictionRunsMillis;
    private Long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
    private boolean useGlobalDataSourceStat;


    /**
     * 创建 primaryDataSource
     * @return
     */
    @Bean
    @Qualifier
    @Primary
    public DataSource primaryDataSource(){
        return createDataSource();
    }

    /**
     * 创建primaryTxManager
     * @return
     */
    @Bean
    @Qualifier
    @Primary
    public PlatformTransactionManager primaryTxManager() {
        return new DataSourceTransactionManager(primaryDataSource());
    }

    /**
     * 创建 primarySqlSessionFactory
     * @return
     */
    @Bean
    @Qualifier
    @Primary
    public SqlSessionFactory primarySqlSessionFactory(){
        return createSqlSessionFactory(primaryDataSource());
    }

    /**
     * 创建 primarySqlSessionTemplate
     * @return
     */
    @Bean
    @Qualifier
    @Primary
    public SqlSessionTemplate primarySqlSessionTemplate(){
        return new SqlSessionTemplate(primarySqlSessionFactory());
    }

    /**
     * 注册默认事务管理器
     * @return
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return primaryTxManager();
    }


    /**
     * 创建Mybatis SqlSessionFactory
     * @param dataSource
     * @return
     */
    public SqlSessionFactory createSqlSessionFactory(DataSource dataSource){
        logger.info("===============创建 Mybatis SqlSessionFactory 开始===========");
        // 1.定义参数
        SqlSessionFactory sessionFactory = null;
        // 2.创建工厂对象
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        // 3.设置相关属性
        // 3.1 设置数据源
        sessionFactoryBean.setDataSource(dataSource);
        try{
            // 3.2 设置Mapper.xml文件路径
            sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                    .getResources("classpath:mapper/*/*.xml"));
            // 3.3 设置mybatis配置文件路径
            sessionFactoryBean.setConfigLocation(new DefaultResourceLoader()
                    .getResource("classpath:mybatis-config.xml"));
            // 4.获取工厂对象
            sessionFactory = sessionFactoryBean.getObject();
        }catch (Exception e){
            logger.error("创建 Mybatis SqlSessionFactory 发生异常", e);
            throw new ApplicationContextException("创建 Mybatis SqlSessionFactory 发生异常",e);
        }
        logger.info("===============创建 Mybatis SqlSessionFactory 结束===========");
        // 5.返回工厂对象
        return sessionFactory;
    }

    /**
     * 创建数据库连接池
     * @return
     */
    public DataSource createDataSource() {
       return createDataSource(this.url,this.username,this.password,this.driverClassName);
    }

    /**
     * 创建数据连接池
     *
     * @param url
     * @param username
     * @param password
     * @param driverClassName
     * @return
     */

    public DataSource createDataSource(String url,String username,String password,String driverClassName) {
        logger.info("===============创建 {} 数据源 开始===========", url);
        // 1.校验配置参数完整性
        if (StringUtils.isBlank(url) ||
                StringUtils.isBlank(username) ||
                StringUtils.isBlank(password) ||
                StringUtils.isBlank(driverClassName)) {
            throw new ApplicationContextException(url + " 数据源配置信息不完整");
        }
        // 2.创建 DruidDataSource 连接池
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        String validationQuery = this.validationQuery;
        if (StringUtils.isNotBlank(validationQuery)) {
            dataSource.setValidationQuery(validationQuery);
        }
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        if (poolPreparedStatements) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        }
        String connectionPropertiesStr = connectionProperties;
        if (StringUtils.isNotBlank(connectionPropertiesStr)) {
            Properties connectProperties = new Properties();
            String[] propertiesArr = connectionPropertiesStr.split(";");
            for (String propertiesTmp : propertiesArr) {
                String[] obj = propertiesTmp.split("=");
                String key = obj[0];
                String value = obj[1];
                connectProperties.put(key, value);
            }
            dataSource.setConnectProperties(connectProperties);
        }
        dataSource.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        try {
            dataSource.setFilters(filters);//这是最关键的,否则SQL监控无法生效
        } catch (Exception e) {
            logger.error("{} 创建数据连接池发生异常", url, e);
            throw new ApplicationContextException(url + " 创建数据连接池发生异常",e);
        }
        logger.info("===============创建 {} 数据源 结束===========", url);
        return dataSource;
    }

    /* @Resource(name="druidSettings")
    private DruidSettings druidSettings;*/

    /*@ConfigurationProperties(prefix = "spring.druid.datasource")
    @PropertySource("classpath:/druid-config.properties")
    @Component("druidSettings")
    @Data
    public class DruidSettings {
        private String type;
        private String driverClassName;
        private String url;
        private String username;
        private String password;
        private Integer initialSize;
        private Integer minIdle;
        private Integer maxActive;
        private Long maxWait;
        private Long timeBetweenEvictionRunsMillis;
        private Long minEvictableIdleTimeMillis;
        private String validationQuery;
        private boolean testWhileIdle;
        private boolean testOnBorrow;
        private boolean testOnReturn;
        private boolean poolPreparedStatements;
        private Integer maxPoolPreparedStatementPerConnectionSize;
        private String filters;
        private String connectionProperties;
        private boolean useGlobalDataSourceStat;
    }*/



}
