package test.jackson;

import java.util.HashMap;

public class UserMap extends HashMap<String, Object> {
    private String age;

    public String getAge() {
        return (String) get("age");
    }

    public void setAge(String age) {
        put("age", age);
    }
}