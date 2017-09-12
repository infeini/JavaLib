package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.RoleSysFunctionPermission;

import java.util.List;

/**
 * @author 刘江山
 */
public interface RoleSysFunctionPermissionMapper extends BaseMapper<RoleSysFunctionPermission>
{
    /**
     * 通过ID查找对象
     */
    RoleSysFunctionPermission findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") RoleSysFunctionPermission pojo);

    /**
     * 获取与其相似的数据
     */
    List<RoleSysFunctionPermission> findByExample(@Param("pojo") RoleSysFunctionPermission pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") RoleSysFunctionPermission pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") RoleSysFunctionPermission pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

