package auto;

import ljs.lib.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field {
    public MyString name;
    public MyType type;
    public Boolean key;
    public String info;
    public Integer length;
    public Boolean nullable;

    public MyString getName() {
        return name;
    }

    public void setName(MyString name) {
        this.name = name;
    }

    public MyType getType() {
        return type;
    }

    public void setType(MyType type) {
        this.type = type;
    }

    public Boolean getKey() {
        return key;
    }

    public void setKey(Boolean key) {
        this.key = key;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Field(MyString name, MyType type, Boolean key, String info, Boolean nullable) {
        this.name = name;
        this.type = type;
        this.key = key;
        this.info = info;
        this.length = initLength();
        this.nullable = nullable;
    }

    Pattern lengthPattern = Pattern.compile("\\d+");

    private int initLength() {
        int length = 0;
        String jdbcType = type.getJdbcType();
        if (!StringUtils.isEmpty(jdbcType)) {
            Matcher matcher = lengthPattern.matcher(jdbcType);
            if (matcher.find())
                length = Integer.parseInt(jdbcType.substring(matcher.start(), matcher.end()));
        }
        return length;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }

    public Pattern getLengthPattern() {
        return lengthPattern;
    }

    public void setLengthPattern(Pattern lengthPattern) {
        this.lengthPattern = lengthPattern;
    }
}