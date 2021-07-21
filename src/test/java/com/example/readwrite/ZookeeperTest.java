package com.example.readwrite;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.function.Consumer;

@SpringBootTest
public class ZookeeperTest {
    private final static String SERVERS = "192.168.56.102:21811,192.168.56.102:21812,192.168.56.102:21813";

    @Test
    public void clientTest() {
        ZkClient client = new ZkClient(SERVERS);
        List<String> nodes = client.getChildren("/");
        nodes.forEach(node -> {
            System.out.println(node);
        });

        client.subscribeChildChanges("/hello", (a, b) -> {
            System.out.println(a);
            System.out.println(b);
        });

        client.subscribeDataChanges("/hello", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("data had been changed: " + s);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("data had been deleted: " + s);
            }
        });
        while (true) {
        }

    }

    public static void main(String[] args) {
        testA(ZookeeperTest::testB);
    }

    public static void testB (String flag) {
        System.out.println("I get " + flag);
    }
    public static void testA(Consumer<String> doSomething) {
        doSomething.accept("A");
    }
}

