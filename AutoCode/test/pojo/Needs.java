package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class Needs extends BasePojo
{
    /**
     * 需求id
     */
    private Integer id;
    /**
     * 需求名称
     */
    private String name;
    /**
     * 需求创建者id
     */
    private Integer creatorId;

    /**
     * 需求id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 需求id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 需求名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 需求名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 需求创建者id Getter
     */
    public Integer getCreatorId()
    {
        return this.creatorId;
    }

    /**
     * 需求创建者id Getter
     */
    public void setCreatorId(Integer creatorId)
    {
        this.creatorId = creatorId;
    }
}