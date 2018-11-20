package ljs.autocode;

import java.util.regex.Pattern;

/**
 * 扩展String对象
 */
public class StringWrap {
    private String target;

    public StringWrap(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return target;
    }

    /**
     * 获取符合 字段 命名格式的字符串
     */
    public String field() {
        return formatToField(target);
    }

    /**
     * 获取符合 类名 命名格式的字符串
     */
    public String type() {
        return formatToClass(target);
    }

    /**
     * 获取符合 数据表 命名格式的字符串
     */
    public String table() {
        return target;
    }

    public String sql() {
        return "`" + target + "`";
    }

    public String getMethod() {
        return "get" + formatToClass(target);
    }

    public String setMethod() {
        return "set" + formatToClass(target);
    }

    /**
     * 将数据库表字段命名格式转换为java字段命名格式
     */
    public String formatToField(String str) {
        while (Pattern.compile("_" ).matcher(str).find()) {
            int index = str.indexOf("_" );
            String str1 = str.substring(0, index);
            String str2 = str.substring(index + 1, index + 2).toUpperCase();
            String str3 = str.substring(index + 2);
            str = str1 + str2 + str3;
        }
        return str;
    }

    /**
     * 将数据库表命名格式转换为java类命名格式
     */

    public String formatToClass(String str) {
        str = formatToField(str);
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static Boolean checkFormat(String str) {
        //_不能开头
        Boolean b1 = !str.substring(0, 1).equals("_" );
        //_不能结尾
        Boolean b2 = !str.substring(str.length() - 1).equals("_" );
        //只包含a-z_
        Boolean b3 = Pattern.compile("[a-z_]+" ).matcher(str).matches();
        //_不能连续
        Boolean b4 = !Pattern.compile("[_]{2,}" ).matcher(str).find();
        return b1 && b2 && b3 && b4;
    }
}
