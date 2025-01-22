package cn.xxywithpq.infrastructure.cache.lc.logestprefix;

class Solution {
    public String longestCommonPrefix(String[] strs) {
        String string = strs[0];
        char[] cstr = string.toCharArray();
        int length = cstr.length;
        while (length > 0) {
            boolean judge = doLongestCommonPrefix(strs, cstr, length);
            if (!judge) {
                length--;
            } else {
                return new String(cstr, 0, length);
            }
        }
        return "";
    }

    public boolean doLongestCommonPrefix(String[] strs, char[] cstr, int length) {
        for (String s : strs) {
            char[] str = s.toCharArray();
            if (!compare(str, cstr, length)) {
                return false;
            }
        }
        return true;
    }

    boolean compare(char[] str, char[] cstr, int length) {
        int i = 0;
        while (i < length) {
            if (str.length >= length && str[i] != cstr[i]) {
                return false;
            }
            i++;
        }
        return true;
    }

}