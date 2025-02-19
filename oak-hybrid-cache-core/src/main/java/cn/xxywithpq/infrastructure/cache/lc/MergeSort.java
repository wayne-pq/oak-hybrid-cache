package cn.xxywithpq.infrastructure.cache.lc;

import java.util.Arrays;

public class MergeSort {
    // 主函数，用于启动归并排序
    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }
        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        mergeSort(left); // 递归排序左半部分
        mergeSort(right); // 递归排序右半部分
        // 排序完后，左右两边都是有序的
        merge(array, left, right); // 合并左右两部分
    }

    // 合并两个已排序的子数组
    private static void merge(int[] result, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        // 1. 比较两个数组的大小，将小的放入合并数组肿
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }
        // 2. 比较完后，将其中一边剩余的数组全部添加到合并数组中
        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    // 测试方法
    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("原始数组: " + Arrays.toString(array));
        mergeSort(array);
        System.out.println("排序后的数组: " + Arrays.toString(array));
    }
}
