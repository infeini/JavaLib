package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class RoleSysFunctionPermission extends BasePojo
{
    /**
     * 角色与系统功能权限关系id
     */
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 系统功能权限id
     */
    private Integer sysFunctionPermissionId;

    /**
     * 角色与系统功能权限关系id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 角色与系统功能权限关系id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 角色id Getter
     */
    public Integer getRoleId()
    {
        return this.roleId;
    }

    /**
     * 角色id Getter
     */
    public void setRoleId(Integer roleId)
    {
        this.roleId = roleId;
    }

    /**
     * 系统功能权限id Getter
     */
    public Integer getSysFunctionPermissionId()
    {
        return this.sysFunctionPermissionId;
    }

    /**
     * 系统功能权限id Getter
     */
    public void setSysFunctionPermissionId(Integer sysFunctionPermissionId)
    {
        this.sysFunctionPermissionId = sysFunctionPermissionId;
    }
}