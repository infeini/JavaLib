package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class RoleTablePermission extends BasePojo
{
    /**
     * 角色数据表权限关系id
     */
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 数据表权限id
     */
    private Integer tablePermissionId;

    /**
     * 角色数据表权限关系id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 角色数据表权限关系id Setter
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
     * 数据表权限id Getter
     */
    public Integer getTablePermissionId()
    {
        return this.tablePermissionId;
    }

    /**
     * 数据表权限id Getter
     */
    public void setTablePermissionId(Integer tablePermissionId)
    {
        this.tablePermissionId = tablePermissionId;
    }
}