package ljs.date;

import org.junit.Test;

import java.util.Date;

public class WeekTest {
    @Test
    public void weekTest() {
        Week weekDay = Week.pase(new Date());
        System.out.println(weekDay.name());
    }
}
