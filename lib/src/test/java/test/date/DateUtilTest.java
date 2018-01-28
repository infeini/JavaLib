package test.date;

import org.junit.Test;

public class DateUtilTest {
    @Test
    public void toHMS() {
        String hms = ljs.date.DateUtil.toHMS(60 * 60 * 24 + 59 + 60);
        System.out.println(hms);
    }
}
