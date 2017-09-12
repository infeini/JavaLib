package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.TablePermission;

import java.util.List;

/**
 * @author 刘江山
 */
public interface TablePermissionMapper extends BaseMapper<TablePermission>
{
    /**
     * 通过ID查找对象
     */
    TablePermission findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") TablePermission pojo);

    /**
     * 获取与其相似的数据
     */
    List<TablePermission> findByExample(@Param("pojo") TablePermission pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") TablePermission pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") TablePermission pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

