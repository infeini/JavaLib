package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class FieldPermission extends BasePojo
{
    /**
     * 字段权限id
     */
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 读取权限
     */
    private Boolean read;
    /**
     * 写入权限
     */
    private Boolean write;

    /**
     * 字段权限id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 字段权限id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 字段名称 Getter
     */
    public String getFieldName()
    {
        return this.fieldName;
    }

    /**
     * 字段名称 Getter
     */
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    /**
     * 读取权限 Getter
     */
    public Boolean getRead()
    {
        return this.read;
    }

    /**
     * 读取权限 Getter
     */
    public void setRead(Boolean read)
    {
        this.read = read;
    }

    /**
     * 写入权限 Getter
     */
    public Boolean getWrite()
    {
        return this.write;
    }

    /**
     * 写入权限 Getter
     */
    public void setWrite(Boolean write)
    {
        this.write = write;
    }
}