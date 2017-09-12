package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.UserFieldPermission;

import java.util.List;

/**
 * @author 刘江山
 */
public interface UserFieldPermissionMapper extends BaseMapper<UserFieldPermission>
{
    /**
     * 通过ID查找对象
     */
    UserFieldPermission findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") UserFieldPermission pojo);

    /**
     * 获取与其相似的数据
     */
    List<UserFieldPermission> findByExample(@Param("pojo") UserFieldPermission pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") UserFieldPermission pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") UserFieldPermission pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}
