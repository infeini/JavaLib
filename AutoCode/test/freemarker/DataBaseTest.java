package freemarker;

import auto.freemarker.DataBase;
import org.junit.Test;

public class DataBaseTest
{
    @Test
    public void test() throws Exception
    {
        DataBase dataBase = new DataBase("plm", "localhost", 3306, "root", "123456");
    }
}
