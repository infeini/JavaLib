package pojo;

import base.BasePojo;
import java.util.Date;

/**
 * @author 刘江山
 */
public class Function extends BasePojo
{
    /**
     * 产品功能点id
     */
    private Integer id;
    /**
     * 产品功能点名称
     */
    private String name;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 描述
     */
    private String info;
    /**
     * 产品版本id
     */
    private Integer productVersionId;
    /**
     * 特征
     */
    private String feature;

    /**
     * 产品功能点id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 产品功能点id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 产品功能点名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 产品功能点名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 创建日期 Getter
     */
    public Date getCreateDate()
    {
        return this.createDate;
    }

    /**
     * 创建日期 Getter
     */
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    /**
     * 描述 Getter
     */
    public String getInfo()
    {
        return this.info;
    }

    /**
     * 描述 Getter
     */
    public void setInfo(String info)
    {
        this.info = info;
    }

    /**
     * 产品版本id Getter
     */
    public Integer getProductVersionId()
    {
        return this.productVersionId;
    }

    /**
     * 产品版本id Getter
     */
    public void setProductVersionId(Integer productVersionId)
    {
        this.productVersionId = productVersionId;
    }

    /**
     * 特征 Getter
     */
    public String getFeature()
    {
        return this.feature;
    }

    /**
     * 特征 Getter
     */
    public void setFeature(String feature)
    {
        this.feature = feature;
    }
}