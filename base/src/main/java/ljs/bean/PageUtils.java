package ljs.bean;

public class PageUtils {
    public static int getOffset(int page, int pageSize) {
        int offset = pageSize * (page - 1);
        if (offset < 0)
            offset = 0;
        return offset;
    }

    public static int getPage(int offset, int pageSize) {
        int page = offset / pageSize;
        if (offset % pageSize == 0 || page == 0)
            page++;
        return page;
    }
}
