package ljs.reflect;

import org.junit.Test;

public class FieldUtilsTest {
    @Test
    public void getFieldsAndValuesTest() {
        Object result = FieldUtils.getFieldNameAndValues(son);
    }

    Son son = new Son();

    {
        son.setSonName("son");
        son.setFatherName("father");
    }

    @Test
    public void getFieldTest() {
        Object obj = FieldUtils.getField(Son.class, "fatherName");
    }

    @Test
    public void getFieldsTest() {
        Object obj = FieldUtils.getFields(Son.class);
    }
}

class Father {
    private String fatherName;

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
}

class Son extends Father {
    public String getSonName() {
        return sonName;
    }

    public void setSonName(String sonName) {
        this.sonName = sonName;
    }

    private String sonName;
}