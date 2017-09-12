package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class TablePermission extends BasePojo
{
    /**
     * 数据库权限id
     */
    private Integer id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限对应目标的名称:tableName、tableName.fieldName、functionName
     */
    private String tableName;
    /**
     * 插入数据权限
     */
    private Boolean create;
    /**
     * 读取数据权限
     */
    private Boolean read;
    /**
     * 更新数据权限
     */
    private Boolean update;
    /**
     * 删除数据权限
     */
    private Boolean delete;
    /**
     * 权限详情
     */
    private String info;

    /**
     * 数据库权限id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 数据库权限id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 权限名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 权限名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 权限对应目标的名称:tableName、tableName.fieldName、functionName Getter
     */
    public String getTableName()
    {
        return this.tableName;
    }

    /**
     * 权限对应目标的名称:tableName、tableName.fieldName、functionName Getter
     */
    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    /**
     * 插入数据权限 Getter
     */
    public Boolean getCreate()
    {
        return this.create;
    }

    /**
     * 插入数据权限 Getter
     */
    public void setCreate(Boolean create)
    {
        this.create = create;
    }

    /**
     * 读取数据权限 Getter
     */
    public Boolean getRead()
    {
        return this.read;
    }

    /**
     * 读取数据权限 Getter
     */
    public void setRead(Boolean read)
    {
        this.read = read;
    }

    /**
     * 更新数据权限 Getter
     */
    public Boolean getUpdate()
    {
        return this.update;
    }

    /**
     * 更新数据权限 Getter
     */
    public void setUpdate(Boolean update)
    {
        this.update = update;
    }

    /**
     * 删除数据权限 Getter
     */
    public Boolean getDelete()
    {
        return this.delete;
    }

    /**
     * 删除数据权限 Getter
     */
    public void setDelete(Boolean delete)
    {
        this.delete = delete;
    }

    /**
     * 权限详情 Getter
     */
    public String getInfo()
    {
        return this.info;
    }

    /**
     * 权限详情 Getter
     */
    public void setInfo(String info)
    {
        this.info = info;
    }
}