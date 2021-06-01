package com.goodmap.hospital.config.dataSources;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘智强
 * @date 2021/2/23
 * @Description
 */
@Configuration
public class DataSourceConfig {
    @Bean(name = "indoormap")
    @ConfigurationProperties(prefix = "spring.datasource.indoormap")
    public DataSource indoormap() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "indoormapsde")
    @ConfigurationProperties(prefix = "spring.datasource.indoormapsde")
    public DataSource indoormapsde() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     */

    @Primary
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //默认数据源
        dynamicDataSource.setDefaultTargetDataSource(indoormap());
        //配置多数据源
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put("indoormap",indoormap());
        dsMap.put("indoormapsde",indoormapsde());

        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 配置@Transactional注解事物
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
