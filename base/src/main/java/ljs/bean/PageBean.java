package ljs.bean;

import ljs.exception.KnowException;

/**
 * 分页工具类
 */
public class PageBean {
    private int pageSize;
    private int page;
    private int offset;

    public static PageBean byPage(int page, int pageSize, int total) throws KnowException {
        if (page <= 0) throw new KnowException("page不能小于等于0");
        PageBean pageBean = new PageBean(pageSize, total);
        pageBean.page = page;
        pageBean.offset = PageUtils.getOffset(page, pageSize);
        return pageBean;
    }

    public static PageBean byOffSet(int offset, int pageSize, int total) throws KnowException {
        if (offset < 0) throw new KnowException("offset不能小于0");
        PageBean pageBean = new PageBean(pageSize, total);
        pageBean.offset = offset;
        pageBean.page = PageUtils.getPage(offset, pageSize);
        return pageBean;
    }

    private PageBean(int pageSize, int total) throws KnowException {
        if (pageSize <= 0) throw new KnowException("pageSize不能小于等于0");
        else if (total < 0) throw new KnowException("total不能小于0");
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }

    public int getOffset() {
        return offset;
    }
}
