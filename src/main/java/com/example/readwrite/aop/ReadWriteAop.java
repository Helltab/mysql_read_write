package com.example.readwrite.aop;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.readwrite.config.DataSourceConfig;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.lang.reflect.Field;

/**
 * Topic
 * execution：用于匹配方法执行的连接点
 * excution(public String com.example.readwrite.aop.TestAop.write*(String) throws java.io.IOException)
 * within：用于匹配指定类型内的方法执行
 * this：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也* 类型匹配
 * target：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配
 * args：用于匹配当前执行的方法传入的参数为指定类型的执行方法
 *
 * @author helltab
 * @version 1.0
 * @within：用于匹配所以持有指定注解类型内的方法
 * @target：用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解
 * @args：用于匹配当前执行的方法传入的参数持有指定注解的执行
 * @annotation：用于匹配当前执行方法持有指定注解的方法 bean：Spring AOP扩展的，AspectJ没有对于指示符，用于匹配特定名称的Bean对象的执行方法
 * 10种标签组成了12种用法
 * @date 2021/7/3 8:19
 */
@Aspect
@Component
public class ReadWriteAop {
    @Pointcut("" +
                      "execution(* com.example.readwrite.*.service.*.update*(..)) " +
                      "||execution(* com.example.readwrite.*.service.*.add*(..)) " +
                      "||execution(* com.example.readwrite.*.service.*.set*(..)) " +
                      "||execution(* com.example.readwrite.*.service.*.put*(..)) " +
                      "||execution(* com.example.readwrite.*.service.*.edit*(..)) " +
                      "||execution(* com.example.readwrite.*.service.*.modify*(..)) " +
                      "||execution(* com.example.readwrite.*.service.*.del*(..)) " +
                      "")
    public void write() {
    }

    @Pointcut("" +
                      "execution(* com.example.readwrite.*.service.*.get*(..)) " +
                      "||execution(* com.example.readwrite.*.service.*.find*(..)) " +
                      "||execution(* com.example.readwrite.*.service.*.list*(..)) " +
                      "||execution(* com.example.readwrite.*.service.*.query*(..)) " +
                      "")
    public void read() {
    }

    @Resource
    JdbcTemplate slaveTemplate;
    @Resource
    JdbcTemplate masterTemplate;

    @Before("write()")
    public synchronized void beforeWrite(JoinPoint joinPoint) {
        DataSourceConfig.setJdbcTemplate(masterTemplate);
    }

    @Before("read()")
    public  void beforeRead(JoinPoint joinPoint) {
        JdbcTemplate readTemplate = DataSourceConfig.getJdbcTemplate();
        if(null == readTemplate) {
            readTemplate = new JdbcTemplate();
            DataSourceConfig.setJdbcTemplate(readTemplate);
        }
        DruidDataSource dataSource = (DruidDataSource) DataSourceConfig.nextSlave();
        readTemplate.setDataSource(dataSource);
    }

}
