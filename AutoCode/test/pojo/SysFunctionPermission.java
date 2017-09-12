package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class SysFunctionPermission extends BasePojo
{
    /**
     * 功能id
     */
    private Integer id;
    /**
     * 功能id
     */
    private Integer sysFunctionId;
    /**
     * 功能权限名称
     */
    private String name;
    /**
     * 功能权限详情
     */
    private String info;

    /**
     * 功能id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 功能id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 功能id Getter
     */
    public Integer getSysFunctionId()
    {
        return this.sysFunctionId;
    }

    /**
     * 功能id Getter
     */
    public void setSysFunctionId(Integer sysFunctionId)
    {
        this.sysFunctionId = sysFunctionId;
    }

    /**
     * 功能权限名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 功能权限名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 功能权限详情 Getter
     */
    public String getInfo()
    {
        return this.info;
    }

    /**
     * 功能权限详情 Getter
     */
    public void setInfo(String info)
    {
        this.info = info;
    }
}