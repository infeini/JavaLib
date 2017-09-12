package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.RoleTablePermission;

import java.util.List;

/**
 * @author 刘江山
 */
public interface RoleTablePermissionMapper extends BaseMapper<RoleTablePermission>
{
    /**
     * 通过ID查找对象
     */
    RoleTablePermission findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") RoleTablePermission pojo);

    /**
     * 获取与其相似的数据
     */
    List<RoleTablePermission> findByExample(@Param("pojo") RoleTablePermission pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") RoleTablePermission pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") RoleTablePermission pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}
