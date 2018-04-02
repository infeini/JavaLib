package zlx.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据表信息
 *
 * @author LiuJiangshan
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    /**
     * 数据表名称
     */
    String name();

    /**
     * 数据表用途介绍
     */
    String info();
}
