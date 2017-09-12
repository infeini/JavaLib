package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class Organization extends BasePojo
{
    /**
     * 组织id
     */
    private Integer id;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 组织负责人id
     */
    private Integer managerId;
    /**
     * 组织备注
     */
    private String remark;

    /**
     * 组织id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 组织id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 组织名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 组织名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 组织负责人id Getter
     */
    public Integer getManagerId()
    {
        return this.managerId;
    }

    /**
     * 组织负责人id Getter
     */
    public void setManagerId(Integer managerId)
    {
        this.managerId = managerId;
    }

    /**
     * 组织备注 Getter
     */
    public String getRemark()
    {
        return this.remark;
    }

    /**
     * 组织备注 Getter
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}