package com.example.readwrite.web.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.readwrite.config.DataSourceConfig;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReadWriteService {
    public JdbcTemplate template;

    public void update() {
        printDS();
    }

    public synchronized void get() {
        printDS();
    }
    public synchronized void put() {
        printDS();
    }

    synchronized void  printDS() {

        try {
            DruidDataSource dataSource = (DruidDataSource) DataSourceConfig.getJdbcTemplate().getDataSource();
            System.out.println(dataSource.getUrl());
        } catch (Exception e) {
            System.out.println("数据库连接失败");
        }
    }
}
