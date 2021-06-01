package com.goodmap.hospital.config.dataSources;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author 刘智强
 * @date 2021/2/23
 * @Description
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDB();
    }
}
