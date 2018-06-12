import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FastJsonTest {
    static class User {

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

    @Before
    public void setUp() {

    }

    @Test
    public void serializeTest() {
        User user = new User();
        user.setName("ljs");
        user.setAge(22);
        System.out.println(JSON.toJSONString(user));
        //{"name":"ljs","age":22}
    }

    @Test
    public void deserializeTest() {
        User user = JSON.parseObject("{\"name\":\"ljs\",\"age\":22}", User.class);
        System.out.println(user);
    }

    @Test
    public void test() {
        System.out.println(JSON.toJSONString(new Date()));
    }
}
