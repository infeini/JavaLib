package base;

import java.util.List;

public interface BaseMapper<T>
{
    T findById(int id);

    int getExampleCount(T pojo);

    List<T> findByExample(T pojo, int start, int length);

    void insert(T pojo);

    void update(T pojo);

    void delete(int id);

}
