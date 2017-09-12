package auto

import java.util.regex.Pattern

object InfoTool
{
    /**将数据库表字段命名格式转换为java字段命名格式*/
    fun formatToField(str: String): String
    {
        var str = str
        while (Pattern.compile("_").matcher(str).find())
        {
            var index = str.indexOf("_")
            var str1 = str.substring(0, index)
            var str2 = str.substring(index + 1, index + 2).toUpperCase()
            var str3 = str.substring(index + 2, str.length)
            str = str1 + str2 + str3
        }
        return str
    }

    /**将数据库表命名格式转换为java类命名格式*/

    fun formatToClass(str: String): String
    {
        var str = formatToField(str)
        return str.substring(0, 1).toUpperCase() + str.substring(1, str.length)
    }

    fun checkFormat(str: String): Boolean
    {
        //_不能开头
        var b1 = !str.substring(0, 1).equals("_")
        //_不能结尾
        var b2 = !str.substring(str.length - 1, str.length).equals("_")
        //只包含a-z_
        var b3 = Pattern.compile("[a-z_]+").matcher(str).matches()
        //_不能连续
        var b4 = !Pattern.compile("[_]{2,}").matcher(str).find()
        return b1 && b2 && b3 && b4
    }
}