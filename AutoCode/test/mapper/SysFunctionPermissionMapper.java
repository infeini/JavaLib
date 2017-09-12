package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.SysFunctionPermission;

import java.util.List;

/**
 * @author 刘江山
 */
public interface SysFunctionPermissionMapper extends BaseMapper<SysFunctionPermission>
{
    /**
     * 通过ID查找对象
     */
    SysFunctionPermission findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") SysFunctionPermission pojo);

    /**
     * 获取与其相似的数据
     */
    List<SysFunctionPermission> findByExample(@Param("pojo") SysFunctionPermission pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") SysFunctionPermission pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") SysFunctionPermission pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

