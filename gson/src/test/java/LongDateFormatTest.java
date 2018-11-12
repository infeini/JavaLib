import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ljs.gson.LongDateFormat;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class LongDateFormatTest {
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(Date.class, new LongDateFormat())
            .create();
    private Date date = new Date();

    @Test
    public void serializeDateTest() {
        long longDate = Long.parseLong(gson.toJson(date));
        Assert.assertEquals(longDate, date.getTime());
    }

    @Test
    public void deserializeDateTest() {
        Date parsedDate = gson.fromJson(date.getTime() + "", Date.class);
        Assert.assertEquals(parsedDate.getTime(), date.getTime());
    }
}
