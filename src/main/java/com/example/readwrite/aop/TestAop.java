package com.example.readwrite.aop;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TestAop {
    /**
     * 测试 aop
     * 当 show 为 null 时, 会触发
     * @param show
     * @return
     * @throws IOException
     */
    public String writeAopTest(String show) throws IOException {
        System.out.println("write show");
        if (null == show) {
            throw new IOException();
        }
        testPrivate();
        return "show: " + show;
    }
    protected void testPrivate() {
        System.out.println("test private");
    }
    public void readAopTest(String show)  {
        System.out.println("read show");
    }
}
