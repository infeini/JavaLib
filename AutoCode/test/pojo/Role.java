package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class Role extends BasePojo
{
    /**
     * 角色id
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 描述
     */
    private String info;

    /**
     * 角色id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 角色id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 角色名称 Getter
     */
    public String getRoleName()
    {
        return this.roleName;
    }

    /**
     * 角色名称 Getter
     */
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    /**
     * 描述 Getter
     */
    public String getInfo()
    {
        return this.info;
    }

    /**
     * 描述 Getter
     */
    public void setInfo(String info)
    {
        this.info = info;
    }
}