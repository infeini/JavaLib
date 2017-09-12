package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class NeedsVersion extends BasePojo
{
    /**
     * 需求版本id
     */
    private Integer id;
    /**
     * 版本号
     */
    private Integer code;
    /**
     * 需求id
     */
    private String needsId;
    /**
     * 需求描述
     */
    private String info;
    /**
     * 是否通过
     */
    private Boolean passed;
    /**
     * 功能id
     */
    private Integer functionId;
    /**
     * 产品版本id
     */
    private Integer productVersionId;

    /**
     * 需求版本id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 需求版本id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 版本号 Getter
     */
    public Integer getCode()
    {
        return this.code;
    }

    /**
     * 版本号 Getter
     */
    public void setCode(Integer code)
    {
        this.code = code;
    }

    /**
     * 需求id Getter
     */
    public String getNeedsId()
    {
        return this.needsId;
    }

    /**
     * 需求id Getter
     */
    public void setNeedsId(String needsId)
    {
        this.needsId = needsId;
    }

    /**
     * 需求描述 Getter
     */
    public String getInfo()
    {
        return this.info;
    }

    /**
     * 需求描述 Getter
     */
    public void setInfo(String info)
    {
        this.info = info;
    }

    /**
     * 是否通过 Getter
     */
    public Boolean getPassed()
    {
        return this.passed;
    }

    /**
     * 是否通过 Getter
     */
    public void setPassed(Boolean passed)
    {
        this.passed = passed;
    }

    /**
     * 功能id Getter
     */
    public Integer getFunctionId()
    {
        return this.functionId;
    }

    /**
     * 功能id Getter
     */
    public void setFunctionId(Integer functionId)
    {
        this.functionId = functionId;
    }

    /**
     * 产品版本id Getter
     */
    public Integer getProductVersionId()
    {
        return this.productVersionId;
    }

    /**
     * 产品版本id Getter
     */
    public void setProductVersionId(Integer productVersionId)
    {
        this.productVersionId = productVersionId;
    }
}