package ljs.bean;

import com.google.gson.Gson;
import ljs.SingletonHolder;
import ljs.exception.KnowException;
import org.junit.Test;

import java.util.Random;

public class PageBeanTest {
    Random random = SingletonHolder.Random.INSTANCE;

    @Test
    public void byPageTest() throws KnowException {
        int randMax = Integer.MAX_VALUE;
        for (int i = 0; i < 1000000; i++) {

            int page = 0;
            while (page == 0)
                page = random.nextInt(randMax);

            int pageSize = 0;
            while (pageSize == 0)
                pageSize = random.nextInt(randMax);

            int total = random.nextInt(randMax);

            PageBean byPageBean = PageBean.byPage(page, pageSize, total);

            int offset = byPageBean.getOffset();

            PageBean byOffsetBean = PageBean.byOffSet(offset, pageSize, total);

            if (offset != byOffsetBean.getOffset())
                throw new KnowException("测试失败:" + new Gson().toJson(new PageBean[]{byPageBean, byOffsetBean}));

            System.out.println("第" + i + "次测试通过");
        }
    }

    @Test
    public void byOffSetTest() throws KnowException {
        int randMax = Integer.MAX_VALUE;
        for (int i = 0; i < 1000000; i++) {

            int offset = 0;
            while (offset == 0)
                offset = random.nextInt(randMax);

            int pageSize = 0;
            while (pageSize == 0)
                pageSize = random.nextInt(randMax);

            int total = random.nextInt(randMax);

            PageBean byOffsetBean = PageBean.byOffSet(offset, pageSize, total);

            int page = byOffsetBean.getPage();

            PageBean byPageBean = PageBean.byPage(page, pageSize, total);

            if (page != byPageBean.getPage())
                throw new KnowException("测试失败:" + new Gson().toJson(new PageBean[]{byPageBean, byOffsetBean}));

            System.out.println("第" + i + "次测试通过");
        }
    }
}
