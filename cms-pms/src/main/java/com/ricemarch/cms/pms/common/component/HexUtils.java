package com.ricemarch.cms.pms.common.component;

import java.math.BigInteger;

/**
 * @author RiceMarch
 * @date 2021/5/21 15:32
 */
public class HexUtils {

    static String hexToBin(String s) {
        s = s.replace(" ", "");
        return new BigInteger(s, 16).toString(2);
    }

    static String binToHex(String s) {
        s = s.replace(" ", "");
        return Long.toHexString(Long.parseUnsignedLong(s, 2));
    }

    public static void main(String[] args) {
        String s = "1111 0000 0000 0000 0000 0000 0000 1111";
        s = s.replace(" ", "");
        String hex = binToHex(s);
        String bin = hexToBin(hex);
        System.out.println(hex);
        System.out.println(bin);
    }
}
