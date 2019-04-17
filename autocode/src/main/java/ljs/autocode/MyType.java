package ljs.autocode;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MyType {
    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    private Class type;
    private String jdbcType;
    static Map<String, Class> typeMap = new HashMap<>();

    static {
        typeMap.put("int", Long.class);
        typeMap.put("bigint", Long.class);
        typeMap.put("char", String.class);
        typeMap.put("varchar", String.class);
        typeMap.put("tinytext", String.class);
        typeMap.put("longtext", String.class);
        typeMap.put("text", String.class);
        typeMap.put("tinyint", Boolean.class);
        typeMap.put("datetime", Date.class);
        typeMap.put("float", Float.class);
    }

    public MyType(String jdbcType, boolean isKeyField, StringWrap name) {

        jdbcType = jdbcType.toLowerCase();

        if (jdbcType.contains("(") && jdbcType.contains(")"))
            jdbcType = jdbcType.substring(0, jdbcType.indexOf("("));

        this.jdbcType = jdbcType;

        type = typeMap.get(this.jdbcType);
        if (type == null) throw new RuntimeException("不能匹配jdbc类型:" + jdbcType);
    }

    public String javaName() {
        return this.type.getName();
    }

    public String javaSimpleName() {
        return this.type.getSimpleName();
    }

    public String jdbcType() {
        return this.jdbcType;
    }

    public Class javaType() {
        return this.type;
    }
}
