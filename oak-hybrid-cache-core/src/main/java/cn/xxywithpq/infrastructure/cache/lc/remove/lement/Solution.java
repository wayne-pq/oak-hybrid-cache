package cn.xxywithpq.infrastructure.cache.lc.remove.lement;

import java.util.HashSet;

class Solution {
    public static void main(String[] args) {
        int[] num = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println(new Solution().removeElement(num, 2));
        char[] charArray = "sss".toCharArray();
        int i = charArray[0] - charArray[1];
        Character a = 'a';
        HashSet<Character> set = new HashSet<>();
    }

    public int removeElement(int[] nums, int val) {
        int count = 0;
        int lastIndex = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                nums[i--] = nums[lastIndex];
                nums[lastIndex--] = 0;
                count++;
            }
        }
        return nums.length - count;
    }
}