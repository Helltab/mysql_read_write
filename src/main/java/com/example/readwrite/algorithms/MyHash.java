package com.example.readwrite.algorithms;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.druid.sql.visitor.functions.Right;
import com.alibaba.druid.support.json.JSONUtils;
import javafx.concurrent.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Topic 简单 hash
 *
 * @author helltab
 * @version 1.0
 * @date 2021/7/15 11:55
 */
public class MyHash {

    public static void main(String[] args) {
        MyHash myHash = new MyHash();
        myHash.put("A", 12);
        myHash.put("B", 23);
        System.out.println(myHash);
        System.out.println(myHash.get("A"));
        System.out.println(myHash.get("B"));
        System.out.println(myHash.get("C"));
    }


    MyNode[] tabs;
    MyNode first;
    int capacity = 1 << 4;

    public MyHash() {
        resetTabs();
    }

    @Override
    public String toString() {
        return Arrays.stream(this.tabs)
                       .filter(Objects::nonNull)
//                       .map(node->node.toString())
                       .collect(Collectors.toList()).toString();
    }

    public MyHash(int capacity) {
        this.capacity = capacity;
        resetTabs();
    }

    public void resetTabs() {
        tabs = new MyNode[capacity];
    }

    public static int hash(Object x) {
        return 1;
//        return System.identityHashCode(x);
    }

    public void put(Object key, Object value) {
        int h = hash(key);
        int index = h & (capacity - 1);
        MyNode node = tabs[index];
        MyNode newNode = new MyNode(key, value, null, null);
        if (null == node) {
            tabs[index] = newNode;
        } else {
            if (null == node.find(key)) {
                newNode.next = node;
                node.prev = newNode;
                tabs[index] = first = newNode;
            }
        }
    }

    public Object get(Object key) {
        MyNode node = getFirst(key);
        if (null == node) return null;
        return node.find(key);
    }

    private MyNode getFirst(Object key) {
        int h = hash(key);
        int index = h & (capacity - 1);
        return tabs[index];
    }


    @Data
    @NoArgsConstructor
    public static class MyNode {
        int hash;
        Object key;
        Object value;
        MyNode prev;
        MyNode next;

        @Data
        @AllArgsConstructor
        private class Stringify {
            String s_key;
            String s_val;
        }

        @Override
        public String toString() {
            List<Stringify> list = new ArrayList<>();
            MyNode first = this;
            while (null != first) {
                HashMap<Object, Object> map = new HashMap<>();
                map.put(key, value);
                list.add(new Stringify(first.getKey().toString(), first.getValue().toString()));
                first = first.next;
            }
            return JSONUtil.toJsonPrettyStr(list);
        }

        public Object find(Object key) {
            MyNode first = this;
            while (null != first) {
                if (key == first.key || key.equals(first.key)) {
                    return first.value;
                }
                first = first.next;
            }
            return null;
        }

        public MyNode(Object key, Object value, MyNode prev, MyNode next) {
            this.key = key;
            this.value = value;
            this.prev = prev;
            this.next = next;
            this.hash = hash(key);
        }
    }

}
