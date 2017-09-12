package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.ProductVersion;

import java.util.List;

/**
 * @author 刘江山
 */
public interface ProductVersionMapper extends BaseMapper<ProductVersion>
{
    /**
     * 通过ID查找对象
     */
    ProductVersion findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") ProductVersion pojo);

    /**
     * 获取与其相似的数据
     */
    List<ProductVersion> findByExample(@Param("pojo") ProductVersion pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") ProductVersion pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") ProductVersion pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

