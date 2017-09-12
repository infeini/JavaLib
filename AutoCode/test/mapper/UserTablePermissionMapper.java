package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.UserTablePermission;

import java.util.List;

/**
 * @author 刘江山
 */
public interface UserTablePermissionMapper extends BaseMapper<UserTablePermission>
{
    /**
     * 通过ID查找对象
     */
    UserTablePermission findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") UserTablePermission pojo);

    /**
     * 获取与其相似的数据
     */
    List<UserTablePermission> findByExample(@Param("pojo") UserTablePermission pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") UserTablePermission pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") UserTablePermission pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

