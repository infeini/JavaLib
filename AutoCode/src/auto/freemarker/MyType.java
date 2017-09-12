package auto.freemarker;

import java.util.Date;

public class MyType
{
    public Class getType()
    {
        return type;
    }

    public void setType(Class type)
    {
        this.type = type;
    }

    public String getJdbcType()
    {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType)
    {
        this.jdbcType = jdbcType;
    }

    private Class type;
    private String jdbcType;

    public MyType(String jdbcType)
    {
        this.jdbcType = jdbcType;
        if (jdbcType.startsWith("int"))
            this.type = Integer.class;
        else if (jdbcType.startsWith("varchar") || jdbcType.startsWith("text"))
            this.type = String.class;
        else if (jdbcType.startsWith("tinyint"))
            this.type = Boolean.class;
        else if (jdbcType.startsWith("datetime"))
            this.type = Date.class;
        else
            throw new RuntimeException("不能匹配jdbc类型:" + jdbcType);
    }

    public String javaName()
    {
        return this.type.getName();
    }

    public String javaSimpleName()
    {
        return this.type.getSimpleName();
    }

    public String jdbcType()
    {
        return this.jdbcType;
    }

    public Class javaType()
    {
        return this.type;
    }
}
