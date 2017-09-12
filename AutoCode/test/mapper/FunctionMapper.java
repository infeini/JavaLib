package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.Function;

import java.util.List;

/**
 * @author 刘江山
 */
public interface FunctionMapper extends BaseMapper<Function>
{
    /**
     * 通过ID查找对象
     */
    Function findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") Function pojo);

    /**
     * 获取与其相似的数据
     */
    List<Function> findByExample(@Param("pojo") Function pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") Function pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") Function pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

