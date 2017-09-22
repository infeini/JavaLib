package auto.freemarker;

import java.util.regex.Pattern;

public class MyString
{
    private String str;

    public MyString(String str)
    {
        this.str = str;
    }

    @Override
    public String toString()
    {
        throw new RuntimeException("");
    }

    public String field()
    {
        return formatToField(str);
    }

    public String type()
    {
        return formatToClass(str);
    }

    public String table()
    {
        return this.str;
    }

    public String sql()
    {
        return "`" + this.str + "`";
    }

    public String getMethod()
    {
        return "get" + formatToClass(str);
    }

    public String setMethod()
    {
        return "set" + formatToClass(str);
    }

    /**
     * 将数据库表字段命名格式转换为java字段命名格式
     */
    public String formatToField(String str)
    {
        while (Pattern.compile("_").matcher(str).find())
        {
            int index = str.indexOf("_");
            String str1 = str.substring(0, index);
            String str2 = str.substring(index + 1, index + 2).toUpperCase();
            String str3 = str.substring(index + 2, str.length());
            str = str1 + str2 + str3;
        }
        return str;
    }

    /**
     * 将数据库表命名格式转换为java类命名格式
     */

    public String formatToClass(String str)
    {
        str = formatToField(str);
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
    }

    public static Boolean checkFormat(String str)
    {
        //_不能开头
        Boolean b1 = !str.substring(0, 1).equals("_");
        //_不能结尾
        Boolean b2 = !str.substring(str.length() - 1, str.length()).equals("_");
        //只包含a-z_
        Boolean b3 = Pattern.compile("[a-z_]+").matcher(str).matches();
        //_不能连续
        Boolean b4 = !Pattern.compile("[_]{2,}").matcher(str).find();
        return b1 && b2 && b3 && b4;
    }
}
