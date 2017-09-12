package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class Job extends BasePojo
{
    /**
     * 岗位id
     */
    private Integer id;
    /**
     * 岗位名称
     */
    private String name;
    /**
     * 所属部门id
     */
    private Integer departmentId;
    /**
     * 任职条件
     */
    private String premise;
    /**
     * 岗位职责
     */
    private String responsibility;
    /**
     * 岗位备注
     */
    private String info;
    /**
     * 岗位编制人数
     */
    private Integer number;

    /**
     * 岗位id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 岗位id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 岗位名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 岗位名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 所属部门id Getter
     */
    public Integer getDepartmentId()
    {
        return this.departmentId;
    }

    /**
     * 所属部门id Getter
     */
    public void setDepartmentId(Integer departmentId)
    {
        this.departmentId = departmentId;
    }

    /**
     * 任职条件 Getter
     */
    public String getPremise()
    {
        return this.premise;
    }

    /**
     * 任职条件 Getter
     */
    public void setPremise(String premise)
    {
        this.premise = premise;
    }

    /**
     * 岗位职责 Getter
     */
    public String getResponsibility()
    {
        return this.responsibility;
    }

    /**
     * 岗位职责 Getter
     */
    public void setResponsibility(String responsibility)
    {
        this.responsibility = responsibility;
    }

    /**
     * 岗位备注 Getter
     */
    public String getInfo()
    {
        return this.info;
    }

    /**
     * 岗位备注 Getter
     */
    public void setInfo(String info)
    {
        this.info = info;
    }

    /**
     * 岗位编制人数 Getter
     */
    public Integer getNumber()
    {
        return this.number;
    }

    /**
     * 岗位编制人数 Getter
     */
    public void setNumber(Integer number)
    {
        this.number = number;
    }
}