package auto

import java.util.*

class TableInfo(tableName: String)
{
    //表名称
    var tabelName: String = tableName
    //表字段信息
    var fieldInfos: ArrayList<FieldInfo> = ArrayList()
    var key: FieldInfo? = null

    fun getImportCode(): String
    {
        var importCode = StringBuffer("")
        var haveDate = false
        for (fieldInfo in fieldInfos)
        {
            if (fieldInfo.type == Date::class.java)
                haveDate = true
        }
        if (haveDate)
            importCode.append("\nimport java.util.Date;\n")
        return importCode.toString()
    }

    fun haveDate(): Boolean
    {
        var result = false
        for (fieldInfo in fieldInfos)
        {
            if (fieldInfo.type == Date::class.java)
                result = true
        }
        return result
    }

    fun getPreKey(): FieldInfo
    {
        if (key != null)
            return key!!
        else
            throw Exception("数据表:$tabelName,不存在主键")
    }
}