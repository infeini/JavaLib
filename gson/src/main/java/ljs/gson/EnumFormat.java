package ljs.gson;

import com.google.gson.*;
import ljs.exception.KnowException;
import ljs.reflect.EnumUtils;
import ljs.reflect.FieldUtils;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Type;

/**
 * gson枚举序列化、反序列化工具
 */
public abstract class EnumFormat<T> implements JsonDeserializer<T>, JsonSerializer<T> {
    private Class<T> type;

    private String valueFieldName;

    private JsonToValue jsonToValue;

    public interface JsonToValue {
        Object get(JsonElement json);
    }

    public EnumFormat() {
        this(JsonElement::getAsInt);
    }

    public EnumFormat(JsonToValue jsonToValue) {
        this(EnumUtils.DefaultValueFieldName, jsonToValue);
    }

    public EnumFormat(String valueFieldName, JsonToValue jsonToValue) {

        ParameterizedTypeImpl parameterizedType = (ParameterizedTypeImpl) getClass().getGenericSuperclass();

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

        type = (Class<T>) actualTypeArguments[0];

        this.valueFieldName = valueFieldName;
        this.jsonToValue = jsonToValue;
    }

    @Override
    public T deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return EnumUtils.parse(jsonToValue.get(json), type);
        } catch (KnowException e) {
            return null;
        }
    }

    @Override
    public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
        int value = (int) FieldUtils.getValueByName(src, valueFieldName);
        return src == null ? null : new JsonPrimitive(value);
    }
}