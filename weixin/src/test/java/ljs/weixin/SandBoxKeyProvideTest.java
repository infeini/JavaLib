package ljs.weixin;

import ljs.exception.KnowException;
import ljs.weixin.SandBoxKeyProvide;
import org.junit.Test;

public class SandBoxKeyProvideTest {
    @Test
    public void getSandBoxKey() throws KnowException {
        String sandBoxKey = new SandBoxKeyProvide().getSandBoxKey("1499745652", "693ac274c7654a60c422b313530385f6");
        System.out.println(sandBoxKey);
    }
}
