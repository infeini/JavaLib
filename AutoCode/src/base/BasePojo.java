package base;

/**
 * pojo实体公共字段
 */
public class BasePojo
{
    /**
     * 分页页码
     */
    private int page;
    /**
     * 一页显示的数据条数,默认为10条
     */
    private int onePageShow = 10;

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getPage()
    {
        return page;
    }

    public void setOnePageShow(int onePageShow)
    {
        this.onePageShow = onePageShow;
    }

    public int getOnePageShow()
    {
        return onePageShow;
    }
}
