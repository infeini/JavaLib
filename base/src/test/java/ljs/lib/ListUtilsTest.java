package ljs.lib;

import ljs.exception.KnowException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListUtilsTest {
    @Test
    public void toArrayTest() throws KnowException {
        List list = new ArrayList();
        list.add(1);
        list.add(2f);
        list.add(new Date());
        String[] array = ListUtils.toArray(list, String.class);
        System.out.println(array);
    }
}
