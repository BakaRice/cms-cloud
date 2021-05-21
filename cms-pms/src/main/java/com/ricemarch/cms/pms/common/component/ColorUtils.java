package com.ricemarch.cms.pms.common.component;

/**
 * @author RiceMarch
 * @date 2021/5/20 15:22
 */
public class ColorUtils {
    public static String rc(int r, int g, int b, int r1, int g1, int b1) {

        int rr = r + (int) (Math.random() * (r1 - r + 1));
        int rg = g + (int) (Math.random() * (g1 - g + 1));
        int rb = b + (int) (Math.random() * (b1 - b + 1));
        String re = "";
        String sr = Integer.toHexString(rr), sg = Integer.toHexString(rg), sb = Integer.toHexString(rb);
        if (Integer.toHexString(rr).length() == 1) {
            sr = "0" + Integer.toHexString(rr);
        }
        if (Integer.toHexString(rg).length() == 1) {
            sg = "0" + Integer.toHexString(rg);
        }
        if (Integer.toHexString(rb).length() == 1) {
            sb = "0" + Integer.toHexString(rb);
        }
        if (Integer.toHexString(rr).length() == 0) {
            sr = "00";
        }
        if (Integer.toHexString(rg).length() == 0) {
            sg = "00";
        }
        if (Integer.toHexString(rb).length() == 0) {
            sb = "00";
        }
        System.out.println(re = "#" + sr + sg + sb);
        return re;
    }
}
