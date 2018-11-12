import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ljs.gson.EnumFormat;
import org.junit.Assert;
import org.junit.Test;

public class EnumFormatTest {
    enum Sex {
        Man(0), WoMan(1);
        public int value;

        Sex(int value) {
            this.value = value;
        }
    }

    class User {
        private Sex sex;

        public User(Sex sex) {
            this.sex = sex;
        }

        public Sex getSex() {
            return sex;
        }

        public void setSex(Sex sex) {
            this.sex = sex;
        }
    }

    private Gson gson = new GsonBuilder().registerTypeAdapter(Sex.class, new EnumFormat<Sex>() {
    }).create();
    private Sex sex = Sex.Man;

    @Test
    public void serializeDateTest() {
        User user = new User(sex);
        String jsonString = gson.toJson(user);
        Assert.assertEquals(jsonString, "{\"sex\":0}");
    }

    @Test
    public void deserializeDateTest() {
        String jsonStr = "{\"sex\":0}";
        User user = gson.fromJson(jsonStr, User.class);
        Assert.assertEquals(user.sex, sex);
    }
}
