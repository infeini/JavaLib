package auto

import ljs.io.IOUtil
import ljs.lib.StringUtils

class FieldInfo(field: String, type: Class<*>, key: Boolean, comment: String)
{
    //字段名
    var field: String = field
    //对应java类型
    var type: Class<*> = type
    //是否主键
    var key: Boolean = key
    //字段介绍
    var comment: String = comment

    fun getFieldCode(): String
    {
        var code = IOUtil.toString(javaClass.getResourceAsStream("/auto/pojo/field.txt"), "UTF-8", true)
        StringUtils.replaceAll(getMap(), code)
        return code.toString()
    }

    fun getGetterCode(): String
    {
        var code = IOUtil.toString(javaClass.getResourceAsStream("/auto/pojo/getMethod.txt"), "UTF-8", true)
        StringUtils.replaceAll(getMap(), code)
        return "\n" + code.toString()
    }

    fun getSetterCode(): String
    {
        var code = IOUtil.toString(javaClass.getResourceAsStream("/auto/pojo/setMethod.txt"), "UTF-8", true)
        StringUtils.replaceAll(getMap(), code)
        return "\n" + code.toString()
    }

    private fun getMap(): HashMap<String, String>
    {
        var fieldName = InfoTool.formatToField(field)
        var fieldType = type.simpleName
        var getMethodName = "get${InfoTool.formatToClass(field)}"
        var setMethodName = "set${InfoTool.formatToClass(field)}"
        var comment = "${comment}"
        var map = HashMap<String, String>()
        map.put("\\*\\{fieldName\\}", fieldName)
        map.put("\\*\\{fieldType\\}", fieldType)
        map.put("\\*\\{getMethodName\\}", getMethodName)
        map.put("\\*\\{setMethodName\\}", setMethodName)
        map.put("\\*\\{comment\\}", comment)
        return map
    }

    fun getPropertyName(): String
    {
        return InfoTool.formatToField(field)
    }
}
