package ljs.date;

import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
    static final long Hour_Second = 60 * 60;

    /**
     * 将秒转换成计时格式字符串
     *
     * @param ofSecond 秒钟数
     * @return 计时格式字符串
     */
    public static String toHMS(long ofSecond)
    {
        long hour = ofSecond / Hour_Second;
        long minute = (ofSecond % Hour_Second) / 60;
        long second = ofSecond % 60;
        return patchZero(hour, 2) + ":" + patchZero(minute, 2) + ":" + patchZero(second, 2);
    }

    /**
     * 用零补全位数
     */
    private static String patchZero(long number, int length)
    {
        String addition = "";
        String result = number + "";
        if (number < 0)
        {
            addition = "-";
            number = -number;
        }
        while (result.length() < length)
            result = "0" + result;
        return addition + result;
    }
}
