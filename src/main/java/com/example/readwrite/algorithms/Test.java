package com.example.readwrite.algorithms;

import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws IOException {
//        Scanner sc = new Scanner(System.in);
//        int x = Integer.parseInt(sc.nextLine());
//        for (int i = 0; i < x; i++) {
//            String s = sc.nextLine();
//            boolean isfalse = true;
//            String[] temp = s.split("0");
//            Arrays.sort(temp, new Comparator<String>() {
//                @Override
//                public int compare(String o1, String o2) {
//                    return o2.length() - o1.length();
//                }
//            });
//            int niumei = 0;
//            int niuniu = 0;
//            for (int j = 0; j < temp.length; j++) {
//                if (isfalse) {
//                    niumei += temp[j].length();
//                    isfalse = false;
//                } else {
//                    niuniu += temp[j].length();
//                    isfalse = true;
//                }
//            }
//            if (niumei > niuniu) {
//                System.out.println("Niumei");
//                System.out.println(niumei - niuniu);
//            } else if (niumei < niuniu) {
//                System.out.println("Niuniu");
//                System.out.println(-niumei + niuniu);
//            } else {
//                System.out.println("Draw");
//                //System.out.println(-niumei+niuniu);
//            }
//        }

        String str = "110011001";
        String[] split = str.split("0");
        System.out.println(Arrays.asList(split));

    }
}
