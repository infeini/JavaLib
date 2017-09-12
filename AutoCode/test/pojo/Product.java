package pojo;

import base.BasePojo;
/**
 * @author 刘江山
 */
public class Product extends BasePojo
{
    /**
     * 产品id
     */
    private Integer id;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 所属产品线
     */
    private Integer productLineId;
    /**
     * 产品经理
     */
    private Integer managerId;
    /**
     * 产品类型:0(自家产品)、2(竞争对手产品)
     */
    private Integer type;
    /**
     * 竞争产品id
     */
    private Integer targetProductId;

    /**
     * 产品id Getter
     */
    public Integer getId()
    {
        return this.id;
    }

    /**
     * 产品id Setter
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * 产品名称 Getter
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * 产品名称 Getter
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 所属产品线 Getter
     */
    public Integer getProductLineId()
    {
        return this.productLineId;
    }

    /**
     * 所属产品线 Getter
     */
    public void setProductLineId(Integer productLineId)
    {
        this.productLineId = productLineId;
    }

    /**
     * 产品经理 Getter
     */
    public Integer getManagerId()
    {
        return this.managerId;
    }

    /**
     * 产品经理 Getter
     */
    public void setManagerId(Integer managerId)
    {
        this.managerId = managerId;
    }

    /**
     * 产品类型:0(自家产品)、2(竞争对手产品) Getter
     */
    public Integer getType()
    {
        return this.type;
    }

    /**
     * 产品类型:0(自家产品)、2(竞争对手产品) Getter
     */
    public void setType(Integer type)
    {
        this.type = type;
    }

    /**
     * 竞争产品id Getter
     */
    public Integer getTargetProductId()
    {
        return this.targetProductId;
    }

    /**
     * 竞争产品id Getter
     */
    public void setTargetProductId(Integer targetProductId)
    {
        this.targetProductId = targetProductId;
    }
}