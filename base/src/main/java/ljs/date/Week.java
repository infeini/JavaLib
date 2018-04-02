package ljs.date;

import java.util.Calendar;
import java.util.Date;

public enum Week {
    SUNDAY(Calendar.SUNDAY),
    MONDAY(Calendar.MONDAY),
    TUESDAY(Calendar.TUESDAY),
    WEDNESDAY(Calendar.WEDNESDAY),
    THURSDAY(Calendar.THURSDAY),
    FRIDAY(Calendar.FRIDAY),
    SATURDAY(Calendar.SATURDAY);
    private int index;

    Week(int index) {
        this.index = index;
    }

    public static Week pase(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        for (Week week : Week.values()) {
            if (week.getIndex() == calendar.get(Calendar.DAY_OF_WEEK))
                return week;
        }
        return null;
    }

    public int getIndex() {
        return index;
    }
}
