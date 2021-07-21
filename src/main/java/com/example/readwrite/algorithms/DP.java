package com.example.readwrite.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topic 动态规划
 * 应该先用树来描述整个过程
 *
 * @author helltab
 * @version 1.0
 * @date 2021/7/5 11:53
 */
public class DP {
    public static void main(String[] args) {
        Map map = new HashMap<>();

        System.out.println(fibonacci(10));
        System.out.println(maxIncreaseSubSeq(new int[]{3, 1, 4, 1, 5, 9, 2, 6, 5}));
        System.out.println(maxStepSubSum_force(new int[]{6, -1, 3, -4, -6, 9, 2, -2, 5}));
        System.out.println(maxStepSubSum(new int[]{6, -1, 3, -4, -6, 9, 2, -2, 5}));
        System.out.println(maxNumberTowerPath(new int[][]{
                {3},
                {1, 5},
                {8, 4, 3},
                {2, 6, 7, 9},
                {6, 2, 3, 5, 1},}));
    }

    /**
     * 斐波那契数列
     */
    public static int fibonacci(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;
        int[] result = new int[n + 1];
        result[0] = 0;
        result[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }
        return result[n];
    }

    /**
     * 数组最大不连续递增子序列
     *
     * @param org
     * @return
     */
    public static int maxIncreaseSubSeq(int[] org) {
        int len = org.length;
        int[] lens = new int[len];// 记录长度
        Arrays.fill(lens, 1);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // org[i] > org[j] 只要大于前面的值, 当前长度就至少应该等于 j 位置的长度
                // lens[j]+1 > lens[i] 过滤分支情况: 如果当前长度 + 1 还不如目标的长度, 就不满足增长需求
                if (org[i] > org[j] && lens[j] + 1 > lens[i]) {
                    lens[i] = lens[j] + 1;
                }
            }
        }
        for (int i = 0; i < lens.length; i++) {
            System.out.print(lens[i] + "\t");
        }
        System.out.println();
        return Arrays.stream(lens).max().getAsInt();
    }

    /**
     * 数组最大连续子序列和
     * {6,-1,3,-4,-6,9,2,-2,5}
     *
     * @return
     */
    public static int maxStepSubSum_force(int[] org) {
        // 暴力
        int len = org.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int temp = org[i];
            list.add(temp);
            for (int j = i - 1; j >= 0; j--) {
                temp += org[j];
                list.add(temp);
            }
        }
        System.out.println(list);
        return list.stream().max((a, b) -> a - b).get();
    }

    public static int maxStepSubSum(int[] org) {
        int n = org.length;
        int max = org[0];
        int sum = org[0];
        for (int i = 1; i < n; i++) {
            // 如果 sum 为负数, 则抛弃之前的序列
            sum = Math.max(sum + org[i], org[i]);
            // 如果 sum 大于 max, 更新 max
            max = Math.max(sum, max);
        }
        return max;
    }

    /**
     * 3
     * 1    5
     * 8    4    3
     * 2    6    7    9
     * 6    2    3    5    1
     *
     * @return
     */
    public static int maxNumberTowerPath(int[][] tower) {
        int max = 0;
        int dp[][] = new int[tower.length][tower.length];
        dp[0][0] = tower[0][0];
        for(int i=1;i<tower.length;i++){
            for(int j=0;j<=i;j++){
                if(j==0){
                    //如果是第一列，直接跟他上面数字相加
                    dp[i][j] = dp[i-1][j] + tower[i][j];
                }else{
                    //如果不是第一列，比较他上面跟上面左面数字谁大，谁大就跟谁相加，放到这个位置
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + tower[i][j];
                }
                max = Math.max(dp[i][j], max);
            }
        }

        return max;
    }

    /**
     * 找零钱问题
     */
    public static void changes() {

    }

    /**
     * 0-1 背包问题
     */
    public static void bag_0_1() {

    }
}
