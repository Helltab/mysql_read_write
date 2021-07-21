package com.example.readwrite.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


@Configuration
public class DataSourceConfig {
    private static ThreadLocal<JdbcTemplate> jdbcTemplateThreadLocal = new ThreadLocal<>();
    private static List<DruidDataSource> slaves = new ArrayList<>();
    private static int slavesIndex;

    @Bean("masterDataSource")
    DruidDataSource master() {
        DruidDataSource masterDataSource = new DruidDataSource();
        masterDataSource.setUrl("jdbc:mysql://192.168.56.102:8880/test_copy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        masterDataSource.setUsername("root");
        masterDataSource.setPassword("123456");
        return masterDataSource;
    }

    @Bean("masterTemplate")
    JdbcTemplate masterTemplate(DruidDataSource masterDataSource) {
        return new JdbcTemplate(masterDataSource);
    }

    @Bean("slaveDataSource1")
    DruidDataSource slave1() {
        DruidDataSource slaveDataSource = new DruidDataSource();
        slaveDataSource.setUrl("jdbc:mysql://192.168.56.102:8881/test_copy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        slaveDataSource.setUsername("root");
        slaveDataSource.setPassword("123456");
        slaves.add(slaveDataSource);
        return slaveDataSource;
    }

    @Bean("slaveDataSource2")
    DruidDataSource slave2() {
        DruidDataSource slaveDataSource = new DruidDataSource();
        slaveDataSource.setUrl("jdbc:mysql://192.168.56.102:8882/test_copy?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        slaveDataSource.setUsername("root");
        slaveDataSource.setPassword("123456");
        slaves.add(slaveDataSource);
        return slaveDataSource;
    }

    @Bean("slaveTemplate")
    JdbcTemplate slaveTemplate(DruidDataSource slaveDataSource1) {
        return new JdbcTemplate(slaveDataSource1);
    }

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplateThreadLocal.get();
    }

    public static void setJdbcTemplate(JdbcTemplate template) {
        jdbcTemplateThreadLocal.set(template);
    }
    /**
     * 轮询算法
     * @return
     */
    public synchronized  static DataSource nextSlave() {
        if (slavesIndex == slaves.size()) {
            slavesIndex = 0;
        }
        return slaves.get(slavesIndex++);
    }
}
