package zlx.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据字段信息
 *
 * @author LiuJiangshan
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Field {
    /**
     * 字段长度
     */
    int length() default 0;

    /**
     * 是否允许为空
     */
    boolean nullable() default false;

    /**
     * 是否主键
     */
    boolean key() default false;
}
