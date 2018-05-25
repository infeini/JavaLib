package ljs.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 */
public class PageBean<T> {
    /**
     * 数据总数
     */
    public int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 一页显示数量
     */
    public int onePageShow = 10;
    /**
     * 开始查询数据index
     */
    public int start;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 当前页面路径,如:/vod/edit.action
     */
    private String path;
    /**
     * 当前页码
     */
    private int nowPage;
    /**
     * 当前页数据集合,可为数组,集合
     */
    private List<T> data;
    /**
     * 页码集合
     */
    private ArrayList<Integer> pageCodes;
    /**
     * 是否有上一页
     */
    private boolean haveLast;
    /**
     * 是否有下一页
     */
    private boolean haveNext;

    public PageBean(Integer nowPage, int total, Integer onePageShow) {
        this(null, nowPage, total, onePageShow);
    }

    public PageBean(String path, Integer nowPage, int total, Integer onePageShow) {
        if (nowPage == null || nowPage <= 0)
            this.nowPage = 1;
        else
            this.nowPage = nowPage;
        if (onePageShow != null)
            this.onePageShow = onePageShow;

        this.start = this.onePageShow * (this.nowPage - 1);

        this.path = path;
        this.total = total;

        totalPage = (this.total / this.onePageShow) + (this.total % this.onePageShow == 0 ? 0 : 1);
    }

    public ArrayList<Integer> getPageCodes() {
        if (pageCodes == null) {
            pageCodes = new ArrayList<>();
            if (nowPage <= 5) {
                if (totalPage < 10)
                    addPage(1, totalPage);
                else
                    addPage(1, 10);
            } else if (nowPage >= totalPage - 4)
                addPage(totalPage - 10, totalPage);
            else
                addPage(nowPage - 4, nowPage + 5);
        }
        return pageCodes;
    }

    public void addPage(int from, int to) {
        if (from <= 0)
            from = 1;
        for (int i = from; i <= to; i++)
            pageCodes.add(i);
    }

    public void setPageCodes(ArrayList pageCodes) {
        this.pageCodes = pageCodes;
    }

    public boolean isHaveLast() {
        haveLast = nowPage <= 1 ? false : true;
        return haveLast;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public void setHaveLast(boolean haveLast) {
        this.haveLast = haveLast;
    }

    public boolean isHaveNext() {
        haveNext = nowPage >= totalPage ? false : true;
        return haveNext;
    }

    public void setHaveNext(boolean haveNext) {
        this.haveNext = haveNext;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
