package ljs.gson;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * gson时间序列化、反序列化工具，
 * long<=>date
 */
public class LongDateFormat implements JsonDeserializer<Date>, JsonSerializer<Date> {
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new Date(json.getAsLong());
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return src == null ? null : new JsonPrimitive(src.getTime());
    }
}