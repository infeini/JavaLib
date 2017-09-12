package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.ProductLine;

import java.util.List;

/**
 * @author 刘江山
 */
public interface ProductLineMapper extends BaseMapper<ProductLine>
{
    /**
     * 通过ID查找对象
     */
    ProductLine findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") ProductLine pojo);

    /**
     * 获取与其相似的数据
     */
    List<ProductLine> findByExample(@Param("pojo") ProductLine pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") ProductLine pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") ProductLine pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

