package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class UserSysFunctionPermission extends BasePojo
{
    /**
     * 
     */
    private Integer id;
    /**
     * 
     */
    private Integer userId;
    /**
     * 
     */
    private Integer sysFunctionPermission;

    /**
     *  Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     *  Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     *  Getter
     */
    public Integer getUserId()
    {
        return this.userId;
    }

    /**
     *  Getter
     */
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    /**
     *  Getter
     */
    public Integer getSysFunctionPermission()
    {
        return this.sysFunctionPermission;
    }

    /**
     *  Getter
     */
    public void setSysFunctionPermission(Integer sysFunctionPermission)
    {
        this.sysFunctionPermission = sysFunctionPermission;
    }
}