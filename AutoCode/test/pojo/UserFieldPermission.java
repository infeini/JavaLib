package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class UserFieldPermission extends BasePojo
{
    /**
     * 用户与字段权限关系表
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 字段权限id
     */
    private Integer fieldPermissionId;

    /**
     * 用户与字段权限关系表 Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 用户与字段权限关系表 Setter
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