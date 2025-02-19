package cn.xxywithpq.infrastructure.cache.lc;

public class DecimalToBase62 {

    // 定义 62 进制的字符集
    private static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 将 10 进制数转换为 62 进制字符串
     *
     * @param num 10 进制数
     * @return 62 进制字符串
     */
    public static String decimalToBase62(long num) {
        if (num == 0) {
            return String.valueOf(BASE62_CHARS.charAt(0));
        }

        StringBuilder result = new StringBuilder();
        while (num > 0) {
            int remainder = (int) (num % 62); // 取余
            result.insert(0, BASE62_CHARS.charAt(remainder)); // 将余数对应的字符插入到结果中
            num = num / 62; // 更新 num
        }
        return result.toString();
    }

    /**
     * 将 62 进制字符串转换为 10 进制数
     *
     * @param base62Str 62 进制字符串
     * @return 10 进制数
     */
    public static long base62ToDecimal(String base62Str) {
        long result = 0;
        for (int i = 0; i < base62Str.length(); i++) {
            char ch = base62Str.charAt(i);
            int value = BASE62_CHARS.indexOf(ch); // 获取字符对应的值
            result = result * 62 + value; // 累加计算
        }
        return result;
    }

    public static void main(String[] args) {
        // 测试 10 进制转 62 进制
        System.out.println(decimalToBase62(1646777064113700865L));
        System.out.println(decimalToBase62(1646777064113700866L));
        System.out.println(decimalToBase62(1646777064113700868L));
        System.out.println(decimalToBase62(1646777064113700883L));

    }
}