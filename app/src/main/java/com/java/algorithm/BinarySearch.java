package com.java.algorithm;

public class BinarySearch {

    //查找第一个值等于给定值的元素
    private static int search_first_equal(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == 0 || a[mid - 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    //查找最后一个值等于给定值的元素
    private static int search_last_equal(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < value) {
                low = mid + 1;
            } else if (a[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == n - 1 || a[mid + 1] != value) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    //查找第一个值大于等于给定值的元素
    private static int search_first_larger(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] >= value) {
                if (mid == 0 || a[mid - 1] < value) {
                    return mid;
                }
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    //查找最后一个值小于等于给定值的元素
    private static int search_last_lesser(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= value) {
                if (mid == n - 1 || a[mid + 1] > value) {
                    return mid;
                }
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = {1, 3, 4, 5, 6, 8, 8, 8, 11, 18};
        int index = search_last_lesser(a, a.length, 10);
        System.out.println("the search index = " + index);
    }
}
