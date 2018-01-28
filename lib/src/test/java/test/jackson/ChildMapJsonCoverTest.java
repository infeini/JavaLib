package test.jackson;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.Before;
import org.junit.Test;

public class ChildMapJsonCoverTest {
    ObjectMapper objectMapper;

    @Before
    public void before() throws JsonMappingException {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();

        module.addDeserializer(UserMap.class, new ChildMapJsonDeserializer(UserMap.class));

        objectMapper.registerModule(module);
    }

    @Test
    public void toJsonTest() throws Exception {
        UserMap user = new UserMap();
        //user.setAge(12);
        String json = toJson(user);
        System.out.println(json);
    }

    @Test
    public void fromJsonTest() throws Exception {
        UserMap user = fromJson("{\"age\":\"12\"}", UserMap.class);
    }

    public <T> T fromJson(String json, Class<T> type) throws Exception {
        return objectMapper.readValue(json, type);
    }

    public String toJson(Object obj) throws Exception {
        return objectMapper.writeValueAsString(obj);
    }
}
