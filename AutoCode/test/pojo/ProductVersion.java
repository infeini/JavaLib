package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class ProductVersion extends BasePojo
{
    /**
     * 产品版本id
     */
    private Integer id;
    /**
     * 版本名称
     */
    private String name;
    /**
     * 
     */
    private Integer productId;
    /**
     * 产品版本号
     */
    private Integer code;

    /**
     * 产品版本id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 产品版本id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 版本名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 版本名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *  Getter
     */
    public Integer getProductId()
    {
        return this.productId;
    }

    /**
     *  Getter
     */
    public void setProductId(Integer productId)
    {
        this.productId = productId;
    }

    /**
     * 产品版本号 Getter
     */
    public Integer getCode()
    {
        return this.code;
    }

    /**
     * 产品版本号 Getter
     */
    public void setCode(Integer code)
    {
        this.code = code;
    }
}