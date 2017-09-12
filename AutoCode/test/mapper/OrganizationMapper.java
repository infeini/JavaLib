package mapper;

import base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import pojo.Organization;

import java.util.List;

/**
 * @author 刘江山
 */
public interface OrganizationMapper extends BaseMapper<Organization>
{
    /**
     * 通过ID查找对象
     */
    Organization findById(@Param("id") int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(@Param("pojo") Organization pojo);

    /**
     * 获取与其相似的数据
     */
    List<Organization> findByExample(@Param("pojo") Organization pojo, @Param("start") int start, @Param("length") int length);

    /**
     * 插入数据
     */
    void insert(@Param("pojo") Organization pojo);

    /**
     * 更新数据
     */
    void update(@Param("pojo") Organization pojo);

    /**
     * 删除数据
     */
    void delete(@Param("id") int id);
}

