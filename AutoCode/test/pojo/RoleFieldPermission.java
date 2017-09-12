package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class RoleFieldPermission extends BasePojo
{
    /**
     * 角色字段权限关系id
     */
    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 字段权限id
     */
    private Integer fieldPermissionId;

    /**
     * 角色字段权限关系id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 角色字段权限关系id Setter
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
     * 字段权限id Getter
     */
    public Integer getFieldPermissionId()
    {
        return this.fieldPermissionId;
    }

    /**
     * 字段权限id Getter
     */
    public void setFieldPermissionId(Integer fieldPermissionId)
    {
        this.fieldPermissionId = fieldPermissionId;
    }
}