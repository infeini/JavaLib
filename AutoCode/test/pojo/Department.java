package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class Department extends BasePojo
{
    /**
     * 部门id
     */
    private Integer id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 所属组织id
     */
    private Integer orgId;
    /**
     * 部门负责人id
     */
    private Integer managerId;
    /**
     * 上级部门id
     */
    private Integer superDepartmentId;
    /**
     * 部门备注
     */
    private String remark;

    /**
     * 部门id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 部门id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 部门名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 部门名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 所属组织id Getter
     */
    public Integer getOrgId()
    {
        return this.orgId;
    }

    /**
     * 所属组织id Getter
     */
    public void setOrgId(Integer orgId)
    {
        this.orgId = orgId;
    }

    /**
     * 部门负责人id Getter
     */
    public Integer getManagerId()
    {
        return this.managerId;
    }

    /**
     * 部门负责人id Getter
     */
    public void setManagerId(Integer managerId)
    {
        this.managerId = managerId;
    }

    /**
     * 上级部门id Getter
     */
    public Integer getSuperDepartmentId()
    {
        return this.superDepartmentId;
    }

    /**
     * 上级部门id Getter
     */
    public void setSuperDepartmentId(Integer superDepartmentId)
    {
        this.superDepartmentId = superDepartmentId;
    }

    /**
     * 部门备注 Getter
     */
    public String getRemark()
    {
        return this.remark;
    }

    /**
     * 部门备注 Getter
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
}