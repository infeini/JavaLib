package ljs.lib;

import ljs.SingletonHolder;

import java.util.Map;
import java.util.Random;

public class StringUtils {
    /**
     * 判断字符串是否非空
     *
     * @param str 需要判断的字符串
     */
    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    /**
     * 替换字符串
     *
     * @param regex        匹配替换字符串或正则表达式
     * @param replacement  替换目标
     * @param stringBuffer 字符串缓存
     */
    public static void replaceAll(String regex, String replacement, StringBuffer stringBuffer) {
        if (regex == null || replacement == null) ;
        else {
            String str = stringBuffer.toString();
            try {
                str = str.replaceAll(regex, replacement == null ? "" : replacement);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stringBuffer.setLength(0);
            stringBuffer.append(str);
        }
    }

    /**
     * 批量替换字符串
     *
     * @param map          替换目标、替换内容的map集合
     * @param stringBuffer 需要替换的字符串
     */
    public static void replaceAll(Map map, StringBuffer stringBuffer) {
        if (map == null) ;
        else {
            for (Object key : map.keySet()) {
                Object value = map.get(key);
                replaceAll(key == null ? "" : key.toString(), value == null ? "" : value.toString(), stringBuffer);
            }
        }
    }

    private static Random random = new Random();

    /**
     * 随机生成指定位数的随机字符串
     *
     * @param count 字符串数量
     * @return 随机字符串
     */
    public static String getRandString(int count) {
        String str = "";
        for (int i = 1; i <= count; i++)
            str += (getRandChar() + "");
        return str;
    }

    /**
     * 获取随机字符
     *
     * @return 随机a-z、A-Z、0-9的字符
     */
    public static char getRandChar() {
        int c = random.nextInt(62);
        if (c <= 9)// 0-9>>[0-9]
            c += '0';
        else if (c < 36)// a-z>>[10,35]
            c += ('a' - 10);
        else if (c < 62)// A-Z>>[36,61]
            c += ('A' - 36);
        return (char) c;
    }

    /**
     * 校验字符串是否市数字格式
     *
     * @param checkStr 需要校验的字符串
     * @return 数字:true,非数字:false
     */
    public static boolean isNumber(String checkStr) {
        try {
            Integer.parseInt(checkStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 校验是否存在空字符
     *
     * @param strs
     * @return boolean
     */
    public static boolean haveEmpty(String... strs) {
        for (String str : strs)
            if (isEmpty(str))
                return true;
        return false;
    }

    /**
     * 获取单个随机小写字母
     *
     * @return 单个小写字母
     */
    public static char getRandLowerCaseLetter() {
        return (char) (SingletonHolder.Random.INSTANCE.nextInt('z' - 'a') + 'a');
    }

    /**
     * 获取单个随机大写字母
     *
     * @return 单个大写字母
     */
    public static char getRandUpperCaseLetter() {
        return (char) (SingletonHolder.Random.INSTANCE.nextInt('Z' - 'A') + 'A');
    }

    /**
     * 获取指定个数的随机小写字母
     *
     * @return 指定位数的小写字符串
     */
    public static String getRandLowerCaseString(int size) {
        char[] chars = new char[size];
        for (int i = 0; i < chars.length; i++) chars[i] = getRandLowerCaseLetter();
        return new String(chars);
    }

    /**
     * 获取指定个数的随机大写字符串
     *
     * @return 指定位数的大写字符串
     */
    public static String getRandUpperCaseString(int size) {
        char[] chars = new char[size];
        for (int i = 0; i < chars.length; i++) chars[i] = getRandUpperCaseLetter();
        return new String(chars);
    }

    public static boolean isLowerCase(char c) {
        return c <= 'z' && c >= 'a';
    }

    public static boolean isUpperCase(char c) {
        return c <= 'Z' && c >= 'A';
    }
}
