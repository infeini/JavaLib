package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.RoleFieldPermission;

import java.util.List;

/**
 * @author 刘江山
 */
public interface RoleFieldPermissionMapper extends BaseMapper<RoleFieldPermission>
{
    /**
     * 通过ID查找对象
     */
    RoleFieldPermission findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") RoleFieldPermission pojo);

    /**
     * 获取与其相似的数据
     */
    List<RoleFieldPermission> findByExample(@Param("pojo") RoleFieldPermission pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") RoleFieldPermission pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") RoleFieldPermission pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

