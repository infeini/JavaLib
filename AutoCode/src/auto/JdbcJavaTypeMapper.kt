package auto
import java.util.*

object JdbcJavaTypeMapper
{
    /**通过jdbc字段类型获取java类型*/
    fun mapperJavaType(jdbcType: String): Class<*>
    {
        return when
        {
            jdbcType.startsWith("int") -> Int::class.java
            jdbcType.startsWith("varchar") || jdbcType.startsWith("text") -> String::class.java
            jdbcType.startsWith("tinyint") -> Boolean::class.java
            jdbcType.startsWith("datetime") -> Date::class.java
            else -> throw RuntimeException("不能匹配jdbc类型:" + jdbcType)
        }
    }
}