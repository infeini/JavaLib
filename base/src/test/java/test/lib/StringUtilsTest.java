package test.lib;

import ljs.exception.KnowException;
import ljs.lib.StringUtils;
import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void getRandStr() {
        for (int i = 0; i < 100; i++)
            System.out.println(ljs.lib.StringUtils.getRandString(100));
    }

    @Test
    public void isLowerCaseTest() {
        for (char c = 0; c < 200; c++)
            System.out.println(c + " is lower case:" + StringUtils.isLowerCase(c));
    }

    @Test
    public void getRandLowerCaseLetterTest() throws KnowException {
        for (int i = 0; i < 100000; i++) {
            char resultChar = StringUtils.getRandLowerCaseLetter();
            if (!StringUtils.isLowerCase(resultChar))
                throw new KnowException("fail");
            else
                System.out.print(resultChar);
        }
        System.out.println("pass");
    }

    @Test
    public void getRandUpperCaseLetterTest() throws KnowException {
        for (int i = 0; i < 100000; i++) {
            char resultChar = StringUtils.getRandUpperCaseLetter();
            if (!StringUtils.isUpperCase(resultChar))
                throw new KnowException("fail");
            else
                System.out.print(resultChar);
        }
        System.out.println("pass");
    }

    @Test
    public void getRandLowerCaseLettersTest() throws KnowException {
        for (int i = 0; i < 10000; i++) {
            String randLowerCaseLetters = StringUtils.getRandLowerCaseString(100);
            for (int j = 0; j < randLowerCaseLetters.length(); j++) {
                char checkChar = randLowerCaseLetters.charAt(j);
                if (!StringUtils.isLowerCase(checkChar))
                    throw new KnowException("fail:" + checkChar);
            }
        }
        System.out.println("pass");
    }

    @Test
    public void getRandUpperCaseLettersTest() throws KnowException {
        for (int i = 0; i < 10000; i++) {
            String randLowerCaseLetters = StringUtils.getRandUpperCaseString(100);
            for (int j = 0; j < randLowerCaseLetters.length(); j++) {
                char checkChar = randLowerCaseLetters.charAt(j);
                if (!StringUtils.isUpperCase(checkChar))
                    throw new KnowException("fail:" + checkChar);
            }
        }
        System.out.println("pass");
    }
}
