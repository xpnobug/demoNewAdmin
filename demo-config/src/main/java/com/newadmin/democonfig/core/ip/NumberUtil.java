package com.newadmin.democonfig.core.ip;

import org.springframework.lang.Nullable;

import java.nio.charset.StandardCharsets;

/**
 * 数字类型工具类
 *
 * @author L.cm
 */
public class NumberUtil extends org.springframework.util.NumberUtils {

    /**
     * All possible chars for representing a number as a String
     */
    static final byte[] DIGITS = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
        'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D',
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z', '_', '-'
    };

    /**
     * 将 long 转短字符串 为 62 进制
     *
     * @param i 数字
     * @return 短字符串
     */
    public static String to62Str(long i) {
        int radix = 62;
        byte[] buf = new byte[65];
        int charPos = 64;
        i = -i;
        while (i <= -radix) {
            buf[charPos--] = DIGITS[(int) (-(i % radix))];
            i = i / radix;
        }
        buf[charPos] = DIGITS[(int) (-i)];
        return new String(buf, charPos, (65 - charPos), StandardCharsets.UTF_8);
    }

    /**
     * 将 62 进制字符串转为数字
     *
     * @param s 字符串
     * @return 数字
     */
    public static long form62Str(String s) {
        char[] chars = s.toCharArray();
        char c;
        int idx;
        long res = 0;
        int len = chars.length;
        int lenIdx = len - 1;
        for (int i = 0; i < len; i++) {
            c = chars[i];
            // 将字符转换为对应的数字
            if (c >= 'A' && c <= 'Z') {
                idx = c - 29;
            } else if (c >= 'a' && c <= 'z') {
                idx = c - 87;
            } else {
                idx = c - 48;
            }
            res += (long) (idx * StrictMath.pow(62, lenIdx - i));
        }
        return res;
    }

}
