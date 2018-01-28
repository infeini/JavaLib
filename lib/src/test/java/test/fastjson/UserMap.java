package test.fastjson;

import java.util.Date;
import java.util.HashMap;

public class UserMap extends HashMap<String, Object> {
    private Date age;

    public Date getAge() {
        return (Date) get("age");
    }

    public void setAge(Date age) {
        put("age", age);
    }
}