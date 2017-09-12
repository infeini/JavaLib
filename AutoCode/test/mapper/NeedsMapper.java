package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.Needs;

import java.util.List;

/**
 * @author 刘江山
 */
public interface NeedsMapper extends BaseMapper<Needs>
{
    /**
     * 通过ID查找对象
     */
    Needs findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") Needs pojo);

    /**
     * 获取与其相似的数据
     */
    List<Needs> findByExample(@Param("pojo") Needs pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") Needs pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") Needs pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

