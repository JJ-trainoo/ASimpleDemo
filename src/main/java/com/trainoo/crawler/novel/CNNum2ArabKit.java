package com.trainoo.crawler.novel;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhoutao on 2018/6/29 10:29
 * 工具类：大写中文数字转换成阿拉伯数字
 */
public class CNNum2ArabKit {

    private static final Character[] CN_NUMERIC = {
            '一', '二', '三', '四', '五', '六', '七', '八', '九',
            '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖',
            '十', '百', '千', '拾', '佰', '仟', '万', '亿', '○', 'Ｏ', '零'};

    private static Map<Character, Integer> cnNumeric = null;

    static {
        cnNumeric = new HashMap<>(40, 0.85f);
        for (int j = 0; j < 9; j++)
            cnNumeric.put(CN_NUMERIC[j], j + 1);
        for (int j = 9; j < 18; j++)
            cnNumeric.put(CN_NUMERIC[j], j - 8);
        cnNumeric.put('○', 0);
        cnNumeric.put('Ｏ', 0);
        cnNumeric.put('零', 0);
        cnNumeric.put('两', 2);
        cnNumeric.put('十', 10);
        cnNumeric.put('拾', 10);
        cnNumeric.put('百', 100);
        cnNumeric.put('佰', 100);
        cnNumeric.put('千', 1000);
        cnNumeric.put('仟', 1000);
        cnNumeric.put('万', 10000);
        cnNumeric.put('亿', 100000000);
    }

    /**
     * 判断给定字符是否是数字
     *
     * @return 是：对应的阿拉伯数字 否：-1
     */
    public static int isCNNumeric(char c) {
        Integer i = cnNumeric.get(c);
        if (i == null){
            return -1;
        }
        return i;
    }


    /**
     * 中文数字转阿拉伯数字
     *
     * @param cnn
     * @param flag
     * @return int
     */
    public static int cnNumericToArabic(String cnn, boolean flag) {

        cnn = cnn.trim();
        if (cnn.length() == 1) {
            return isCNNumeric(cnn.charAt(0));
        }
        if (flag){
            cnn = cnn.replace('佰', '百').replace('仟', '千').replace('拾', '十').replace('零', ' ');
        }
        int yi, wan, qian, bai, shi;
        int val = 0;
        yi = cnn.lastIndexOf('亿');
        if (yi > -1) {
            val += cnNumericToArabic(cnn.substring(0, yi), false) * 100000000;
            if (yi < cnn.length() - 1) {
                cnn = cnn.substring(yi + 1, cnn.length());
            }
            else {
                cnn = "";
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10){
                    val += arbic * 10000000;
                }
                cnn = "";
            }
        }

        wan = cnn.lastIndexOf('万');
        if (wan > -1) {
            val += cnNumericToArabic(cnn.substring(0, wan), false) * 10000;
            if (wan < cnn.length() - 1) {
                cnn = cnn.substring(wan + 1, cnn.length());
            } else {
                cnn = "";
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10) {
                    val += arbic * 1000;
                }
                cnn = "";
            }
        }

        qian = cnn.lastIndexOf('千');
        if (qian > -1) {
            val += cnNumericToArabic(cnn.substring(0, qian), false) * 1000;
            if (qian < cnn.length() - 1) {
                cnn = cnn.substring(qian + 1, cnn.length());
            } else {
                cnn = "";
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10){
                    val += arbic * 100;
                }
                cnn = "";
            }
        }

        bai = cnn.lastIndexOf('百');
        if (bai > -1) {
            val += cnNumericToArabic(cnn.substring(0, bai), false) * 100;
            if (bai < cnn.length() - 1) {
                cnn = cnn.substring(bai + 1, cnn.length());
            } else {
                return val;
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10){
                    val += arbic * 10;
                }
                cnn = "";
            }
        }

        shi = cnn.lastIndexOf('十');
        if (shi > -1) {
            if (shi == 0) {
                val += 1 * 10;
            } else {
                val += cnNumericToArabic(cnn.substring(0, shi), false) * 10;
            }
            if (shi < cnn.length() - 1) {
                cnn = cnn.substring(shi + 1, cnn.length());
            } else {
                cnn = "";
            }
        }

        cnn = cnn.trim();
        for (int j = 0; j < cnn.length(); j++) {
            val += isCNNumeric(cnn.charAt(j)) * Math.pow(10, cnn.length() - j - 1);
        }
        return val;
    }

    public static BigDecimal cnBigNumericToArabic(String cnn, boolean flag) {
        cnn = cnn.trim();
        if (cnn.length() == 1) {
            return new BigDecimal(isCNNumeric(cnn.charAt(0)));
        }
        if (flag){
            cnn = cnn.replace('佰', '百').replace('仟', '千').replace('拾', '十').replace('零', ' ');
        }
        int yi, wan, qian, bai, shi;
        BigDecimal val = new BigDecimal(0);
        yi = cnn.lastIndexOf('亿');
        if (yi > -1) {
            val = val.add(cnBigNumericToArabic(cnn.substring(0, yi), false).multiply(new BigDecimal(100000000)));
            if (yi < cnn.length() - 1) {
                cnn = cnn.substring(yi + 1, cnn.length());
            }
            else {
                cnn = "";
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10){
                    val = val.add(new BigDecimal(arbic).multiply(new BigDecimal(10000000)));
                }
                cnn = "";
            }
        }

        wan = cnn.lastIndexOf('万');
        if (wan > -1) {
            val = val.add(cnBigNumericToArabic(cnn.substring(0, wan), false).multiply(new BigDecimal(10000)));
            if (wan < cnn.length() - 1) {
                cnn = cnn.substring(wan + 1, cnn.length());
            } else {
                cnn = "";
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10) {
                    val = val.add(new BigDecimal(arbic).multiply(new BigDecimal(1000)));
                }
                cnn = "";
            }
        }

        qian = cnn.lastIndexOf('千');
        if (qian > -1) {
            val = val.add(cnBigNumericToArabic(cnn.substring(0, qian), false).multiply(new BigDecimal(1000)));
            if (qian < cnn.length() - 1) {
                cnn = cnn.substring(qian + 1, cnn.length());
            } else {
                cnn = "";
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10){
                    val = val.add(new BigDecimal(arbic).multiply(new BigDecimal(100)));
                }
                cnn = "";
            }
        }

        bai = cnn.lastIndexOf('百');
        if (bai > -1) {
            val = val.add(cnBigNumericToArabic(cnn.substring(0, bai), false).multiply(new BigDecimal(100)));
            if (bai < cnn.length() - 1) {
                cnn = cnn.substring(bai + 1, cnn.length());
            } else {
                return val;
            }
            if (cnn.length() == 1) {
                int arbic = isCNNumeric(cnn.charAt(0));
                if (arbic <= 10){
                    val = val.add(new BigDecimal(arbic).multiply(new BigDecimal(10)));
                }
                cnn = "";
            }
        }

        shi = cnn.lastIndexOf('十');
        if (shi > -1) {
            if (shi == 0) {
                val = val.add(new BigDecimal(10));
            } else {
                val = val.add(cnBigNumericToArabic(cnn.substring(0, shi), false).multiply(new BigDecimal(10)));
            }
            if (shi < cnn.length() - 1) {
                cnn = cnn.substring(shi + 1, cnn.length());
            } else {
                cnn = "";
            }
        }

        cnn = cnn.trim();
        for (int j = 0; j < cnn.length(); j++) {
            val = val.add(new BigDecimal(isCNNumeric(cnn.charAt(j))).multiply(new BigDecimal(Math.pow(10, cnn.length() - j - 1))));
        }
        return val;
    }

    public static void main(String[] args) {
        System.out.println(CNNum2ArabKit.isCNNumeric('零'));
        System.out.println(CNNum2ArabKit.cnNumericToArabic("一九九八", true));
        System.out.println(CNNum2ArabKit.cnNumericToArabic("一千万亿零壹拾壹", true));
        System.out.println(CNNum2ArabKit.cnBigNumericToArabic("一千万亿零壹拾壹", true));
    }
}