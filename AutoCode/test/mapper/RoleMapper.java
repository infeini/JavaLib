package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.Role;

import java.util.List;

/**
 * @author 刘江山
 */
public interface RoleMapper extends BaseMapper<Role>
{
    /**
     * 通过ID查找对象
     */
    Role findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") Role pojo);

    /**
     * 获取与其相似的数据
     */
    List<Role> findByExample(@Param("pojo") Role pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") Role pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") Role pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

