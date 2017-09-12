package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.Product;

import java.util.List;

/**
 * @author 刘江山
 */
public interface ProductMapper extends BaseMapper<Product>
{
    /**
     * 通过ID查找对象
     */
    Product findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") Product pojo);

    /**
     * 获取与其相似的数据
     */
    List<Product> findByExample(@Param("pojo") Product pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") Product pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") Product pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

