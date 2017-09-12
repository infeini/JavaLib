package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.NeedsVersion;

import java.util.List;

/**
 * @author 刘江山
 */
public interface NeedsVersionMapper extends BaseMapper<NeedsVersion>
{
    /**
     * 通过ID查找对象
     */
    NeedsVersion findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") NeedsVersion pojo);

    /**
     * 获取与其相似的数据
     */
    List<NeedsVersion> findByExample(@Param("pojo") NeedsVersion pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") NeedsVersion pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") NeedsVersion pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

