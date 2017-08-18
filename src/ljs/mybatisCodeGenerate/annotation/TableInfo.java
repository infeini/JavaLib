package ljs.mybatisCodeGenerate.annotation;

import java.lang.annotation.*;

/**
 * 注解声明实体对象对应的表信息
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableInfo
{
    String tableName();
}
