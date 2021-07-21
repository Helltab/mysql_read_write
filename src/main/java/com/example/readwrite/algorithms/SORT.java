package com.example.readwrite.algorithms;

import javafx.scene.control.TreeView;
import lombok.Data;

import java.util.Arrays;
import java.util.Base64;
import java.util.Timer;
import java.util.TooManyListenersException;
import java.util.TreeMap;

/**
 * 排序
 */
public class SORT {
    public static void main(String[] args) {
//        int[] ints = quickSort(new int[]{1, 3, 2, 5, 4, 68, 86, 7, 0});
//        for (int anInt : ints) {
//            System.out.print(anInt + "\t");
//        }
        int[] res = mergeSort(genInts());
        print(res);
//        res = quickSort(genInts());
//        print(res);
//        res = quickSort2(genInts());
//        print(res);
//        res = quickSort3(genInts());
//        print(res);
//        res = quickSort4(genInts());
//        print(res);
    }

    private static int[] genInts() {
        return new int[]{1, 3, 2, 5, 4, 68, 86, 7, 0};
    }

    public static void print(int[] res) {
        System.out.println();
        for (int anInt : res) {
            System.out.print(anInt + "\t");
        }
        System.out.println();
    }

    public static int[] quickSort(int[] org) {
        return quickSort(org, 0, org.length - 1);
    }

    public static int[] quickSort(int[] org, int left, int right) {
        if (left < right) {
            int middle = part(org, left, right);
            quickSort(org, left, middle - 1);
            quickSort(org, middle + 1, right);
        }
        return org;
    }

    private static int part(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                right--;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    public static int[] mergeSort(int[] arr) {
        int len = arr.length;
        mergeSort(arr, 0, len - 1, new int[len]);
        return arr;
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] > arr[j]) {
                temp[t++] = arr[j++];
            } else {
                temp[t++] = arr[i++];
            }
        }
        while (mid >= i) {
            temp[t++] = arr[i++];
        }
        while (right >= j) {
            temp[t++] = arr[j++];
        }
//        for (int k = 0; k < temp.length; k++) {
//            arr[k] = temp[k];
//        }
        t = 0;
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }


    public static int[] quickSort2(int[] org, int left, int right) {
        if (left < right) {
            int middle = part2(org, left, right);
            quickSort2(org, left, middle - 1);
            quickSort2(org, middle + 1, right);
        }
        return org;
    }

    private static int part2(int[] org, int left, int right) {
        int pivot = org[left];
        while (left < right) {
            while (left < right && org[right] >= pivot) {
                right--;
            }
            org[left] = org[right];
            while (left < right && org[left] <= pivot) {
                left++;
            }
            org[right] = org[left];
        }
        org[left] = pivot;
        return left;
    }

    public static int[] quickSort2(int[] org) {
        return quickSort2(org, 0, org.length - 1);
    }


    public static void quickSort3(int[] org, int left, int right) {
        if (left < right) {
            int middle = part3(org, left, right);
            quickSort3(org, left, middle - 1);
            quickSort3(org, middle + 1, right);
        }

    }

    public static int[] quickSort3(int[] org) {
        quickSort3(org, 0, org.length - 1);
        return org;
    }

    private static int part3(int[] org, int left, int right) {
        int pivot = org[left];
        while (left < right) {
            while (left < right && org[right] >= pivot) {
                right--;
            }
            org[left] = org[right];
            while (left < right && org[left] <= pivot) {
                left++;
            }
            org[right] = org[left];
        }
        org[left] = pivot;
        return left;
    }

    public static int[] quickSort4(int[] org) {
        quickSort4(org, 0 ,org.length-1);
        return org;
    }

    private static void quickSort4(int[] org, int left, int right) {
        if (left < right) {
            int middle;
            {
                int l = left;
                int r = right;
                int pivot = org[l];
                while (l<r) {
                    while (l<r && org[r]>=pivot) {
                        r--;
                    }
                    org[l] = org[r];
                    while (l<r && org[l]<=pivot) {
                        l++;
                    }
                    org[r] = org[l];
                }
                org[l] = pivot;
                middle = l;
            }
            quickSort4(org, left, middle - 1);
            quickSort4(org, middle + 1, right);

        }
    }

}

