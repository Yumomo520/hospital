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
@MapperScan(basePackages = SecondDataSourceConfig.PACKAGE,sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDataSourceConfig {

//    //精确到master目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.goodmap.hospital.pojo.second";
//    static final String MAPPER_LOCATION = "classpath*：mapper/";
//    @Value("${spring.datasource.second.url}")
//    private String url;
//    @Value("${spring.datasource.second.username}")
//    private String user;
//    @Value("${spring.datasource.second.password}")
//    private String password;
//    @Value("${spring.datasource.second.driver-class-name}")
//    private String driverClass;
//
//    @Bean(name = "secondDataSource")
//    public DataSource clusterDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    @Bean(name = "secondTransactionManager")
//    public DataSourceTransactionManager clusterTransactionManager() {
//        return new DataSourceTransactionManager(clusterDataSource());
//    }
//
//    @Bean(name = "secondSqlSessionFactory")
//    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("secondDataSource") DataSource clusterDataSource)
//        throws Exception {
//
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(clusterDataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(SecondDataSourceConfig.MAPPER_LOCATION));
//        return sessionFactory.getObject();
//    }

}
