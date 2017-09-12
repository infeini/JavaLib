package auto.pojo

import auto.InfoTool
import auto.LoadTool
import auto.TableInfo
import ljs.io.IOUtil
import ljs.io.file.FileUtils
import ljs.lib.StringUtils
import java.io.File
import java.sql.Connection
import java.util.*

object Auto
{
    fun AutoPojo(tableInfo: TableInfo, packageName: String, saveAs: File)
    {
        var map = initReplaceMap(saveAs, packageName)
        var example = IOUtil.toString(javaClass.getResourceAsStream("/auto/pojo/class.txt"), "UTF-8", true)
        var code = StringBuffer("")
        var fieldCode = StringBuffer("")
        var methodCode = StringBuffer("")
        fieldCode.append(tableInfo.getPreKey().getFieldCode())
        methodCode.append(tableInfo.getPreKey().getSetterCode())
        for (fieldInfo in tableInfo.fieldInfos)
        {
            fieldCode.append(fieldInfo.getFieldCode())
            methodCode.append(fieldInfo.getGetterCode() + fieldInfo.getSetterCode())
        }
        code.append(fieldCode.toString() + methodCode.toString())
        map.put("\\*\\{code\\}", code.toString())
        map.put("\\*\\{import\\}", tableInfo.getImportCode())
        StringUtils.replaceAll(map, example)
        IOUtil.write(example.toString(), saveAs, "UTF-8")
        println("生成pojo:" + saveAs.canonicalPath)
    }

    fun AutoAllPojo(jdbcConnect: Connection, dbName: String, packageName: String, saveDir: File)
    {
        if (!saveDir.exists())
            saveDir.mkdirs()
        var dbInfo = LoadTool.loadDataBaseInfo(dbName, jdbcConnect)
        for (tableInfo in dbInfo.tableInfos)
            AutoPojo(tableInfo, packageName, File(saveDir, InfoTool.formatToClass(tableInfo.tabelName) + ".java"))
    }

    private fun initReplaceMap(saveAs: File, packageName: String): HashMap<String, Any>
    {
        var map: HashMap<String, Any> = HashMap<String, Any>()
        map.put("\\*\\{fileName\\}", FileUtils.getNameNoSuffix(saveAs))
        map.put("\\*\\{packageName\\}", packageName)
        return map
    }
}