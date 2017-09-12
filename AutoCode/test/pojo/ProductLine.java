package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class ProductLine extends BasePojo
{
    /**
     * 产品线id
     */
    private Integer id;
    /**
     * 产品线名称
     */
    private String name;
    /**
     * 产品线经理
     */
    private Integer managerId;

    /**
     * 产品线id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 产品线id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 产品线名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 产品线名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 产品线经理 Getter
     */
    public Integer getManagerId()
    {
        return this.managerId;
    }

    /**
     * 产品线经理 Getter
     */
    public void setManagerId(Integer managerId)
    {
        this.managerId = managerId;
    }
}