package com.example.readwrite.algorithms;

import lombok.Data;

import java.sql.ResultSet;

@Data
public class Result <T> {
    public static enum CodeEnum {
        SUCCESS(8000, "成功");
        int code;
        String desc;
        CodeEnum(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

    }
    private String msg;
    private int code;
    private T data;
    private Paged paged;
    @Data
    public static  class Paged{
        int total;
        int pageSize;
        int pageNum;
    }


}
