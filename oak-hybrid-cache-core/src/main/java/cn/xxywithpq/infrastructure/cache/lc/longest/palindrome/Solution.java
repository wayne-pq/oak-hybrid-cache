package cn.xxywithpq.infrastructure.cache.lc.longest.palindrome;

class Solution {
    public static String longestPalindrome(String s) {
        int size = s.length();
        boolean[][] bp = new boolean[size][size];

        int maxLen = 1;
        int beginIndex = 0;

        //长度为1全是回文串
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bp[i][j] = true;
            }
        }

        for (int len = 2; len <= size; len++) {
            for (int i = 0; i < size; i++) {
                int j = i + len - 1;
                if (j + 1 > size) {
                    break;
                }

                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        bp[i][j] = true;
                        maxLen = j - i + 1;
                        beginIndex = i;
                    } else {
                        bp[i][j] = bp[i + 1][j - 1];
                    }
                } else {
                    bp[i][j] = false;
                }

                if (bp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    beginIndex = i;
                }
            }
        }
        return s.substring(beginIndex, beginIndex + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
    }
}