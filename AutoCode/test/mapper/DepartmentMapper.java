package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.Department;

import java.util.List;

/**
 * @author 刘江山
 */
public interface DepartmentMapper extends BaseMapper<Department>
{
    /**
     * 通过ID查找对象
     */
    Department findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") Department pojo);

    /**
     * 获取与其相似的数据
     */
    List<Department> findByExample(@Param("pojo") Department pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") Department pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") Department pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

