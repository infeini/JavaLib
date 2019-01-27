package ljs.lib;

import ljs.exception.KnowException;
import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void getRandStr() {
        for (int i = 0; i < 10; i++)
            System.out.println(StringUtils.getRandString(100));
    }

    @Test
    public void isLowerCaseTest() {
        for (char c = 0; c < 200; c++)
            System.out.println(c + " is lower case:" + StringUtils.isLowerCase(c));
    }

    @Test
    public void getRandLowerCaseLetterTest() {
        for (int i = 0; i < 100000; i++) {
            Assert.assertTrue(StringUtils.isLowerCase(StringUtils.getRandLowerCaseLetter()));
            Assert.assertTrue(StringUtils.isUpperCase(StringUtils.getRandUpperCaseLetter()));
        }
    }
}
