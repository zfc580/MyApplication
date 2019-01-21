package com.java.algorithm;

import java.util.Arrays;

public class ArraySort {


    // 冒泡排序，a 表示数组，n 表示数组大小
    public static void bubbleSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            boolean isSort = false;
            System.out.println("loop i = " + i);
            for (int j = 0; j < n - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = temp;
                    isSort = true;
                }
            }
            if (!isSort) {
                break;
            }
        }
    }

    // 插入排序
    private static void insertionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 1; i < n; i++) {
            int value = a[i]; //取出数组未排序一边的第一个元素
            int j = i - 1;
            for (; j >= 0; --j) {
                /*已排序一边的最后一个元素和value作比较，如果大于则移动，不大于中断此次循环
                 (因为已排序的最后一个数已经是最大了，不用再往前比较)*/
                if (a[j] > value) {
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = value;
        }
    }

    // 选择排序
    private static void selectionSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        for (int i = 0; i < n; i++) {
            int min = a[i];
            int index = i;
            for (int j = i; j < n; j++) {
                if (a[j] < min) {
                    min = a[j];
                    index = j;
                }
            }
            a[index] = a[i];
            a[i] = min;
        }

    }

    //归并排序
    private static void mergeSort(int[] a, int n) {
        int[] temp = new int[a.length];
        merge_sort_c(a, 0, n - 1, temp);
    }

    private static void merge(int[] a, int start, int mid, int end, int[] temp) {
        int i = start;
        int j = mid + 1;
        int k = 0; //temp当作临时数组，如果数组分割到两个成员时，temp就只用到两个元素
        while (i <= mid && j <= end) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[i++];
        }
        while (j <= end) {
            temp[k++] = a[j++];
        }
        k = 0;
        while (start <= end) {
            a[start++] = temp[k++];
        }
    }

    private static void merge_sort_c(int[] a, int start, int end, int[] array) {
        if (start >= end) {
            return;
        }
        int mid = (end + start) / 2;
        merge_sort_c(a, start, mid, array);
        merge_sort_c(a, mid + 1, end, array);
        merge(a, start, mid, end, array);
    }

    //快速排序
    private static void quick_sort(int[] a, int n) {
        quick_sort_c(a, 0, n - 1);
    }

    private static void quick_sort_c(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int p = partition(a, start, end);
        quick_sort_c(a, start, p - 1);
        quick_sort_c(a, p + 1, end);
    }

    private static int partition(int[] a, int start, int end) {
        int pivot = a[end];
        int i = start;
        for (int j = i; j<end; j++) {
            if (a[j] < pivot) {
                int temp = a[j];
                a[j] = a[i];
                a[i] = temp;
                i++;
            }
        }
        a[end] = a[i];
        a[i] = pivot;
        return i;
    }


    public static void main(String[] args) {
        int a[] = {71, 44, 52, 13, 24, 15, 6};
        //bubbleSort(a, a.length);
        //insertionSort(a, a.length);
        //selectionSort(a, a.length);
        //mergeSort(a, a.length);
        quick_sort(a, a.length);
        System.out.println(Arrays.toString(a));
    }
}
