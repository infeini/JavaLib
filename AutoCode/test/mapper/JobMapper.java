package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.Job;

import java.util.List;

/**
 * @author 刘江山
 */
public interface JobMapper extends BaseMapper<Job>
{
    /**
     * 通过ID查找对象
     */
    Job findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") Job pojo);

    /**
     * 获取与其相似的数据
     */
    List<Job> findByExample(@Param("pojo") Job pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") Job pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") Job pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

