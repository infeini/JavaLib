package auto;

import java.util.Date;

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

    public MyType(String jdbcType, boolean isKeyField, MyString name) {
        this.jdbcType = jdbcType;
        if (jdbcType.startsWith("int"))
            //外键、以id结尾的字段java类型为Long
            if (isKeyField || name.table().toLowerCase().endsWith("id"))
                this.type = Long.class;
            else
                this.type = Integer.class;
        else if (jdbcType.startsWith("varchar") || jdbcType.startsWith("text"))
            this.type = String.class;
        else if (jdbcType.startsWith("tinyint"))
            this.type = Boolean.class;
        else if (jdbcType.startsWith("datetime"))
            this.type = Date.class;
        else if (jdbcType.startsWith("bigint"))
            this.type = Long.class;
        else if (jdbcType.startsWith("longtext"))
            this.type = String.class;
        else
            throw new RuntimeException("不能匹配jdbc类型:" + jdbcType);
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
