package com.example.readwrite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class ReadWriteApplication {
    public static void main(String[] args) {
        assert assertCheck();
        SpringApplication.run(ReadWriteApplication.class, args);
    }

    /**
     * -ea
     * -enableassertions
     * @return
     */
    private static boolean assertCheck() {
        System.out.println("check");
        return false;
    }
}

