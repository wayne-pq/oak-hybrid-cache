package cn.xxywithpq.infrastructure.cache.lc.findKthLargest;

import java.util.Arrays;

class Solution {
    public static int findKthLargest(int[] nums, int k) {
        doFindKthLargest(nums, 0, nums.length, k);
        return nums[k - 1];
    }

    public static void doFindKthLargest(int[] nums, int left, int right, int k) {
        if (nums.length < 2) {
            return;
        }
        int size = nums.length;
        int mid = left + ((right - left) >> 1);
        int[] leftArrs = Arrays.copyOfRange(nums, 0, mid);
        int[] rightArrs = Arrays.copyOfRange(nums, mid, size);

        doFindKthLargest(leftArrs, 0, leftArrs.length, k);
        doFindKthLargest(rightArrs, 0, rightArrs.length, k);
        merge(nums, leftArrs, rightArrs, k);
    }

    public static void merge(int[] nums, int[] left, int[] right, int k) {
        int l = 0, r = 0, n = 0;
        while (l < left.length && r < right.length) {
            if (left[l] < right[r]) {
                nums[n++] = left[l++];

            } else {
                nums[n++] = right[r++];
            }
            if (--k == 0) {
                return;
            }
        }
        while (l < left.length) {
            nums[n++] = left[l++];
            if (--k == 0) {
                return;
            }
        }
        while (r < right.length) {
            nums[n++] = right[r++];
            if (--k == 0) {
                return;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int result = findKthLargest(nums, k);
        System.out.println("数组中第 " + k + " 大的元素是：" + result);
    }
}