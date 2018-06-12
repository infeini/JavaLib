import com.google.gson.*;
import ljs.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class GsonTest {

    Gson gson;

    class User {

        public Map<String, Object> map = new HashMap<>();

        private String name;

        private int age;

        public <T> T get(String key) {
            return (T) map.get(key);
        }

        public void put(String key, Object value) {
            map.put(key, value);
        }

        public String getName() {
            return get("name");
        }

        public void setName(String name) {
            put("name", name);
        }

        public int getAge() {
            return get("age");
        }

        public void setAge(int age) {
            put("age", age);
        }
    }

    class UserCustom implements JsonSerializer<User>, JsonDeserializer<User> {
        @Override
        public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            User user = jsonDeserializationContext.deserialize(jsonElement, type);
            Map<String, Object> map = FieldUtils.getFieldNameAndValues(user);
            map.remove("map");
            user.map = map;
            return user;
        }

        @Override
        public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
            return jsonSerializationContext.serialize(user.map);
        }
    }

    class SimpleBean {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Before
    public void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(User.class, new UserCustom())
                .create();
        Object adapter = gson.getAdapter(SimpleBean.class);
    }

    @Test
    public void serializeTest() {
        User user = new User();
        user.setName("ljs");
        user.setAge(22);
        System.out.println(gson.toJson(user));
        //{"name":"ljs","age":22}
    }

    @Test
    public void deserializeTest() {
        User user = gson.fromJson("{\"name\":\"ljs\",\"age\":22}", User.class);
        System.out.println(user);
    }
}
