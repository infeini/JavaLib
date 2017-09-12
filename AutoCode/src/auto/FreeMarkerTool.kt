package auto

import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler
import ljs.io.IOUtil
import ljs.io.file.FileUtils
import org.junit.Test
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.sql.Connection
import java.sql.DriverManager
import java.util.*

class FreeMarkerTool
{
    @Test
    fun auto()
    {
        Class.forName("com.mysql.jdbc.Driver")
        var jdbcConnect = DriverManager.getConnection("jdbc:mysql://localhost:3306/plm", "root", "123456")
        FreeMarkerTool.autoPojo("pojo", File("./test/pojo"), jdbcConnect, "plm")
        FreeMarkerTool.autoMapperJava("mapper", File("./test/pojo"), jdbcConnect, "plm")
    }

    companion object
    {
        @Throws(Exception::class)
        fun autoPojo(packageName: String, saveDir: File, jdbcConnect: Connection, dbName: String)
        {
            if (!saveDir.exists())
                saveDir.mkdirs()
            var dbInfo = LoadTool.loadDataBaseInfo(dbName, jdbcConnect)
            for (tableInfo in dbInfo.tableInfos)
            {
                var saveAs = File(saveDir, InfoTool.formatToClass(tableInfo.tabelName) + ".java")
                val map = HashMap<Any, Any>()
                map.put("packageName", packageName)
                map.put("saveAs", saveAs)
                map.put("tableInfo", tableInfo)
                map.put("FileUtils", FileUtils())
                map.put("InfoTool", InfoTool)
                write(map, File("./src/auto/pojo/pojo.txt"), saveAs)
            }
        }

        @Throws(Exception::class)
        fun autoMapperJava(packageName: String, saveDir: File, jdbcConnect: Connection, dbName: String)
        {
            if (!saveDir.exists())
                saveDir.mkdirs()
            var dbInfo = LoadTool.loadDataBaseInfo(dbName, jdbcConnect)
            for (tableInfo in dbInfo.tableInfos)
            {
                var saveAs = File(saveDir, InfoTool.formatToClass(tableInfo.tabelName) + ".java")
                val map = HashMap<Any, Any>()
                map.put("packageName", packageName)
                map.put("saveAs", saveAs)
                map.put("tableInfo", tableInfo)
                map.put("FileUtils", FileUtils())
                map.put("InfoTool", InfoTool)
                write(map, File("./src/auto/pojo/pojo.txt"), saveAs)
            }
        }

        @Throws(Exception::class)
        fun write(model: Any, view: File, saveAs: File)
        {
            val cfg = Configuration(Configuration.VERSION_2_3_25)
            cfg.setDirectoryForTemplateLoading(view.parentFile)
            cfg.defaultEncoding = "UTF-8"
            cfg.templateExceptionHandler = TemplateExceptionHandler.RETHROW_HANDLER
            cfg.logTemplateExceptions = false
            val temp = cfg.getTemplate(view.name)
            var writer = BufferedWriter(OutputStreamWriter(FileOutputStream(saveAs)))
            temp.process(model, writer)
            IOUtil.close(writer)
        }
    }
}
