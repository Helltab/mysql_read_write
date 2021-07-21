package com.example.readwrite;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.readwrite.aop.TestAop;
import com.example.readwrite.config.DataSourceConfig;
import com.example.readwrite.web.service.ReadWriteService;
import com.mysql.cj.log.Log;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ReadWriteApplicationTests {

    @Resource
    JdbcTemplate masterTemplate;
    @Resource
    JdbcTemplate slaveTemplate;
    @Resource
    ReadWriteService readWriteService;

    @Test
    void read() {
        System.out.println(slaveTemplate);
        List<Student> query = slaveTemplate.query("select * from student",
                new BeanPropertyRowMapper<Student>(Student.class));
        System.out.println(query);
    }

    @Test
    void write() {
        masterTemplate.update("update student set teacher = 'Li'");
    }

    @Test
    void aopTest() throws IOException {
        CountDownLatch countDownLatch = new CountDownLatch(40);
        for (int i = 0; i < 40; i++) {
            int finalI = i;
            new Thread(() -> {
//                countDownLatch.countDown();
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                if ((finalI & 1) == 0) {
//                    readWriteService.update();
                } else {
                    readWriteService.get();
                }
            }).start();
        }
        while (true) {
        }
    }
}


@Data
class Student {
    int id;
    String sName;
    String teacher;
    int age;
    int sex;
}
