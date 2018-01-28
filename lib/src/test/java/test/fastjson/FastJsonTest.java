package test.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import org.junit.Test;

public class FastJsonTest {
    @Test
    public void test() {
        ParserConfig config = new ParserConfig();
        ParserConfig.getGlobalInstance().putDeserializer(UserMap.class, new JavaBeanDeserializer(ParserConfig.global, UserMap.class));
        UserMap user = JSON.parseObject("{\"age\":1}", UserMap.class);
    }
}
