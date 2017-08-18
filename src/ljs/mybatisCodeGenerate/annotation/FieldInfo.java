package ljs.mybatisCodeGenerate.annotation;

import java.lang.annotation.*;

/**
 * 注解声明实体对象对应的字段信息
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldInfo
{
    String tableFieldName();

    boolean isPrimaryKey() default false;
}
