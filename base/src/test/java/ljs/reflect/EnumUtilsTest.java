package ljs.reflect;

import ljs.exception.KnowException;
import org.junit.Assert;
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

    enum EnumType {
        A(EnumType.class), B(Integer.class), C(Long.class);

        public Object value;

        EnumType(Object value) {
            this.value = value;
        }
    }

    @Test
    public void parseTest() throws KnowException {
        Assert.assertTrue(EnumUtils.parse(EnumType.class, EnumType.class, "value") == EnumType.A);
    }

    @Test
    public void canParseTest() {
        Assert.assertTrue(EnumUtils.canParse(EnumType.class, EnumType.class, "value"));
        Assert.assertFalse(EnumUtils.canParse(1, EnumType.class, "value"));
    }
}
