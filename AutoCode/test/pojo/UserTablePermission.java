package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class UserTablePermission extends BasePojo
{
    /**
     * 用户与数据表权限管理
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 数据表权限id
     */
    private Integer tablePermissionId;

    /**
     * 用户与数据表权限管理 Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 用户与数据表权限管理 Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 用户id Getter
     */
    public Integer getUserId()
    {
        return this.userId;
    }

    /**
     * 用户id Getter
     */
    public void setUserId(Integer userId)
    {
        this.userId = userId;
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