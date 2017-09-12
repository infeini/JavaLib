package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.UserSysFunctionPermission;

import java.util.List;

/**
 * @author 刘江山
 */
public interface UserSysFunctionPermissionMapper extends BaseMapper<UserSysFunctionPermission>
{
    /**
     * 通过ID查找对象
     */
    UserSysFunctionPermission findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") UserSysFunctionPermission pojo);

    /**
     * 获取与其相似的数据
     */
    List<UserSysFunctionPermission> findByExample(@Param("pojo") UserSysFunctionPermission pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") UserSysFunctionPermission pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") UserSysFunctionPermission pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

