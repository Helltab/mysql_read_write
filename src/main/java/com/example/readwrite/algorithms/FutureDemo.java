package com.example.readwrite.algorithms;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureDemo {
    public static void main(String[] args) {
        List<FutureTask<Integer>> list = new ArrayList<>();
        int len = 4;
        for (int i = 0; i < len; i++) {
            int finalI = i;
            list.add(new FutureTask<>(() -> {
                return finalI;
            }));
        }

        for (int i = 0; i < len; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("异步操作先做: " + finalI);
                TT t = new TT(finalI);
                if (finalI == 0) {
                    System.out.println("这是第一个操作: 0");
                    System.out.println("\t " + t);
                }else {
                    try {
                        System.out.println("同步操作这里之后做: " + (list.get(finalI - 1).get() + 1) );
                        System.out.println("\t " + t);

                    } catch (Exception ignore) {
                    }
                }
                try {
                    list.get(finalI).run();
                } catch (Exception ignore) {
                    ignore.printStackTrace();
                }
            }).start();
        }

    }

    /**
     *
     */
    @Data
    static
    class TT {
        String name;
        public TT(int index) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.name = "我是 index: " + index + " 的父类";
        }
    }
}
