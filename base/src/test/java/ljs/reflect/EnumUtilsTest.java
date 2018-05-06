package ljs.reflect;

import ljs.exception.KnowException;
import org.junit.Test;

public class EnumUtilsTest {


    @Test
    public void toValueTest() throws KnowException {
        System.out.println(EnumUtils.toValue(EnumType.A, "value"));
        System.out.println(EnumUtils.toValue(EnumType.B, "value"));
        System.out.println(EnumUtils.toValue(EnumType.C, "value"));
        System.out.println(EnumUtils.toValue(EnumType.A));
        System.out.println(EnumUtils.toValue(EnumType.B));
        System.out.println(EnumUtils.toValue(EnumType.C));
    }

    @Test
    public void parseTest() throws KnowException {
        System.out.println(EnumUtils.parse(EnumType.class, EnumType.class, "value"));
        System.out.println(EnumUtils.parse(1, EnumType.class, "value"));
        System.out.println(EnumUtils.parse(2, EnumType.class, "value"));
        System.out.println(EnumUtils.parse(0, EnumType.class));
        System.out.println(EnumUtils.parse(1, EnumType.class));
        System.out.println(EnumUtils.parse(2, EnumType.class));
    }

    @Test
    public void canParseTest() {
        System.out.println(EnumUtils.canParse(EnumType.class, EnumType.class, "value"));
        System.out.println(EnumUtils.canParse(1, EnumType.class, "value"));
        System.out.println(EnumUtils.canParse(2, EnumType.class, "value"));
        System.out.println(EnumUtils.canParse(0, EnumType.class));
        System.out.println(EnumUtils.canParse(1, EnumType.class));
        System.out.println(EnumUtils.canParse(2, EnumType.class));
    }
}
