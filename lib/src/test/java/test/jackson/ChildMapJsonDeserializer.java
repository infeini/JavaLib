package test.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.type.SimpleType;

import java.io.IOException;

public class ChildMapJsonDeserializer extends StdDeserializer {
    protected ChildMapJsonDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JavaType type = SimpleType.constructUnsafe(UserMap.class);
        BasicBeanDescription beanDescription = ctxt.getConfig().introspect(type);
        JsonDeserializer deserializer = BeanDeserializerFactory.instance.buildBeanDeserializer(ctxt, type, beanDescription);
        return deserializer.deserialize(p, ctxt);
    }
}
