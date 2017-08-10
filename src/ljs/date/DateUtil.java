package ljs.date;

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
        return patchZero(hour) + ":" + patchZero(minute) + ":" + patchZero(second);
    }

    private static String patchZero(long number)
    {
        String addition = "";
        if (number >= 0 && number <= 9)
            addition = "0";
        return addition + number;
    }
}
