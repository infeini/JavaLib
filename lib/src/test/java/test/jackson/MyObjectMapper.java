package test.jackson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class MyObjectMapper extends ObjectMapper {
    public <T> T readValue(String content, Class<T> valueType)
            throws IOException, JsonParseException, JsonMappingException {
        return (T) _readMapAndClose(_jsonFactory.createParser(content), _typeFactory.constructType(valueType));
    }

    protected Object _readMapAndClose(JsonParser p0, JavaType valueType)
            throws IOException {
        return null;
    }
}
