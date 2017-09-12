package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.FieldPermission;

import java.util.List;

/**
 * @author 刘江山
 */
public interface FieldPermissionMapper extends BaseMapper<FieldPermission>
{
    /**
     * 通过ID查找对象
     */
    FieldPermission findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") FieldPermission pojo);

    /**
     * 获取与其相似的数据
     */
    List<FieldPermission> findByExample(@Param("pojo") FieldPermission pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") FieldPermission pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") FieldPermission pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

