package ljs.weixin;

import ljs.exception.KnowException;
import ljs.weixin.SandBoxKeyProvide;
import org.junit.Test;

public class SandBoxKeyProvideTest {
    @Test
    public void getSandBoxKey() throws KnowException {
        String sandBoxKey = new SandBoxKeyProvide().getSandBoxKey("", "");
        System.out.println(sandBoxKey);
    }
}
