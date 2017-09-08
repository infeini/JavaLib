package ljs.code.mybatis.mapper.example;

import java.util.List;

public interface BaseMapper<T>
{
    /**
     * 通过ID查找对象
     */
    T findById(int id);

    /**
     * 获取与其相似的数据数量
     */
    int getExampleCount(T pojo);

    /**
     * 获取与其相似的数据
     */
    List<T> findByExample(T pojo,
                          int start,
                          int length);

    /**
     * 插入数据
     */
    void insert(T pojo);

    /**
     * 更新数据
     */
    void update(T pojo);

    /**
     * 删除数据
     */
    void delete(int id);
}
