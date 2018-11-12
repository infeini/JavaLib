package ljs.date;

import org.junit.Assert;
import org.junit.Test;

public class DateUtilTest {
    @Test
    public void toHMSTest() {
        //一分钟
        Assert.assertEquals(DateUtil.toHMS(60), "00:01:00");
        //一小时
        Assert.assertEquals(DateUtil.toHMS(60 * 60), "01:00:00");
        //一天
        Assert.assertEquals(DateUtil.toHMS(60 * 60 * 24), "24:00:00");
    }
}
