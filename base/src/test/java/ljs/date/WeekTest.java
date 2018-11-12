package ljs.date;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class WeekTest {
    @Test
    public void weekTest() {
        Week weekDay = Week.pase(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Assert.assertEquals(calendar.get(Calendar.DAY_OF_WEEK), weekDay.getIndex());
    }
}
