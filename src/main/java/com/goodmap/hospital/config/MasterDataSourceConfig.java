package com.goodmap.hospital.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 刘智强
 * @date 2021/2/23
 * @Description
 */
@Configuration
@MapperScan(basePackages = "MasterDataSourceConfig.PACKAGE",sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

//    //精确到master目录，以便跟其他数据源隔离
//    static final String PACKAGE = "com.goodmap.hospital.mapper";
//    static final String MAPPER_LOCATION = "classpath*：mapper/";
//    @Value("${spring.datasource.master.url}")
//    private String url;
//    @Value("${spring.datasource.master.username}")
//    private String user;
//    @Value("${spring.datasource.master.password}")
//    private String password;
//    @Value("${spring.datasource.master.driver-class-name}")
//    private String driverClass;
//
//    @Bean(name = "masterDataSource")
//    @Primary
//    public DataSource masterDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    @Bean(name = "masterTransactionManager")
//    @Primary
//    public DataSourceTransactionManager masterTransactionManager() {
//        return new DataSourceTransactionManager(masterDataSource());
//    }
//
//    @Bean(name = "masterSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
//        throws Exception {
//
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(masterDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
//        return sessionFactory.getObject();
//    }

}
